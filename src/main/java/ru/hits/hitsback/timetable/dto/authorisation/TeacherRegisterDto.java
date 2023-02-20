package ru.hits.hitsback.timetable.dto.authorisation;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TeacherRegisterDto {
    private UUID teacherId;
    private String email;
    private String password;
    private String confirmedPassword;
}
