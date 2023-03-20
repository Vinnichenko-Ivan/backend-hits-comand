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
import ru.hits.hitsback.timetable.model.dto.subject.SubjectCreateDto;
import ru.hits.hitsback.timetable.model.dto.subject.SubjectDto;
import ru.hits.hitsback.timetable.model.dto.subject.SubjectIdDto;
import ru.hits.hitsback.timetable.service.subject.SubjectService;

import java.util.List;

import static ru.hits.hitsback.timetable.configuration.UrlConstant.BASE_URL;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.SUBJECT_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = BASE_URL + SUBJECT_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController {

    private final SubjectService subjectService;

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @GetMapping
    public ResponseEntity<List<SubjectDto>> fetchSubjects() {
        return ResponseEntity.ok(subjectService.fetchSubjects());
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<SubjectIdDto> createSubject(@Valid @RequestBody SubjectCreateDto subjectCreateDto){
        return ResponseEntity.ok(subjectService.createSubject(subjectCreateDto));
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500"),
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> modifySubject(@Valid @RequestBody SubjectDto subjectDto) {
        subjectService.modifySubject(subjectDto);
        return ResponseEntity.status(200).build();
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
    public ResponseEntity<Void> deleteSubject(@PathVariable String id) {
        SubjectIdDto subjectIdDto = new SubjectIdDto(id);
        subjectService.deleteSubject(subjectIdDto);
        return ResponseEntity.status(200).build();
    }
}
