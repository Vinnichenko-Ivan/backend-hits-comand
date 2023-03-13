package ru.hits.hitsback.timetable.service.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.exception.group.GroupNotFoundException;
import ru.hits.hitsback.timetable.exception.lesson.*;
import ru.hits.hitsback.timetable.exception.studyroom.StudyRoomNotFoundException;
import ru.hits.hitsback.timetable.exception.subject.SubjectNotFoundException;
import ru.hits.hitsback.timetable.exception.teacher.TeacherNotFoundException;
import ru.hits.hitsback.timetable.mapper.LessonMapper;
import ru.hits.hitsback.timetable.mapper.LessonTypeMapper;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.model.dto.lesson.*;
import ru.hits.hitsback.timetable.model.entity.*;
import ru.hits.hitsback.timetable.model.util.LessonsBeforeAndAfterToday;
import ru.hits.hitsback.timetable.repository.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final LessonTypeRepository lessonTypeRepository;
    private final LessonMapper lessonMapper;
    private final LessonTypeMapper lessonTypeMapper;
    private final GroupRepository groupRepository;
    private final StudyRoomRepository studyRoomRepository;
    private final LessonTimeRepository lessonTimeRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final LessonGroupRepository lessonGroupRepository;

    @Override
    public void deleteLesson(LessonIdDto lessonIdDto) {
        Lesson lesson = lessonRepository
                .findById(lessonIdDto.getId())
                .orElseThrow(LessonNotFoundException::new);

        checkDateNotBeforeToday(lesson.getDate().toLocalDate());

        if (lesson.getLessonGroup().getLessons().size() == 0) {
            lessonGroupRepository.deleteById(lesson.getLessonGroup().getId());
        }

        lessonRepository.deleteById(lessonIdDto.getId());
    }

    @Override
    public void deleteLessonGroup(LessonIdDto lessonIdDto) {
        Lesson lesson = lessonRepository
                .findById(lessonIdDto.getId())
                .orElseThrow(LessonNotFoundException::new);

        LessonGroup lessonGroup = lesson.getLessonGroup();
        LessonsBeforeAndAfterToday lessonsBeforeAndAfterToday = divideLessonsByBeforeAndAfterToday(lessonGroup.getLessons().stream().toList());

        if (lessonsBeforeAndAfterToday.getLessonsBeforeToday().size() == 0) {
            lessonGroupRepository.deleteById(lessonGroup.getId());
        } else {
            if (lessonsBeforeAndAfterToday.getLessonsAfterToday().size() == 0){
                throw new LessonsHaveFinishedException();
            }

            deleteLessons(lessonsBeforeAndAfterToday.getLessonsAfterToday());
        }
    }

    @Override
    public void modifyLessonGroup(LessonGroupModifyDto lessonGroupModifyDto) {
//        LessonGroup lessonGroup = lessonRepository
//                .findById(lessonGroupModifyDto.getLessonIdDto().getId())
//                .orElseThrow(LessonNotFoundException::new)
//                .getLessonGroup();
        Lesson lesson = lessonRepository
                .findById(lessonGroupModifyDto.getLessonIdDto().getId())
                .orElseThrow(LessonNotFoundException::new);
//        lessonGroupRepository.findBy
        LessonGroup lessonGroup = lesson.getLessonGroup();

        LessonsBeforeAndAfterToday lessonsBeforeAndAfterToday = divideLessonsByBeforeAndAfterToday(lessonGroup.getLessons().stream().toList());
        lessonGroup.setLessons(new HashSet<>(lessonsBeforeAndAfterToday.getLessonsBeforeToday()));

        lessonGroupModifyDto.setStartDate(LocalDate.now());
        createLesson(lessonGroupModifyDto);
        lessonsBeforeAndAfterToday.getLessonsAfterToday().forEach(it -> deleteLesson(new LessonIdDto(it.getId().toString())));
    }

    @Override
    public void modifyLesson(LessonModifyDto lessonModifyDto) {
        Lesson lesson = lessonRepository.findById(lessonModifyDto.getLessonIdDto().getId()).orElseThrow(LessonNotFoundException::new);

        checkDateNotBeforeToday(lesson.getDate().toLocalDate());
        checkDateNotBeforeToday(lessonModifyDto.getStartDate());

        checkIntersects(lessonModifyDto.getStartDate(),
                lessonModifyDto.getStartDate(),
                lessonModifyDto.getTeacherId().getId(),
                lessonModifyDto.getStudyRoomId().getId(),
                lesson.getLessonGroup().getGroups(),
                lessonModifyDto.getLessonNumber(),
                lesson.getLessonGroup().getFrequency());

        Lesson updatedLesson = getLesson(lessonModifyDto);

        lesson.setStudyRoom(updatedLesson.getStudyRoom());
        lesson.setTeacher(updatedLesson.getTeacher());
        lesson.setLessonTime(updatedLesson.getLessonTime());
        lesson.setDate(updatedLesson.getDate());
        lesson.setDayOfWeek(updatedLesson.getDate().toLocalDate().getDayOfWeek());

        lessonRepository.save(lesson);
    }

    @Override
    public List<LessonIdDto> createLesson(LessonGroupCreateDto lessonGroupCreateDto) {
        validateDates(lessonGroupCreateDto.getStartDate(), lessonGroupCreateDto.getEndDate());

        checkIntersects(lessonGroupCreateDto.getStartDate(),
                lessonGroupCreateDto.getEndDate(),
                lessonGroupCreateDto.getTeacherId().getId(),
                lessonGroupCreateDto.getStudyRoomId().getId(),
                groupRepository.findAllByIdIn(lessonGroupCreateDto.getGroupIds().stream().map(GroupIdDto::getId).toList()),
                lessonGroupCreateDto.getLessonNumber(),
                lessonGroupCreateDto.getFrequency());

        LessonGroup lessonGroup = getLessonGroup(lessonGroupCreateDto);
        Set<Lesson> lessons = lessonGroup.getLessons();
        lessonGroup.setLessons(new HashSet<>());
        lessonGroupRepository.save(lessonGroup);

        lessons.forEach(it -> it.setLessonGroup(lessonGroup));
        lessonRepository.saveAll(lessons);

        return lessons
                .stream()
                .map(it -> new LessonIdDto(it.getId().toString()))
                .toList();
    }

    @Override
    public List<LessonTypeDto> fetchLessonTypes() {
        return lessonTypeRepository
                .findAll()
                .stream()
                .map(lessonTypeMapper::toDto)
                .toList();
    }

    @Override
    public LessonDto fetchLesson(LessonIdDto lessonIdDto) {
        return lessonMapper.toDto(lessonRepository
                .findById(lessonIdDto.getId())
                .orElseThrow(LessonNotFoundException::new));
    }

    private void deleteLessons(List<Lesson> lessons) {
        List<LessonIdDto> lessonIds = new ArrayList<>();
        lessons.forEach(it -> lessonIds.add(new LessonIdDto(it.getId().toString())));
        lessonIds.forEach(this::deleteLesson);
    }

    private LessonsBeforeAndAfterToday divideLessonsByBeforeAndAfterToday(List<Lesson> lessons) {
        List<Lesson> lessonsBeforeToday = new ArrayList<>();
        List<Lesson> lessonsAfterToday = new ArrayList<>();

        lessons.forEach(it -> {
            if (it.getDate().toLocalDate().isBefore(LocalDate.now())) {
                lessonsBeforeToday.add(it);
            } else {
                lessonsAfterToday.add(it);
            }
        });

        return new LessonsBeforeAndAfterToday(lessonsBeforeToday, lessonsAfterToday);
    }

    private void checkIntersects(LocalDate startDate, LocalDate endDate, UUID teacherId, UUID studyRoomId, List<Group> groups, int lessonNumber, int frequency) {
        List<Lesson> lessons = lessonRepository.findAllByLessonOptions(Date.valueOf(startDate), Date.valueOf(endDate), teacherId, studyRoomId, groups);
        List<Lesson> intersectedLessons = new ArrayList<>();

        for (LocalDate date = startDate; date.isBefore(endDate) || date.isEqual(endDate); date = date.plusWeeks(frequency)) {
            LocalDate finalDate = date;

            intersectedLessons.addAll(lessons
                    .stream()
                    .filter(it -> it.getDate().toLocalDate().isEqual(finalDate)
                            && it.getLessonTime().getLessonNumber() == lessonNumber)
                    .toList());
        }

        if (intersectedLessons.size() != 0) {
            throwLessonsIntersectException(intersectedLessons, lessons.stream().findFirst().get().getLessonGroup());
        }
    }

    private void throwLessonsIntersectException(List<Lesson> lessons, LessonGroup lessonGroup) {
        StringBuilder message = new StringBuilder();
        message.append("lessons intersect: \n");

        for (Lesson lesson : lessons) {
            message.append("in ")
                    .append(lesson.getDate().toLocalDate().toString())
                    .append(" at ")
                    .append(DateTimeFormatter.ofPattern("HH:mm").format(lesson.getLessonTime().getStartTime()))
                    .append(" - ")
                    .append(DateTimeFormatter.ofPattern("HH:mm").format(lesson.getLessonTime().getEndTime()))
                    .append(" ")
                    .append(lessonGroup.getSubject().getName())
                    .append("\n");
        }

        throw new LessonsIntersectException(message.toString());
    }

    private void validateDates(LocalDate startDate, LocalDate endDate) {
        checkDateNotBeforeToday(startDate);
        checkStartDateBeforeEndDate(startDate, endDate);
    }

    private void checkDateNotBeforeToday(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new DateBeforeTodayException();
        }
    }

    private void checkStartDateBeforeEndDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new EndDateBeforeStartDateException();
        }
    }

    private LessonGroup getLessonGroup(LessonGroupCreateDto lessonGroupCreateDto) {
        List<Lesson> lessons = getLessonGroupLessons(lessonGroupCreateDto);

        List<Group> groups = groupRepository
                .findAllByIdIn(lessonGroupCreateDto
                        .getGroupIds()
                        .stream()
                        .map(GroupIdDto::getId)
                        .toList());

        if (groups == null || groups.isEmpty()) {
            throw new GroupNotFoundException();
        }

        LessonType lessonType = lessonTypeRepository
                .findById(lessonGroupCreateDto.getLessonTypeId().getId())
                .orElseThrow(LessonTypeNotFoundException::new);

        Subject subject = subjectRepository
                .findById(lessonGroupCreateDto.getSubjectId().getId())
                .orElseThrow(SubjectNotFoundException::new);

        return new LessonGroup(
                new HashSet<>(lessons),
                Date.valueOf(lessonGroupCreateDto.getStartDate()),
                Date.valueOf(lessonGroupCreateDto.getEndDate()),
                lessonGroupCreateDto.getFrequency(),
                subject,
                lessonType,
                groups);
    }

    private List<Lesson> getLessonGroupLessons(LessonGroupCreateDto lessonGroupCreateDto) {
        LessonModifyDto lessonModifyDto = lessonMapper.toLessonModifyDto(lessonGroupCreateDto);
        List<Lesson> lessons = new ArrayList<>();

        for (LocalDate date = lessonGroupCreateDto.getStartDate();
             date.isBefore(lessonGroupCreateDto.getEndDate()) || date.isEqual(lessonGroupCreateDto.getEndDate());
             date = date.plusWeeks(lessonGroupCreateDto.getFrequency())
        ) {
            lessonModifyDto.setStartDate(date);
            lessons.add(getLesson(lessonModifyDto));
        }

        return lessons;
    }

    private Lesson getLesson(LessonModifyDto lessonModifyDto) {
        StudyRoom studyRoom = studyRoomRepository
                .findById(lessonModifyDto.getStudyRoomId().getId())
                .orElseThrow(StudyRoomNotFoundException::new);

        Teacher teacher = teacherRepository
                .findById(lessonModifyDto.getTeacherId().getId())
                .orElseThrow(TeacherNotFoundException::new);

        LessonTime lessonTime = lessonTimeRepository
                .findByLessonNumber(lessonModifyDto.getLessonNumber())
                .orElseThrow(LessonNumberIsNotExistException::new);

        return new Lesson(
                studyRoom,
                teacher,
                Date.valueOf(lessonModifyDto.getStartDate()),
                lessonModifyDto.getStartDate().getDayOfWeek(),
                lessonTime);
    }
}