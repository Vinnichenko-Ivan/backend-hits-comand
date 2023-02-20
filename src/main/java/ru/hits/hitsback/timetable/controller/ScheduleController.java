package ru.hits.hitsback.timetable.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.dto.schedule.LessonTimeDto;
import ru.hits.hitsback.timetable.dto.teacher.TeacherIdDto;
import ru.hits.hitsback.timetable.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.dto.schedule.DayScheduleDto;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "schedule", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScheduleController {
    @GetMapping
    public ResponseEntity<List<DayScheduleDto>> fetchSchedule(
            @RequestParam Date startDate,
            @RequestParam(required = false) Date endDate,
            Authentication authentication
    ) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping(value = "group/{id}")
    public ResponseEntity<List<DayScheduleDto>> fetchGroupSchedule(
            @RequestParam Date startDate,
            @RequestParam(required = false) Date endDate,
            @PathVariable String id
    ) {
        GroupIdDto groupIdDto = new GroupIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
    @GetMapping(value = "teacher/{id}")
    public ResponseEntity<List<DayScheduleDto>> fetchTeacherSchedule(
            @RequestParam Date startDate,
            @RequestParam(required = false) Date endDate,
            @PathVariable String id
    ) {
        TeacherIdDto teacherIdDto = new TeacherIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping(value = "staff", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DayScheduleDto>> fetchScheduleWithLessonOptions(
            @RequestParam String teacherId,
            @RequestParam String studentId,
            @RequestParam String studyRoomId,
            @RequestParam Date startDate,
            @RequestParam() Date endDate
    ) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping(value = "lesson-time")
    public ResponseEntity<List<LessonTimeDto>> fetchLessonTimes() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
