package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import ru.hits.hitsback.timetable.model.dto.schedule.LessonTimeDto;
import ru.hits.hitsback.timetable.model.entity.LessonTime;

@Mapper
public interface LessonTimeMapper {
    LessonTimeDto map(LessonTime lessonTime);
    LessonTime map(LessonTimeDto lessonTimeDto);
}