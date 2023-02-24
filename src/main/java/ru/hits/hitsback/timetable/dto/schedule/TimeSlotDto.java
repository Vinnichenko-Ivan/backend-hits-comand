package ru.hits.hitsback.timetable.dto.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class TimeSlotDto {
    @JsonProperty("id")
    private TimeSlotIdDto timeSlotIdDto;
    private LocalDate date;
    private DayOfWeek dayOfWeek;
    private LessonTimeDto lessonTime;
}
