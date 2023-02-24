package ru.hits.hitsback.timetable.dto.lesson;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.dto.schedule.TimeSlotIdDto;
import ru.hits.hitsback.timetable.dto.studyroom.StudyRoomIdDto;
import ru.hits.hitsback.timetable.dto.teacher.TeacherIdDto;

import java.util.Set;

@Getter
@Setter
public class LessonCreateDto {
    @NotNull(message = "group-id.required")
    private Set<GroupIdDto> groupIds;
    @NotNull(message = "study-room-id.required")
    private StudyRoomIdDto studyRoomId;
    @NotNull(message = "lesson-id.required")
    private LessonTypeIdDto lessonTypeId;
    @NotNull(message = "teacher-id.required")
    private TeacherIdDto teacherId;
    @NotNull(message = "time-slot-id.required")
    private TimeSlotIdDto timeSlotId;
}
