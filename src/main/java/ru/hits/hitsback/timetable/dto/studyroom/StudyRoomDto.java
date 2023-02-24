package ru.hits.hitsback.timetable.dto.studyroom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyRoomDto {
    @JsonProperty("id")
    private StudyRoomIdDto studyRoomIdDto;
    private Integer buildingNumber;
    private Integer floor;
    private String name;
    private Integer number;
}
