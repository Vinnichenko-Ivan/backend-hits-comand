package ru.hits.hitsback.timetable.dto.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDto {
    @NotNull(message = "subject-id.required")
    @JsonProperty("id") private SubjectIdDto subjectIdDto;
    @NotEmpty(message = "name.empty")
    @JsonProperty private String name;
}
