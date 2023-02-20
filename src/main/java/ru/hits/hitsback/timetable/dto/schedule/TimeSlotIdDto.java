package ru.hits.hitsback.timetable.dto.schedule;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TimeSlotIdDto {
    @JsonValue
    private UUID id;
}
