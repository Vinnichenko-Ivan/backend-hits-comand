package ru.hits.hitsback.timetable.dto.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Setter
@Getter
public class TimeSlotDto {
    @JsonProperty("id") private TimeSlotIdDto timeSlotIdDto;
    @JsonProperty private LocalDate date;
    @JsonProperty private DayOfWeek dayOfWeek;
    @JsonProperty private LessonTimeDto lessonTime;
}
