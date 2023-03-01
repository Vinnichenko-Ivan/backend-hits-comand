package ru.hits.hitsback.timetable.dto.authorisation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {
    @JsonProperty private String token;

    public TokenDto(String token) {
        this.token = token;
    }

    public TokenDto() {
    }
}
