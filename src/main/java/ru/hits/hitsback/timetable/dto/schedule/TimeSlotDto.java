package ru.hits.hitsback.timetable.dto.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.UUID;

public class TimeSlotDto {
    private UUID id;
    private LocalDate date;
    private DayOfWeek dayOfWeek;
    private LessonTimeDto lessonTime;
}
