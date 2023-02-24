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
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String patronymicName;
    @NotEmpty
    @Email
    private String email;
    @Size(min = 6, max = 64, message = "password must be at least 6 characters and no more than 64 characters")
    private String password;
    @NotEmpty(message = "password must be confirmed")
    private String confirmedPassword;
}
