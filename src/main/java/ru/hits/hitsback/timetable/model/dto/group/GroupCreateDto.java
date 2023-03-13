package ru.hits.hitsback.timetable.model.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupCreateDto {
    @NotEmpty(message = "number.empty")
    @JsonProperty private String number;
}
