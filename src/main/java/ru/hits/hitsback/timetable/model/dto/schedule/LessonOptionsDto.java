package ru.hits.hitsback.timetable.model.dto.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomIdDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherIdDto;

import java.util.List;

@Getter
@Setter
public class LessonOptionsDto {
    @JsonProperty("teacherId") private TeacherIdDto teacherIdDto;
    @JsonProperty("studyRoomId") private StudyRoomIdDto studyRoomIdDto;
    @JsonProperty("groupIds") private List<GroupIdDto> groupIdDtos;
}
