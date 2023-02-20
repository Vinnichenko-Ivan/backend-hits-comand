package ru.hits.hitsback.timetable.dto.authorisation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsDto {
    private String email;
    private String password;
}
