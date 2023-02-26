package ru.hits.hitsback.timetable.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

import static ru.hits.hitsback.timetable.configuration.UrlConstant.AUTHORISATION_URL;
import static ru.hits.hitsback.timetable.configuration.UrlConstant.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + AUTHORISATION_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorisationController {
    @PostMapping(value = "student/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> signUpStudent(@Valid @RequestBody StudentRegisterDto studentRegisterDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping(value = "teacher/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> signUpTeacher(@Valid @RequestBody TeacherRegisterDto teacherRegisterDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping(value = "sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> signIn(@Valid @RequestBody CredentialsDto credentialsDto){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping(value = "sign-out")/**/
    public ResponseEntity<Void> signOut(/*Authentication authentication*/){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping(value = "sign-out-all")
    public ResponseEntity<Void> signOutAll(/*Authentication authentication*/){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
