package ru.hits.hitsback.timetable.dto.schedule;

import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.dto.studyroom.StudyRoomIdDto;
import ru.hits.hitsback.timetable.dto.teacher.TeacherIdDto;

import java.util.List;

@Getter
@Setter
public class LessonOptionsDto {
    private TeacherIdDto teacherIdDto;
    private List<GroupIdDto> groupIdDto;
    private StudyRoomIdDto studyRoomIdDto;

    public LessonOptionsDto(String teacherId, List<String> groupIds, String studyRoomId) {
        teacherIdDto = new TeacherIdDto(teacherId);
        groupIdDto = groupIds.stream().map(GroupIdDto::new).toList();
        studyRoomIdDto = new StudyRoomIdDto(studyRoomId);
    }
}
