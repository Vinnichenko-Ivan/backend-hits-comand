package ru.hits.hitsback.timetable.dto.request;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationRequestIdDto {
    @JsonValue
    private UUID id;

    public RegistrationRequestIdDto(String id) {
        this.id = UUID.fromString(id);
    }
}
