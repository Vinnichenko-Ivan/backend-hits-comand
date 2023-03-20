package ru.hits.hitsback.timetable.model.dto.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomIdDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherIdDto;

import java.time.LocalDate;

@Getter
@Setter
public class LessonModifyDto {
    @JsonProperty("id") private LessonIdDto lessonIdDto;
    @JsonProperty private StudyRoomIdDto studyRoomId;
    @NotNull(message = "teacher-id.required")
    @JsonProperty private TeacherIdDto teacherId;
    @NotNull(message = "start-date.required")
    @JsonProperty private LocalDate startDate;
    @NotNull(message = "lesson-number.required")
    @Min(value = 1, message = "lesson number must be at least 1")
    @Max(value = 8, message = "lesson number must be less than 8")
    @JsonProperty private Integer lessonNumber;
}
