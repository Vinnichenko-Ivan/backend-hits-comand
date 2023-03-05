package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonShortDto;
import ru.hits.hitsback.timetable.model.entity.Lesson;

@Mapper
public interface LessonShortMapper extends StudyRoomMapper, LessonTypeMapper, LessonTimeMapper, TeacherMapper {
    @Mapping(target = "lessonIdDto.id", source = "id")
    LessonShortDto map(Lesson lesson);
    Lesson map(LessonShortDto lessonShortDto);
}
