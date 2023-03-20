package ru.hits.hitsback.timetable.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcceptDto {
    @NotNull
    @JsonProperty private Boolean accept;
}
