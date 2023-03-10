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
import ru.hits.hitsback.timetable.model.dto.account.AccountDto;
import ru.hits.hitsback.timetable.model.dto.account.PasswordModifyDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.model.entity.Account;
import ru.hits.hitsback.timetable.service.AuthorisationService;
import ru.hits.hitsback.timetable.service.account.AccountService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.BASE_URL;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.PROFILE_URL;

@RestController
@RequestMapping(value = BASE_URL + PROFILE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AccountController {
    private final AuthorisationService authorisationService;
    private final AccountService accountService;

    @Operation(summary = "Получить информацию о своём профиле", description = "Не путать с .+/profile запросом", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @GetMapping("me")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<AccountDto> fetchAccountInfo(){
        Account account = authorisationService.getUser();
        return ok(accountService.fetchAccountInfo(account));
    }

    @Operation(summary = "Получить всех пользователей-студентов и -преподавателей", description = "Этот запрос доступен только админке", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<AccountDto>> fetchAccountsInfo(){
        return ok(accountService.fetchAccountsInfo());
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500"),
    })
    @PutMapping(value = "group", consumes = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> changeGroup(@Valid @RequestBody GroupIdDto groupIdDto){
        Account account = authorisationService.getUser();
        accountService.changeGroup(groupIdDto,account);
        return ok().build();
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "500"),
    })
    @PutMapping(value = "security/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordModifyDto passwordModifyDto){
        Account account = authorisationService.getUser();
        accountService.changePassword(passwordModifyDto, account);
        return ResponseEntity.ok().build();
    }
}