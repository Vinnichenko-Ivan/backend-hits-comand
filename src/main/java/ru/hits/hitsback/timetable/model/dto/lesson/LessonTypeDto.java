package ru.hits.hitsback.timetable.model.dto.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonTypeDto {
    @JsonProperty("id") private LessonTypeIdDto lessonTypeIdDto;
    @JsonProperty private String name;
}
