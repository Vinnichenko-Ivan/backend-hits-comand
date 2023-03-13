package ru.hits.hitsback.timetable.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import ru.hits.hitsback.timetable.model.dto.schedule.TimeDto;

import java.time.LocalTime;

public class TimeDtoConverter extends StdConverter<LocalTime, TimeDto> {
    @Override
    public TimeDto convert(LocalTime localTime){
        return new TimeDto(localTime.getHour(), localTime.getMinute(), localTime.getSecond(), localTime.getNano());
    }
}
