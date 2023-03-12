package ru.hits.hitsback.timetable.service.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.hitsback.timetable.mapper.LessonMapper;
import ru.hits.hitsback.timetable.mapper.LessonTimeMapper;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonDto;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonShortDto;
import ru.hits.hitsback.timetable.model.dto.schedule.DayScheduleDto;
import ru.hits.hitsback.timetable.model.dto.schedule.LessonTimeDto;
import ru.hits.hitsback.timetable.model.dto.schedule.TimeIntervalDto;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomIdDto;
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
        return fetchGroupSchedule(timeIntervalDto, new GroupIdDto(account.getGroup().getId().toString()));
    }

    @Override
    public List<DayScheduleDto> fetchGroupSchedule(TimeIntervalDto timeIntervalDto, GroupIdDto groupIdDto) {
        return fetchScheduleWithLessonOptions(timeIntervalDto, null, null, List.of(groupIdDto.getId().toString()));
    }

    @Override
    public List<DayScheduleDto> fetchTeacherSchedule(TimeIntervalDto timeIntervalDto, TeacherIdDto teacherIdDto) {
        return fetchScheduleWithLessonOptions(timeIntervalDto, teacherIdDto.getId().toString(), null, null);
    }

    @Override
    public List<DayScheduleDto> fetchStudyRoomSchedule(TimeIntervalDto timeIntervalDto, StudyRoomIdDto studyRoomIdDto){
        return fetchScheduleWithLessonOptions(timeIntervalDto, null, studyRoomIdDto.getId().toString(), null);
    }

    @Override
    public List<DayScheduleDto> fetchScheduleWithLessonOptions(TimeIntervalDto timeIntervalDto, String teacherId, String studyRoomId, List<String> groupIds) {
        List<Lesson> lessons = fetchLessonsWithLessonOptionsLessons(timeIntervalDto, teacherId, studyRoomId, groupIds);
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

    private List<Lesson> fetchLessonsWithLessonOptionsLessons(TimeIntervalDto timeIntervalDto, String teacherId, String studyRoomId, List<String> groupIds) {
        UUID teacherUUID;
        UUID studyRoomUUID;
        List<Group> groups;

        try {
            teacherUUID = UUID.fromString(teacherId);
        } catch (Exception e){
            teacherUUID = null;
        }

        try {
            studyRoomUUID = UUID.fromString(studyRoomId);
        } catch (Exception e){
            studyRoomUUID = null;
        }

        try {
            groups = fetchGroupByIdIn(groupIds.stream().map(GroupIdDto::new).toList());
        } catch (Exception e){
            groups = null;
        }

        return lessonRepository
                .findAllByLessonOptions(
                        Date.valueOf(timeIntervalDto.getStartDate()),
                        Date.valueOf(timeIntervalDto.getEndDate()),
                        teacherUUID,
                        studyRoomUUID,
                        groups);
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
            dayScheduleDtos.add(dayScheduleDto);
        }

        return dayScheduleDtos;
    }

    private DayScheduleDto getDayScheduleFromLessonsByDate(Map<LocalDate, List<LessonDto>> lessonsByDate, LocalDate day) {
        List<LessonDto> dayLessons = lessonsByDate.get(day);
        return dayLessonsToDayScheduleDto(dayLessons, day);
    }

    private DayScheduleDto dayLessonsToDayScheduleDto(List<LessonDto> dayLessons, LocalDate day) {
        if (dayLessons == null) return new DayScheduleDto(day, day.getDayOfWeek());

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
