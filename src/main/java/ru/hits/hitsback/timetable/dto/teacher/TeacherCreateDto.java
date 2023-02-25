package ru.hits.hitsback.timetable.dto.teacher;

import jakarta.validation.constraints.NotEmpty;
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
}
