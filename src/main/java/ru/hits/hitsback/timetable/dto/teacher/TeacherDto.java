package ru.hits.hitsback.timetable.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDto {
    @JsonProperty("id") private TeacherIdDto teacherIdDto;
    @JsonProperty private String firstName;
    @JsonProperty private String lastName;
    @JsonProperty private String patronymicName;
}
