package ru.hits.hitsback.timetable.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDto {
    @NotNull(message = "teacher-id.required")
    @JsonProperty("id") private TeacherIdDto teacherIdDto;
    @NotEmpty(message = "first-name.empty")
    @JsonProperty private String firstName;
    @NotEmpty(message = "last-name.empty")
    @JsonProperty private String lastName;
    @NotEmpty(message = "patronymic-name.empty")
    @JsonProperty private String patronymicName;
}
