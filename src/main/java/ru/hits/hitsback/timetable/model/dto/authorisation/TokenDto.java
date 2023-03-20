package ru.hits.hitsback.timetable.model.dto.authorisation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {
    @NotNull
    @JsonProperty private String token;

    public TokenDto(String token) {
        this.token = token;
    }

    public TokenDto() {
    }
}
