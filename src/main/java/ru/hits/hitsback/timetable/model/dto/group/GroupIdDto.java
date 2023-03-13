package ru.hits.hitsback.timetable.model.dto.group;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class GroupIdDto {
    @JsonValue
    private UUID id;

    public GroupIdDto(String id) {
        this.id = UUID.fromString(id);
    }
}
