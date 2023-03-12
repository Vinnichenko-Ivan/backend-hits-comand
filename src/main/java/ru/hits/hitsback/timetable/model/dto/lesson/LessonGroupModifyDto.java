package ru.hits.hitsback.timetable.model.dto.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LessonGroupModifyDto extends LessonGroupCreateDto {
    @JsonProperty("id") private LessonGroupIdDto lessonGroupIdDto;
}
