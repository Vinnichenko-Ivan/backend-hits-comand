package ru.hits.hitsback.timetable.model.dto.authorisation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsDto {
    @NotEmpty(message = "email.empty")
    @Email
    @JsonProperty private String email;
    @NotEmpty(message = "password.empty")
    @JsonProperty private String password;
}
