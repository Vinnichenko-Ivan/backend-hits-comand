package ru.hits.hitsback.timetable.dto.schedule;

import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.lesson.LessonDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DayScheduleDto {
    private LocalDate date;
    private List<LessonDto> lessons;
}
