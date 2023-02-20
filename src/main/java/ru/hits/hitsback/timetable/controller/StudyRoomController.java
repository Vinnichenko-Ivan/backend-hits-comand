package ru.hits.hitsback.timetable.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.dto.studyroom.StudyRoomCreateDto;
import ru.hits.hitsback.timetable.dto.studyroom.StudyRoomDto;
import ru.hits.hitsback.timetable.dto.studyroom.StudyRoomIdDto;

import java.util.List;

@RestController
@RequestMapping(value = "study-room", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudyRoomController {
    @GetMapping
    public ResponseEntity<List<StudyRoomDto>> fetchStudyRooms(){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudyRoomIdDto> createStudyRoom(@Valid @RequestBody StudyRoomCreateDto studyRoomCreateDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> modifyStudyRoom(@Valid @RequestBody StudyRoomDto studyRoomDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteStudyRoom(@PathVariable String id){
        StudyRoomIdDto studyRoomIdDto = new StudyRoomIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
