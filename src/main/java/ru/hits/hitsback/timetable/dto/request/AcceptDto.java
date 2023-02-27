package ru.hits.hitsback.timetable.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcceptDto {
    @JsonProperty private Boolean accept;
}
