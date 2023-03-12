package ru.hits.hitsback.timetable.model.dto.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.lesson.LessonShortDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DayScheduleDto {
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty private LocalDate date;
    @NotNull
    @JsonProperty private DayOfWeek dayOfWeek;
    @NotNull
    @JsonProperty private List<LessonShortDto> lessons;

    public DayScheduleDto(LocalDate date, DayOfWeek dayOfWeek) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.lessons = new ArrayList<>();
    }
}
