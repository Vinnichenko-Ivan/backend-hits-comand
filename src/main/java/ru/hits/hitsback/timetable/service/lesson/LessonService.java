package ru.hits.hitsback.timetable.service.lesson;

import ru.hits.hitsback.timetable.model.dto.lesson.*;

import java.util.List;

public interface LessonService {
    void deleteLesson(LessonIdDto lessonIdDto);

    void modifyLessonGroup(LessonGroupModifyDto lessonGroupModifyDto);

    void modifyLesson(LessonModifyDto lessonModifyDto);

    List<LessonIdDto> createLesson(LessonGroupCreateDto lessonGroupCreateDto);

    List<LessonTypeDto> fetchLessonTypes();

    LessonDto fetchLesson(LessonIdDto lessonIdDto);
}
