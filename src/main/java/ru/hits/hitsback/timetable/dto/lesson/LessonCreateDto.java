package ru.hits.hitsback.timetable.dto.lesson;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private Set<GroupIdDto> groupIds;
    @NotEmpty
    private StudyRoomIdDto studyRoomIdDto;
    @NotEmpty
    private LessonTypeIdDto lessonTypeId;
    @NotEmpty
    private TeacherIdDto teacherId;
    @NotEmpty
    private TimeSlotIdDto timeSlotId;
}
