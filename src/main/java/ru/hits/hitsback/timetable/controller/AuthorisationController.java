package ru.hits.hitsback.timetable.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.hitsback.timetable.dto.authorisation.CredentialsDto;
import ru.hits.hitsback.timetable.dto.authorisation.StudentRegisterDto;
import ru.hits.hitsback.timetable.dto.authorisation.TeacherRegisterDto;
import ru.hits.hitsback.timetable.dto.authorisation.TokenDto;
import ru.hits.hitsback.timetable.service.AuthService;

import static org.springframework.http.ResponseEntity.ok;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.AUTHORISATION_URL;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.BASE_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = BASE_URL + AUTHORISATION_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorisationController {
    private final AuthService authService;

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @PostMapping(value = "student/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signUpStudent(@Valid @RequestBody StudentRegisterDto studentRegisterDto){
        authService.registerStudent(studentRegisterDto);
        return ResponseEntity.status(200).build();
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @PostMapping(value = "teacher/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signUpTeacher(@Valid @RequestBody TeacherRegisterDto teacherRegisterDto) {
        authService.registerTeacher(teacherRegisterDto);
        return ResponseEntity.status(200).build();
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "500", content = @Content),
    })
    @PostMapping(value = "sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> signIn(@Valid @RequestBody CredentialsDto credentialsDto){
        TokenDto tokenDto = authService.singIn(credentialsDto);
        return ok(tokenDto);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "500"),
    })
    @PostMapping(value = "sign-out")
    public ResponseEntity<Void> signOut(){
        authService.singOut();
        return ResponseEntity.status(200).build();
    }

    @Operation(description = "Выходит из аккаунта со всех устройств данного пользователя", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "500"),
    })
    @PostMapping(value = "sign-out-all")
    public ResponseEntity<Void> signOutAll(){
        authService.singOutAll();
        return ResponseEntity.status(200).build();
    }
}
