package ru.hits.hitsback.timetable.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.model.dto.request.*;
import ru.hits.hitsback.timetable.model.dto.request.AcceptDto;
import ru.hits.hitsback.timetable.model.dto.request.GroupChangingRequestDto;
import ru.hits.hitsback.timetable.model.dto.request.GroupChangingRequestIdDto;
import ru.hits.hitsback.timetable.model.dto.request.RegistrationRequestDto;
import ru.hits.hitsback.timetable.service.RequestService;

import java.util.List;

import static ru.hits.hitsback.timetable.configuration.UrlConstant.BASE_URL;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.REQUEST_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = BASE_URL + REQUEST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RequestController {

    private final RequestService requestService;

    @Operation(summary = "Получить запросы на регистрацию (админка)", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @GetMapping(value = "registration")
    public ResponseEntity<List<RegistrationRequestDto>> fetchRegistrationRequests() {
        return ResponseEntity.ok(requestService.fetchRegistrationRequests());
    }

    @Operation(summary = "Получить запросы на смену группы (админка)", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @GetMapping(value = "group")
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
    @PutMapping(value = "registration/{id}")
    public ResponseEntity<AcceptDto> resolveRegistrationRequest(@PathVariable String id, @RequestParam Boolean accept){
        return ResponseEntity.ok(requestService.resolveRegistrationRequest(id, accept));
    }

    @Operation(summary = "Подтвердить/отклонить запрос на смену группы", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @PutMapping(value = "group/{id}")
    public ResponseEntity<AcceptDto> resolveGroupChangingRequest(@PathVariable String id, @RequestParam Boolean accept){
        GroupChangingRequestIdDto groupChangingRequestIdDto = new GroupChangingRequestIdDto(id);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
