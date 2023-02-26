package ru.hits.hitsback.timetable.dto.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LessonTypeDto {
    @JsonProperty("id")
    private LessonTypeIdDto lessonTypeIdDto;
    private String name;
}
