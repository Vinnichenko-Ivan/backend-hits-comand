package ru.hits.hitsback.timetable.dto.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.dto.studyroom.StudyRoomIdDto;
import ru.hits.hitsback.timetable.dto.subject.SubjectIdDto;
import ru.hits.hitsback.timetable.dto.teacher.TeacherIdDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class LessonCreateDto {
    @NotNull(message = "group-id.required")
    @JsonProperty private Set<GroupIdDto> groupIds;
    @NotNull(message = "study-room-id.required")
    @JsonProperty private StudyRoomIdDto studyRoomId;
    @NotNull(message = "lesson-type-id.required")
    @JsonProperty private LessonTypeIdDto lessonTypeId;
    @NotNull(message = "teacher-id.required")
    @JsonProperty private TeacherIdDto teacherId;
    @NotNull(message = "subject-id.required")
    @JsonProperty private SubjectIdDto subjectId;
    @NotNull(message = "start-date.required")
    @JsonProperty private LocalDate startDate;
    @NotNull(message = "end-date.required")
    @JsonProperty private LocalDate endDate;
    @NotNull(message = "day-of-week.required")
    @JsonProperty private DayOfWeek dayOfWeek;
    @NotNull(message = "lesson-number.required")
    @Min(value = 1, message = "lesson number must be at least 1")
    @Max(value = 8, message = "lesson number must be less than 8")
    @JsonProperty private Integer lessonNumber;
    @NotNull(message = "frequency.required")
    @Min(value = 1, message = "frequency.positive")
    @JsonProperty private Integer frequency;
}
