package ru.hits.hitsback.timetable.model.dto.authorisation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherIdDto;

@Getter
@Setter
public class TeacherRegisterDto {
    @NotNull(message = "teacher-id.required")
    @JsonProperty private TeacherIdDto teacherId;
    @NotEmpty(message = "email.empty")
    @Email
    @JsonProperty private String email;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).*", message = "password.too-simple")
    @Size(min = 6, max = 64, message = "password.too-long-or-short")
    @JsonProperty private String password;
    @NotEmpty(message = "password.not-confirmed")
    @JsonProperty private String confirmedPassword;
}
