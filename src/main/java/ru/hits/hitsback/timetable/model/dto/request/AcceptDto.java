package ru.hits.hitsback.timetable.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcceptDto {
    @JsonProperty private Boolean accept;
}
