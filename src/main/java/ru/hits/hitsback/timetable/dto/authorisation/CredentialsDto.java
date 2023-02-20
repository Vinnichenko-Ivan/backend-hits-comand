package ru.hits.hitsback.timetable.dto.authorisation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsDto {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
}
