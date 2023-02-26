package ru.hits.hitsback.timetable.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hits.hitsback.timetable.dto.authorisation.CredentialsDto;
import ru.hits.hitsback.timetable.dto.authorisation.StudentRegisterDto;
import ru.hits.hitsback.timetable.dto.authorisation.TeacherRegisterDto;
import ru.hits.hitsback.timetable.dto.authorisation.TokenDto;
import ru.hits.hitsback.timetable.exception.TeacherNotFoundException;
import ru.hits.hitsback.timetable.exception.UnauthorizedException;
import ru.hits.hitsback.timetable.service.AuthService;

import static ru.hits.hitsback.timetable.configuration.UrlConstant.AUTHORISATION_URL;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.BASE_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = BASE_URL + AUTHORISATION_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorisationController {

    private final AuthService authService;

    @PostMapping(value = "student/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> signUpStudent(@Valid @RequestBody StudentRegisterDto studentRegisterDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
    @ExceptionHandler(UnauthorizedException.class)

    @PostMapping(value = "teacher/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> signUpTeacher(@Valid @RequestBody TeacherRegisterDto teacherRegisterDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping(value = "sign-in")
    public ResponseEntity<TokenDto> signIn(@Valid @RequestBody CredentialsDto credentialsDto){
        TokenDto tokenDto = authService.singIn(credentialsDto);
        return ResponseEntity.ok(tokenDto);
//        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping(value = "sign-out")
    public ResponseEntity<Void> signOut(Authentication authentication){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping(value = "sign-out-all")
    public ResponseEntity<Void> signOutAll(Authentication authentication){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
