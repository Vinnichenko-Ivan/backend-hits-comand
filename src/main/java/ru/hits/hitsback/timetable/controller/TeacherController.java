package ru.hits.hitsback.timetable.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.dto.teacher.TeacherCreateDto;
import ru.hits.hitsback.timetable.dto.teacher.TeacherDto;
import ru.hits.hitsback.timetable.dto.teacher.TeacherIdDto;
import ru.hits.hitsback.timetable.dto.teacher.TeacherModifyDto;

import java.util.List;

@RestController
@RequestMapping(value = "teacher", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeacherController {
    @GetMapping
    public ResponseEntity<List<TeacherDto>> fetchTeachers(){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherIdDto> createTeacher(@Valid @RequestBody TeacherCreateDto teacherCreateDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> modifyTeacher(@Valid @RequestBody TeacherModifyDto teacherModifyDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String id){
        TeacherIdDto teacherIdDto = new TeacherIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
