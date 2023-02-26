package ru.hits.hitsback.timetable.dto.authorisation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsDto {
    @NotEmpty(message = "email.empty")
    @Email
    private String email;
    @NotEmpty(message = "password.empty")
    private String password;
}
