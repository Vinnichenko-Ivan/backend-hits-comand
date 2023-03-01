package ru.hits.hitsback.timetable.dto.authorisation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {
    private String token;

    public TokenDto(String token) {
        this.token = token;
    }

    public TokenDto() {
    }
}
