package ru.hits.hitsback.timetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.hits.hitsback.timetable.exception.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorControllerAdvice extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @Autowired
    public ErrorControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(it-> {
                    if (it.getDefaultMessage() == null) return it.getDefaultMessage();

                    try {
                        return messageSource.getMessage(it.getDefaultMessage(), null, Locale.ENGLISH);
                    } catch (Exception e){
                        return it.getDefaultMessage();
                    }
                })
                .collect(Collectors.joining(", "));

        Map<String, Object> body = new HashMap<>();
        body.put("message", message);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleTeacherNotFoundException(){
        return handleCustomException(HttpStatus.NOT_FOUND, "teacher.not-found");
    }

    @ExceptionHandler(GroupIsAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleGroupIsAlreadyExistException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "group.used-number");
    }

    @ExceptionHandler(TeacherIsAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleTeacherIsAlreadyExistException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "teacher.already-exists");
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleGroupNotFoundException() {
        return handleCustomException(HttpStatus.NOT_FOUND, "group.not-found");
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException() {
        return handleCustomException(HttpStatus.INTERNAL_SERVER_ERROR, "internal");
    }

    @ExceptionHandler(StudyRoomIsAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleStudyRoomAlreadyExistsException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "study-room.already-exists");
    }

    private String getErrorMessage(String code) {
        return messageSource.getMessage(code, null, Locale.ENGLISH);
    }

    private ResponseEntity<Map<String, Object>> handleCustomException(HttpStatus status, String code) {
        Map<String, Object> message = new HashMap<>();
        message.put("message", getErrorMessage(code));
        return ResponseEntity.status(status).body(message);
    }
}
