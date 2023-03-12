package ru.hits.hitsback.timetable.model.dto.lesson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.group.GroupDto;
import ru.hits.hitsback.timetable.model.dto.schedule.LessonTimeDto;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomDto;
import ru.hits.hitsback.timetable.model.dto.subject.SubjectDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class LessonDto {
    @NotNull
    @JsonProperty("id") private LessonIdDto lessonIdDto;
    @NotNull
    @JsonProperty private Set<GroupDto> groups;
    @JsonProperty private StudyRoomDto studyRoom;
    @NotNull
    @JsonProperty private LessonTypeDto lessonType;
    @NotNull
    @JsonProperty private TeacherDto teacher;
    @NotNull
    @JsonProperty private SubjectDto subject;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty private LocalDate date;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty private LocalDate startDate;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty private LocalDate endDate;
    @NotNull
    @JsonProperty private Integer frequency;
    @NotNull
    @JsonProperty private DayOfWeek dayOfWeek;
    @NotNull
    @JsonProperty private LessonTimeDto lessonTime;
}
