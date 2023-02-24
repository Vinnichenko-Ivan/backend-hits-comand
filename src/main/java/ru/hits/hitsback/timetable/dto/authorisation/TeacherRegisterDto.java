package ru.hits.hitsback.timetable.dto.authorisation;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.teacher.TeacherIdDto;

@Getter
@Setter
public class TeacherRegisterDto {
    @NotNull(message = "teacher-id.required")
    private TeacherIdDto teacherId;
    @NotEmpty(message = "email.empty")
    @Email
    private String email;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).*", message = "password.too-simple")
    @Size(min = 6, max = 64, message = "password.too-long-or-short")
    private String password;
    @NotEmpty(message = "password.not-confirmed")
    private String confirmedPassword;
}
