package ru.hits.hitsback.timetable.model.dto.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectCreateDto {
    @NotEmpty(message = "name.empty")
    @JsonProperty private String name;
}
