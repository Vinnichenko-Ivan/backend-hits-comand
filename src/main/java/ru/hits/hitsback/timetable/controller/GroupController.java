package ru.hits.hitsback.timetable.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.dto.group.GroupCreateDto;
import ru.hits.hitsback.timetable.dto.group.GroupDto;
import ru.hits.hitsback.timetable.dto.group.GroupIdDto;

import java.util.List;

@RestController
@RequestMapping(value = "group", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {
    @GetMapping
    public ResponseEntity<List<GroupDto>> fetchGroups(){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupIdDto> createGroup(@Valid @RequestBody GroupCreateDto groupCreateDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> modifyGroup(@Valid @RequestBody GroupDto groupDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable String id){
        GroupIdDto groupIdDto = new GroupIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
