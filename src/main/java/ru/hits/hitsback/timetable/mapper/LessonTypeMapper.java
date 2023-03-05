package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonTypeDto;
import ru.hits.hitsback.timetable.model.entity.LessonType;

@Mapper
public interface LessonTypeMapper {
    @Mapping(target = "lessonTypeIdDto.id", source = "id")
    LessonTypeDto map(LessonType lessonType);
    LessonType map(LessonTypeDto lessonTypeDto);
}
