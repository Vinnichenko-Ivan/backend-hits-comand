package ru.hits.hitsback.timetable.dto.acoount;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordModifyDto {
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).*", message = "password must include at least one letter and one numeric digit")
    @Size(min = 6, max = 64, message = "password must be at least 6 characters and no more than 64 characters")
    private String password;
    @NotEmpty(message = "password must be confirmed")
    private String confirmedPassword;
}
