package ru.hits.hitsback.timetable.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TeacherCreateDto implements Serializable {
    @NotEmpty(message = "first-name.empty")
    @JsonProperty private String firstName;
    @NotEmpty(message = "last-name.empty")
    @JsonProperty private String lastName;
    @NotEmpty(message = "patronymic-name.empty")
    @JsonProperty private String patronymicName;
}
