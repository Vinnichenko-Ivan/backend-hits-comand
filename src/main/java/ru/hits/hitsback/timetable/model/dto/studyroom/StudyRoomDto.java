package ru.hits.hitsback.timetable.model.dto.studyroom;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyRoomDto {
    @NotNull(message = "study-room-id.required")
    @JsonProperty("id") private StudyRoomIdDto studyRoomIdDto;
    @NotNull(message = "building-number.required")
    @JsonProperty private Integer buildingNumber;
    @JsonProperty private Integer floor;
    @JsonProperty private String name;
    @NotEmpty(message = "number.required")
    @JsonProperty private String number;
}
