package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import ru.hits.hitsback.timetable.model.dto.schedule.LessonTimeDto;
import ru.hits.hitsback.timetable.model.entity.LessonTime;

@Mapper(componentModel = "spring")
public interface LessonTimeMapper {
    LessonTimeDto toDto(LessonTime lessonTime);
    LessonTime toEntity(LessonTimeDto lessonTimeDto);
}