package ru.hits.hitsback.timetable.dto.studyroom;

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
    @NotEmpty(message = "name.empty")
    @JsonProperty private String name;
    @NotNull(message = "number.required")
    @JsonProperty private Integer number;
}
