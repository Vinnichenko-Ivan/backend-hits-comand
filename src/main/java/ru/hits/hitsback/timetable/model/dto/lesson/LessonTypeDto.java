package ru.hits.hitsback.timetable.model.dto.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonTypeDto {
    @NotNull
    @JsonProperty("id") private LessonTypeIdDto lessonTypeIdDto;
    @NotNull
    @JsonProperty private String name;
}
