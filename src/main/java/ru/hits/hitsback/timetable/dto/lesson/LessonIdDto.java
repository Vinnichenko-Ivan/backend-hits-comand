package ru.hits.hitsback.timetable.dto.lesson;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class LessonIdDto {
    @JsonValue
    private UUID id;

    public LessonIdDto(String id) {
        this.id = UUID.fromString(id);
    }
}
