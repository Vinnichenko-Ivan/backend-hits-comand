package ru.hits.hitsback.timetable.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.model.dto.lesson.*;
import ru.hits.hitsback.timetable.service.lesson.LessonService;

import java.util.List;

import static ru.hits.hitsback.timetable.configuration.UrlConstant.BASE_URL;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.LESSON_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = BASE_URL + LESSON_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LessonController {
    private final LessonService lessonService;

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @GetMapping(value = "{id}")
    public ResponseEntity<LessonDto> fetchLesson(@PathVariable String id) {
        LessonIdDto lessonIdDto = new LessonIdDto(id);
        return ResponseEntity.ok(lessonService.fetchLesson(lessonIdDto));
    }

    @Operation(description = "У пар есть тип. Например, лекция, практика", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @GetMapping(value = "type")
    public ResponseEntity<List<LessonTypeDto>> fetchLessonTypes() {
        return ResponseEntity.ok(lessonService.fetchLessonTypes());
    }

    @Operation(description = "frequency обозначает, раз во сколько недель. Например, frequency = 2 означает раз в 2 недели", responses = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<LessonIdDto>> createLesson(@Valid @RequestBody LessonGroupCreateDto lessonGroupCreateDto) {
        return ResponseEntity.ok(lessonService.createLesson(lessonGroupCreateDto));
    }

    @Operation(summary = "Изменить одну пару из группы", description = "Если пара стоит, например, по вторникам, то при изменении на другой день изменится состояние только выбранной пары, а не всей группы", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500"),
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> modifyLesson(@Valid @RequestBody LessonModifyDto lessonModifyDto) {
        lessonService.modifyLesson(lessonModifyDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Изменить группу пар", description = "Если пара стоит, например, по вторникам, то при изменении на другой день изменятся все пары данной группы, не считая тех, что уже прошли", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500"),
    })
    @PutMapping(value = "lesson-group", consumes = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> modifyLessonGroup(@Valid @RequestBody LessonGroupModifyDto lessonGroupModifyDto) {
        lessonService.modifyLessonGroup(lessonGroupModifyDto);
        return ResponseEntity.ok().build();
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500"),
    })
    @DeleteMapping(value = "{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> deleteLesson(@PathVariable String id) {
        LessonIdDto lessonIdDto = new LessonIdDto(id);
        lessonService.deleteLesson(lessonIdDto);
        return ResponseEntity.ok().build();
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500"),
    })
    @DeleteMapping(value = "{id}/lesson-group")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> deleteLessonGroup(@PathVariable String id) {
        LessonIdDto lessonIdDto = new LessonIdDto(id);
        lessonService.deleteLessonGroup(lessonIdDto);
        return ResponseEntity.ok().build();
    }
}
