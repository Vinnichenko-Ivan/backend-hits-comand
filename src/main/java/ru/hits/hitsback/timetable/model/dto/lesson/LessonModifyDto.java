package ru.hits.hitsback.timetable.model.dto.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonModifyDto extends LessonCreateDto {
    @NotNull(message = "lesson-id.required")
    @JsonProperty("id") private LessonIdDto lessonIdDto;
}
