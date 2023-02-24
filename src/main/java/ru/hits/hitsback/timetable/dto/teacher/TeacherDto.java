package ru.hits.hitsback.timetable.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDto {
    @JsonProperty("id")
    private TeacherIdDto teacherIdDto;
    private String firstName;
    private String lastName;
    private String patronymicName;
    private String email;
}
