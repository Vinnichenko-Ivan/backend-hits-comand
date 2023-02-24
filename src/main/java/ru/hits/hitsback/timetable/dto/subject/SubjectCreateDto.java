package ru.hits.hitsback.timetable.dto.subject;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectCreateDto {
    @NotEmpty
    private String name;
}
