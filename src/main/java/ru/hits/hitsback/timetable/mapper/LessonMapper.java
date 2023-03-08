package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonDto;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonShortDto;
import ru.hits.hitsback.timetable.model.entity.Lesson;

@Mapper(componentModel = "spring")
public interface LessonMapper extends StudyRoomMapper, LessonTypeMapper, TeacherMapper, LessonTimeMapper, GroupMapper {
    @Mapping(target = "lessonIdDto.id", source = "id")
    @Mapping(target = "groups", source = "lessonGroup.group")
    @Mapping(target = "lessonType", source = "lessonGroup.lessonType")
    @Mapping(target = "subject", source = "lessonGroup.subject")
    LessonDto toDto(Lesson lesson);
    Lesson toEntity(LessonDto lessonDto);
    LessonShortDto toShortDto(LessonDto lessonDto);
}
