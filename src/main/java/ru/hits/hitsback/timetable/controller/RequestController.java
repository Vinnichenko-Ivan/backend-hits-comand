package ru.hits.hitsback.timetable.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.model.dto.request.*;

import java.util.List;

import static ru.hits.hitsback.timetable.configuration.UrlConstant.BASE_URL;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.REQUEST_URL;

@RestController
@RequestMapping(value = BASE_URL + REQUEST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RequestController {
    @Operation(summary = "Получить запросы на регистрацию (админка)", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @GetMapping(value = "registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RegistrationRequestDto>> fetchRegistrationRequests(){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Operation(summary = "Получить запросы на смену группы (админка)", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @GetMapping(value = "group", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GroupChangingRequestDto>> fetchGroupChangingRequests(){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Operation(summary = "Подтвердить/отклонить запрос на регистрацию", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @PutMapping(value = "registration/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AcceptDto> resolveRegistrationRequest(@PathVariable String id, @RequestParam Boolean accept){
        RegistrationRequestIdDto registrationRequestIdDto = new RegistrationRequestIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Operation(summary = "Подтвердить/отклонить запрос на смену группы", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @PutMapping(value = "group/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AcceptDto> resolveGroupChangingRequest(@PathVariable String id, @RequestParam Boolean accept){
        GroupChangingRequestIdDto groupChangingRequestIdDto = new GroupChangingRequestIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
