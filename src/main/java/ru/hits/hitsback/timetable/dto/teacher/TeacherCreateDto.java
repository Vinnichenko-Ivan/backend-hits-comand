package ru.hits.hitsback.timetable.dto.teacher;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TeacherCreateDto implements Serializable {
    private String firstName;
    private String lastName;
    private String patronymicName;
    private String email;
    private String password;
    private String confirmedPassword;
}
