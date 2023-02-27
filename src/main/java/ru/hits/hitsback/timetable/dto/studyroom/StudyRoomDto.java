package ru.hits.hitsback.timetable.dto.studyroom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyRoomDto {
    @JsonProperty("id") private StudyRoomIdDto studyRoomIdDto;
    @JsonProperty private Integer buildingNumber;
    @JsonProperty private Integer floor;
    @JsonProperty private String name;
    @JsonProperty private Integer number;
}
