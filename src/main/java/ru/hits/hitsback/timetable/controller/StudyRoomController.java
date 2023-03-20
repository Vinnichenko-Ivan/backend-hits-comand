package ru.hits.hitsback.timetable.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomCreateDto;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomDto;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomIdDto;
import ru.hits.hitsback.timetable.service.studyroom.StudyRoomService;

import java.util.List;

import static ru.hits.hitsback.timetable.configuration.UrlConstant.BASE_URL;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.STUDY_ROOM_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = BASE_URL + STUDY_ROOM_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class StudyRoomController {

    private final StudyRoomService studyRoomService;

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @GetMapping
    public ResponseEntity<List<StudyRoomDto>> fetchStudyRooms() {
        return ResponseEntity.ok(studyRoomService.fetchStudyRooms());
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
    public ResponseEntity<StudyRoomIdDto> createStudyRoom(@Valid @RequestBody StudyRoomCreateDto studyRoomCreateDto){
        return ResponseEntity.ok(studyRoomService.createStudyRoom(studyRoomCreateDto));
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
    public ResponseEntity<Void> modifyStudyRoom(@Valid @RequestBody StudyRoomDto studyRoomDto) {
        studyRoomService.modifyStudyRoom(studyRoomDto);
        return ResponseEntity.status(HttpStatus.OK).build();
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
    public ResponseEntity<Void> deleteStudyRoom(@PathVariable String id) {
        StudyRoomIdDto studyRoomIdDto = new StudyRoomIdDto(id);
        studyRoomService.deleteStudyRoom(studyRoomIdDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
