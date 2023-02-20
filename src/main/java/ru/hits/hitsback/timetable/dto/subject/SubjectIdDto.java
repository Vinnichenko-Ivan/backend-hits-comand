package ru.hits.hitsback.timetable.dto.subject;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SubjectIdDto {
    @JsonValue
    private UUID id;

    public SubjectIdDto(String id) {
        this.id = UUID.fromString(id);
    }
}
