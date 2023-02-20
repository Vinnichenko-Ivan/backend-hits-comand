package ru.hits.hitsback.timetable.dto.authorisation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TeacherRegisterDto {
    @NotEmpty
    private UUID teacherId;
    @NotEmpty
    @Email
    private String email;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).*", message = "password must include at least one letter and one numeric digit")
    @Size(min = 6, max = 64, message = "password must be at least 6 characters and no more than 64 characters")
    private String password;
    @NotEmpty(message = "password must be confirmed")
    private String confirmedPassword;
}
