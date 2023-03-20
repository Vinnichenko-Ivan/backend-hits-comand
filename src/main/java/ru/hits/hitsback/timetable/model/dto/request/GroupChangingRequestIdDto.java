package ru.hits.hitsback.timetable.model.dto.request;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class GroupChangingRequestIdDto {
    @JsonValue
    private UUID id;

    public GroupChangingRequestIdDto(String id) {
        this.id = UUID.fromString(id);
    }
}
