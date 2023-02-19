package ru.hits.hitsback.timetable.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.dto.lesson.LessonDto;
import ru.hits.hitsback.timetable.dto.teacher.TeacherIdDto;
import ru.hits.hitsback.timetable.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.dto.schedule.DayScheduleDto;
import ru.hits.hitsback.timetable.dto.schedule.LessonTimesDto;
import ru.hits.hitsback.timetable.dto.schedule.WeekScheduleDto;

import java.util.List;

@RestController
@RequestMapping(value = "schedule", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScheduleController {
    @GetMapping(value = "week")
    public ResponseEntity<WeekScheduleDto> fetchWeekSchedule(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping(value = "day")
    public ResponseEntity<DayScheduleDto> fetchDaySchedule(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping(value = "group/{id}/day")
    public ResponseEntity<DayScheduleDto> fetchGroupDaySchedule(@PathVariable String id) {
        GroupIdDto groupIdDto = new GroupIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping(value = "group/{id}/week")
    public ResponseEntity<WeekScheduleDto> fetchGroupWeekSchedule(@PathVariable String id) {
        GroupIdDto groupIdDto = new GroupIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping(value = "teacher/{id}/day")
    public ResponseEntity<DayScheduleDto> fetchTeacherDaySchedule(@PathVariable String id) {
        TeacherIdDto teacherIdDto = new TeacherIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping(value = "teacher/{id}/week")
    public ResponseEntity<WeekScheduleDto> fetchTeacherWeekSchedule(@PathVariable String id) {
        TeacherIdDto teacherIdDto = new TeacherIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping(value = "staff/week", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LessonDto>> fetchWeekScheduleWithLessonOptions(
            @RequestParam String teacherId,
            @RequestParam String studentId,
            @RequestParam String studyRoomId
    ) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping(value = "lesson-time")
    public ResponseEntity<LessonTimesDto> fetchLessonTimes() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
