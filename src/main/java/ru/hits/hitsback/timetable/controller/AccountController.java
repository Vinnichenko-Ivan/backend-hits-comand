package ru.hits.hitsback.timetable.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.dto.acoount.AccountDto;
import ru.hits.hitsback.timetable.dto.acoount.PasswordModifyDto;
import ru.hits.hitsback.timetable.dto.group.GroupIdDto;

import java.util.List;

import static ru.hits.hitsback.timetable.configuration.UrlConstant.BASE_URL;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.PROFILE_URL;

@RestController
@RequestMapping(value = BASE_URL + PROFILE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    @GetMapping("me")
    public ResponseEntity<AccountDto> fetchAccountInfo(/*Authentication authentication*/){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> fetchAccountsInfo(){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PutMapping(value = "group", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> changeGroup(@Valid @RequestBody GroupIdDto groupIdDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PutMapping(value = "security/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordModifyDto passwordModifyDto/*, Authentication authentication*/){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}