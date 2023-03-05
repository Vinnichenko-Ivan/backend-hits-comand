package ru.hits.hitsback.timetable.model.dto.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class LessonTimeDto {
    @NotNull
    @JsonProperty private Integer lessonNumber;
    @NotNull
    @JsonProperty private LocalTime startTime;
    @NotNull
    @JsonProperty private LocalTime endTime;
}
