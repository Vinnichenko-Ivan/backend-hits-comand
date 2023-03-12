package ru.hits.hitsback.timetable.model.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeDto {
    private Integer hour;
    private Integer minute;
    private Integer second;
    private Integer nano;
}
