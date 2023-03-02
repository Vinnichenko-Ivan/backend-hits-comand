package ru.hits.hitsback.timetable.model.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDto {
    @NotNull(message = "group-id.required")
    @JsonProperty("id") private GroupIdDto groupIdDto;
    @NotEmpty(message = "number.empty")
    @JsonProperty private String number;
}
