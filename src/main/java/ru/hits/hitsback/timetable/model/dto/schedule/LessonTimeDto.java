package ru.hits.hitsback.timetable.model.dto.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.converter.TimeDtoConverter;

import java.time.LocalTime;

@Getter
@Setter
public class LessonTimeDto {
    @NotNull
    @JsonProperty private Integer lessonNumber;
    @NotNull
    @JsonSerialize(converter = TimeDtoConverter.class)
    @JsonProperty private LocalTime startTime;
    @NotNull
    @JsonSerialize(converter = TimeDtoConverter.class)
    @JsonProperty private LocalTime endTime;
}
