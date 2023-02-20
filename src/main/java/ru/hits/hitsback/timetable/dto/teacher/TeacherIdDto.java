package ru.hits.hitsback.timetable.dto.teacher;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class TeacherIdDto {
    @JsonValue
    private UUID id;

    public TeacherIdDto(String id) {
        this.id = UUID.fromString(id);
    }
}
