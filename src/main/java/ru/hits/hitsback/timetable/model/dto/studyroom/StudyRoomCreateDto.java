package ru.hits.hitsback.timetable.model.dto.studyroom;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyRoomCreateDto {
    @NotNull(message = "building-number.required")
    @JsonProperty private Integer buildingNumber;
    @JsonProperty private Integer floor;
    @NotEmpty(message = "name.empty")
    @JsonProperty
    private String name;
    @NotNull(message = "number.empty")
    @JsonProperty
    private String number;
}
