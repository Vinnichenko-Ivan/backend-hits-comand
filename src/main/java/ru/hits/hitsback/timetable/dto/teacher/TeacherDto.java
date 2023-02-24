package ru.hits.hitsback.timetable.dto.teacher;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDto {
    private TeacherIdDto id;
    private String firstName;
    private String lastName;
    private String patronymicName;
    private String email;
}
