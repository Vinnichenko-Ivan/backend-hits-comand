package ru.hits.hitsback.timetable.service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.hitsback.timetable.exception.GroupNotFoundException;
import ru.hits.hitsback.timetable.mapper.LessonMapper;
import ru.hits.hitsback.timetable.mapper.LessonTimeMapper;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonDto;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonShortDto;
import ru.hits.hitsback.timetable.model.dto.schedule.DayScheduleDto;
import ru.hits.hitsback.timetable.model.dto.schedule.LessonOptionsDto;
import ru.hits.hitsback.timetable.model.dto.schedule.LessonTimeDto;
import ru.hits.hitsback.timetable.model.dto.schedule.TimeIntervalDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherIdDto;
import ru.hits.hitsback.timetable.model.entity.Account;
import ru.hits.hitsback.timetable.model.entity.Group;
import ru.hits.hitsback.timetable.model.entity.Lesson;
import ru.hits.hitsback.timetable.repository.GroupRepository;
import ru.hits.hitsback.timetable.repository.LessonRepository;
import ru.hits.hitsback.timetable.repository.LessonTimeRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final LessonTimeMapper lessonTimeMapper;
    private final LessonMapper lessonMapper;
    private final LessonTimeRepository lessonTimeRepository;
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;

    @Override
    public List<DayScheduleDto> fetchSchedule(TimeIntervalDto timeIntervalDto, Account account) {
        return fetchGroupSchedule(timeIntervalDto, account.getGroup());
    }

    @Override
    public List<DayScheduleDto> fetchGroupSchedule(TimeIntervalDto timeIntervalDto, GroupIdDto groupIdDto) {
        return fetchGroupSchedule(timeIntervalDto, fetchGroupById(groupIdDto));
    }

    @Override
    public List<DayScheduleDto> fetchTeacherSchedule(TimeIntervalDto timeIntervalDto, TeacherIdDto teacherIdDto) {
        List<Lesson> lessons = fetchTeacherLessons(timeIntervalDto, teacherIdDto);
        return lessonsToDayScheduleDtos(lessons, timeIntervalDto);
    }

    @Override
    public List<DayScheduleDto> fetchScheduleWithLessonOptions(TimeIntervalDto timeIntervalDto, LessonOptionsDto lessonOptionsDto) {
        List<Lesson> lessons = fetchLessonsWithLessonOptionsLessons(timeIntervalDto, lessonOptionsDto);
        return lessonsToDayScheduleDtos(lessons, timeIntervalDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonTimeDto> fetchLessonTimes() {
        return lessonTimeRepository
                .findAll()
                .stream()
                .map(lessonTimeMapper::toDto)
                .toList();
    }

    private List<DayScheduleDto> fetchGroupSchedule(TimeIntervalDto timeIntervalDto, Group group) {
        List<Lesson> lessons = fetchGroupLessons(timeIntervalDto, group);
        return lessonsToDayScheduleDtos(lessons, timeIntervalDto);
    }

    private List<Lesson> fetchGroupLessons(TimeIntervalDto timeIntervalDto, Group group) {
        return lessonRepository
                .findAllByDateBetweenAndLessonGroupGroupIsContaining(
                        Date.valueOf(timeIntervalDto.getStartDate()),
                        Date.valueOf(timeIntervalDto.getEndDate()),
                        group);
    }

    private List<Lesson> fetchTeacherLessons(TimeIntervalDto timeIntervalDto, TeacherIdDto teacherIdDto) {
        return lessonRepository
                .findAllByDateBetweenAndTeacherId(
                        Date.valueOf(timeIntervalDto.getStartDate()),
                        Date.valueOf(timeIntervalDto.getEndDate()),
                        teacherIdDto.getId());
    }

    private List<Lesson> fetchLessonsWithLessonOptionsLessons(TimeIntervalDto timeIntervalDto, LessonOptionsDto lessonOptionsDto) {
        UUID teacherId;
        UUID studyRoomId;
        List<Group> groupIds;

        try {
            teacherId = lessonOptionsDto.getTeacherIdDto().getId();
        } catch (Exception e){
            teacherId = null;
        }

        try {
            studyRoomId = lessonOptionsDto.getStudyRoomIdDto().getId();
        } catch (Exception e){
            studyRoomId = null;
        }

        try {
            groupIds = fetchGroupByIdIn(lessonOptionsDto.getGroupIdDtos());
        } catch (Exception e){
            groupIds = null;
        }

        return lessonRepository
                .findAllByLessonOptions(
                        Date.valueOf(timeIntervalDto.getStartDate()),
                        Date.valueOf(timeIntervalDto.getEndDate()),
                        teacherId,
                        studyRoomId,
                        groupIds);
    }

    private Group fetchGroupById(GroupIdDto groupIdDto) {
        return groupRepository
                .findById(groupIdDto.getId())
                .orElseThrow(GroupNotFoundException::new);
    }

    private List<Group> fetchGroupByIdIn(List<GroupIdDto> groupIdDtos) {
        return groupRepository
                .findAllByIdIn(groupIdDtos
                        .stream()
                        .map(GroupIdDto::getId)
                        .toList());
    }

    private List<DayScheduleDto> lessonsToDayScheduleDtos(List<Lesson> lessons, TimeIntervalDto timeIntervalDto) {
        List<LessonDto> lessonDtos = lessons
                .stream()
                .map(lessonMapper::toDto)
                .toList();

        Map<LocalDate, List<LessonDto>> lessonsByDate = getGroupLessonsByDate(lessonDtos);
        return lessonDtosToDayScheduleDtos(lessonsByDate, timeIntervalDto.getStartDate(), timeIntervalDto.getEndDate());
    }

    private List<DayScheduleDto> lessonDtosToDayScheduleDtos(Map<LocalDate, List<LessonDto>> lessonsByDate, LocalDate startDate, LocalDate endDate) {
        List<DayScheduleDto> dayScheduleDtos = new ArrayList<>();

        for (LocalDate currentDate = startDate;
             currentDate.isBefore(endDate) || currentDate.isEqual(endDate);
             currentDate = currentDate.plusDays(1)
        ) {
            DayScheduleDto dayScheduleDto = getDayScheduleFromLessonsByDate(lessonsByDate, currentDate);

            if (dayScheduleDto != null) {
                dayScheduleDtos.add(dayScheduleDto);
            }
        }

        return dayScheduleDtos;
    }

    private DayScheduleDto getDayScheduleFromLessonsByDate(Map<LocalDate, List<LessonDto>> lessonsByDate, LocalDate day) {
        List<LessonDto> dayLessons = lessonsByDate.get(day);

        if (dayLessons != null) return dayLessonsToDayScheduleDto(dayLessons, day);
        return null;
    }

    private DayScheduleDto dayLessonsToDayScheduleDto(List<LessonDto> dayLessons, LocalDate day) {
        List<LessonShortDto> lessonShortDtos = dayLessons
                .stream()
                .map(lessonMapper::toShortDto)
                .toList();

        return new DayScheduleDto(day, day.getDayOfWeek(), lessonShortDtos);
    }

    private Map<LocalDate, List<LessonDto>> getGroupLessonsByDate(List<LessonDto> lessons) {
        return lessons.stream().collect(Collectors.groupingBy(LessonDto::getDate));
    }
}
