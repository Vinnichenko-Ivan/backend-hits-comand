package ru.hits.hitsback.timetable.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.dto.subject.SubjectCreateDto;
import ru.hits.hitsback.timetable.dto.subject.SubjectDto;
import ru.hits.hitsback.timetable.dto.subject.SubjectIdDto;

import java.util.List;

@RestController
@RequestMapping(value = "subject", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController {
    @GetMapping
    public ResponseEntity<List<SubjectDto>> fetchSubjects(){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubjectIdDto> createSubject(@Valid @RequestBody SubjectCreateDto subjectCreateDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> modifySubject(@Valid @RequestBody SubjectDto subjectDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String id){
        SubjectIdDto subjectIdDto = new SubjectIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
