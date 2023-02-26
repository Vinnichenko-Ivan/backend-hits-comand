package ru.hits.hitsback.timetable.dto.studyroom;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyRoomCreateDto {
    @NotNull(message = "building-number.required")
    private Integer buildingNumber;
    private Integer floor;
    @NotEmpty(message = "name.empty")
    private String name;
    @NotNull(message = "number.required")
    private Integer number;
}
