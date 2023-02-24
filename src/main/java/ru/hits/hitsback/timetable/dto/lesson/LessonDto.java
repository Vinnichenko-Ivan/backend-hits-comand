package ru.hits.hitsback.timetable.dto.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.group.GroupDto;
import ru.hits.hitsback.timetable.dto.schedule.TimeSlotDto;
import ru.hits.hitsback.timetable.dto.studyroom.StudyRoomDto;
import ru.hits.hitsback.timetable.dto.teacher.TeacherDto;

import java.util.Set;

@Getter
@Setter
public class LessonDto {
    @JsonProperty("id")
    private LessonIdDto lessonIdDto;
    private Set<GroupDto> groups;
    private StudyRoomDto studyRoom;
    private LessonTypeDto lessonType;
    private TeacherDto teacher;
    private TimeSlotDto timeSlot;
}
