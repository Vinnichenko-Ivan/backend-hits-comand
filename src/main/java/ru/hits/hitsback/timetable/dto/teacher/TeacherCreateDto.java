package ru.hits.hitsback.timetable.dto.teacher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TeacherCreateDto implements Serializable {
    @NotEmpty(message = "first-name.empty")
    private String firstName;
    @NotEmpty(message = "last-name.empty")
    private String lastName;
    @NotEmpty(message = "patronymic-name.empty")
    private String patronymicName;
    @NotEmpty(message = "email.empty")
    @Email
    private String email;
    @Size(min = 6, max = 64, message = "password.too-long-or-short")
    private String password;
    @NotEmpty(message = "password.not-confirmed")
    private String confirmedPassword;
}
