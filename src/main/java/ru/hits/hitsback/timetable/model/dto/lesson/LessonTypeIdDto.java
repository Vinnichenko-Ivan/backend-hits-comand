package ru.hits.hitsback.timetable.model.dto.lesson;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class LessonTypeIdDto {
    @JsonValue
    private UUID id;

    public LessonTypeIdDto(String id) {
        this.id = UUID.fromString(id);
    }
}
