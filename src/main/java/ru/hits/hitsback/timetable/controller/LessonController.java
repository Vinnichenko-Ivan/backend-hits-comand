package ru.hits.hitsback.timetable.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.dto.lesson.LessonCreateDto;
import ru.hits.hitsback.timetable.dto.lesson.LessonDto;
import ru.hits.hitsback.timetable.dto.lesson.LessonIdDto;

import static ru.hits.hitsback.timetable.configuration.UrlConstant.BASE_URL;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.LESSON_URL;

@RestController
@RequestMapping(value = BASE_URL + LESSON_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LessonController {
    @GetMapping(value = "{id}")
    public ResponseEntity<LessonDto> fetchLesson(@PathVariable String id){
        LessonIdDto lessonIdDto = new LessonIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LessonIdDto> createLesson(@Valid @RequestBody LessonCreateDto lessonCreateDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> modifyLesson(@Valid @RequestBody LessonDto lessonDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable String id){
        LessonIdDto lessonIdDto = new LessonIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
