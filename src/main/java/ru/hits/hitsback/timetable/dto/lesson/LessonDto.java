package ru.hits.hitsback.timetable.dto.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.group.GroupDto;
import ru.hits.hitsback.timetable.dto.schedule.LessonTimeDto;
import ru.hits.hitsback.timetable.dto.studyroom.StudyRoomDto;
import ru.hits.hitsback.timetable.dto.subject.SubjectDto;
import ru.hits.hitsback.timetable.dto.teacher.TeacherDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class LessonDto {
    @JsonProperty("id") private LessonIdDto lessonIdDto;
    @JsonProperty private Set<GroupDto> groups;
    @JsonProperty private StudyRoomDto studyRoom;
    @JsonProperty private LessonTypeDto lessonType;
    @JsonProperty private TeacherDto teacher;
    @JsonProperty private SubjectDto subject;
    @JsonProperty private LocalDate startDate;
    @JsonProperty private LocalDate endDate;
    @JsonProperty private Integer frequency;
    @JsonProperty private DayOfWeek dayOfWeek;
    @JsonProperty private LessonTimeDto lessonTime;
}
