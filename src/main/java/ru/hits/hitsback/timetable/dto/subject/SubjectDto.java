package ru.hits.hitsback.timetable.dto.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDto {
    @JsonProperty("id")
    private SubjectIdDto subjectIdDto;
    private String name;
}
