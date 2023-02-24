package ru.hits.hitsback.timetable.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.dto.request.*;

import java.util.List;

import static ru.hits.hitsback.timetable.configuration.UrlConstant.BASE_URL;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.REQUEST_URL;

@RestController
@RequestMapping(value = BASE_URL + REQUEST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RequestController {
    @GetMapping(value = "registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RegistrationRequestDto>> fetchRegistrationRequests(){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping(value = "group", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GroupChangingRequestDto>> fetchGroupChangingRequests(){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PutMapping(value = "registration/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AcceptDto> resolveRegistrationRequest(@PathVariable String id, @RequestParam Boolean accept){
        RegistrationRequestIdDto registrationRequestIdDto = new RegistrationRequestIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PutMapping(value = "group/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AcceptDto> resolveGroupChangingRequest(@PathVariable String id, @RequestParam Boolean accept){
        GroupChangingRequestIdDto groupChangingRequestIdDto = new GroupChangingRequestIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
