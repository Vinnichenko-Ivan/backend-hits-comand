package ru.hits.hitsback.timetable.dto.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class LessonTimeDto {
    @JsonProperty private Integer lessonNumber;
    @JsonProperty private LocalTime startTime;
    @JsonProperty private LocalTime endTime;
}
