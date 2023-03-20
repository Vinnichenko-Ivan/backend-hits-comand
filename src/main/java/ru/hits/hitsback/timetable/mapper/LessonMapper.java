package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonDto;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonGroupCreateDto;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonModifyDto;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonShortDto;
import ru.hits.hitsback.timetable.model.entity.Lesson;

@Mapper(componentModel = "spring", uses = {StudyRoomMapper.class, LessonTypeMapper.class, TeacherMapper.class, LessonTimeMapper.class, GroupMapper.class, SubjectMapper.class})
public interface LessonMapper {
    @Mapping(target = "lessonIdDto.id", source = "id")
    @Mapping(target = "lessonGroupId", source = "lessonGroup.id")
    @Mapping(target = "groups", source = "lessonGroup.groups")
    @Mapping(target = "lessonType", source = "lessonGroup.lessonType")
    @Mapping(target = "subject", source = "lessonGroup.subject")
    LessonDto toDto(Lesson lesson);
    Lesson toEntity(LessonDto lessonDto);
    LessonShortDto toShortDto(LessonDto lessonDto);
    LessonModifyDto toLessonModifyDto(LessonGroupCreateDto lessonGroupCreateDto);
}
