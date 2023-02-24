package ru.hits.hitsback.timetable.dto.studyroom;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class StudyRoomIdDto {
    @JsonValue
    private UUID id;

    public StudyRoomIdDto(String id) {
        this.id = UUID.fromString(id);
    }
}
