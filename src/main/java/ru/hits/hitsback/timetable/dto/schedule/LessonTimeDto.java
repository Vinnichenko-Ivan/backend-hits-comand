package ru.hits.hitsback.timetable.dto.schedule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class LessonTimeDto {
    private UUID id;
    private Integer lessonNumber;
    private LocalTime startTime;
    private LocalTime endTime;
}
