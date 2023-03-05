package ru.hits.hitsback.timetable.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.model.dto.group.GroupCreateDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.service.GroupService;

import java.util.List;

import static ru.hits.hitsback.timetable.configuration.UrlConstant.BASE_URL;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.GROUP_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = BASE_URL + GROUP_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {
    private final GroupService groupService;

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @GetMapping
    public ResponseEntity<List<GroupDto>> fetchGroups() {
        return ResponseEntity.ok(groupService.fetchGroups());
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupIdDto> createGroup(@Valid @RequestBody GroupCreateDto groupCreateDto) {
        return ResponseEntity.ok(groupService.createGroup(groupCreateDto));
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
    public ResponseEntity<Void> modifyGroup(@Valid @RequestBody GroupDto groupDto) {
        groupService.modifyGroup(groupDto);
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
    public ResponseEntity<Void> deleteGroup(@PathVariable String id) {
        GroupIdDto groupIdDto = new GroupIdDto(id);
        groupService.deleteGroup(groupIdDto);
        return ResponseEntity.ok().build();
    }
}
