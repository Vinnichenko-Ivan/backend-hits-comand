package ru.hits.hitsback.timetable.model.dto.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DayScheduleDto {
    @JsonProperty private LocalDate date;
    @JsonProperty private List<LessonDto> lessons;
}
