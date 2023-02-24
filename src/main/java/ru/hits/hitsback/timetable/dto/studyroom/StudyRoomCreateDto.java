package ru.hits.hitsback.timetable.dto.studyroom;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyRoomCreateDto {
    @NotEmpty
    private Integer buildingNumber;
    private Integer floor;
    @NotEmpty
    private String name;
    @NotEmpty
    private Integer number;
}
