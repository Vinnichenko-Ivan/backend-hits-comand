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
import ru.hits.hitsback.timetable.exception.common.NotAcceptedException;
import ru.hits.hitsback.timetable.exception.group.GroupIsAlreadyExistException;
import ru.hits.hitsback.timetable.exception.group.GroupNotFoundException;
import ru.hits.hitsback.timetable.exception.lesson.*;
import ru.hits.hitsback.timetable.exception.request.AccountNotFoundException;
import ru.hits.hitsback.timetable.exception.studyroom.StudyRoomIsAlreadyExistsException;
import ru.hits.hitsback.timetable.exception.studyroom.StudyRoomNotFoundException;
import ru.hits.hitsback.timetable.exception.subject.SubjectCopyException;
import ru.hits.hitsback.timetable.exception.subject.SubjectIsAlreadyExistException;
import ru.hits.hitsback.timetable.exception.subject.SubjectNotFoundException;
import ru.hits.hitsback.timetable.exception.teacher.TeacherIsAlreadyExistException;
import ru.hits.hitsback.timetable.exception.teacher.TeacherNotFoundException;

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
    public ResponseEntity<Map<String, Object>> handleTeacherNotFoundException() {
        return handleCustomException(HttpStatus.NOT_FOUND, "teacher.not-found");
    }

    @ExceptionHandler(GroupIsAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleGroupIsAlreadyExistException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "group.used-number");
    }
    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleGroupNotFoundException() {
        return handleCustomException(HttpStatus.NOT_FOUND, "group.not-found");
    }

    @ExceptionHandler(StudyRoomNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleStudyRoomNotFoundException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "study-room.not-found");
    }
    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<Map<String, Object>> handleIncorrectPasswordException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "password.old-incorrect");
    }

    @ExceptionHandler(UserIsAlreadyInThisGroupException.class)
    public ResponseEntity<Map<String, Object>> handleIUserIsAlreadyInThisGroupException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "user.already-in-group");
    }

    @ExceptionHandler(StudyRoomIsAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleStudyRoomAlreadyExistsException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "study-room.already-exists");
    }

    @ExceptionHandler(SamePasswordsException.class)
    public ResponseEntity<Map<String, Object>> handleNotTheSamePasswordsException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "password.same");
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAccountNotFoundException() {
        return handleCustomException(HttpStatus.NOT_FOUND, "account.not-found");
    }

    @ExceptionHandler(TeacherIsAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleTeacherIsAlreadyExistException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "teacher.already-exists");
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<Map<String, Object>> handleRuntimeException() {
//        return handleCustomException(HttpStatus.INTERNAL_SERVER_ERROR, "internal");
//    }

    @ExceptionHandler(LessonTypeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleLessonTypeNotFoundException() {
        return handleCustomException(HttpStatus.NOT_FOUND, "lesson-type.not-found");
    }

    @ExceptionHandler(LessonNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleLessonNotFoundException() {
        return handleCustomException(HttpStatus.NOT_FOUND, "lesson.not-found");
    }

    @ExceptionHandler(LessonNumberIsNotExistException.class)
    public ResponseEntity<Map<String, Object>> handleLessonNumberIsNotExistException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "lesson-number.is-not-exist");
    }

    @ExceptionHandler(LessonsHaveFinishedException.class)
    public ResponseEntity<Map<String, Object>> handleLessonsHaveFinishedException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "lesson.have-finished");
    }

    @ExceptionHandler(DateBeforeTodayException.class)
    public ResponseEntity<Map<String, Object>> handleDateBeforeTodayException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "lesson.date.before-today");
    }

    @ExceptionHandler(EndDateBeforeStartDateException.class)
    public ResponseEntity<Map<String, Object>> handleEndDateBeforeStartDateException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "lesson.end-date.before.start-date");
    }

    @ExceptionHandler(LessonsIntersectException.class)
    public ResponseEntity<Map<String, Object>> handleLessonsIntersectException(LessonsIntersectException exception) {
        Map<String, Object> message = new HashMap<>();
        message.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleWrongCredentialsException() {
        return handleCustomException(HttpStatus.UNAUTHORIZED, "wrong.credentials");
    }

    @ExceptionHandler(NotAcceptedException.class)
    public ResponseEntity<Map<String, Object>> handleNotAcceptedException() {
        return handleCustomException(HttpStatus.UNAUTHORIZED, "not.accepted");
    }

    @ExceptionHandler(GroupChangingRequestIsNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleGroupChangingRequestIsNotFoundException() {
        return handleCustomException(HttpStatus.UNAUTHORIZED, "group-changing-request.not-found");
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleSubjectNotFoundException() {
        return handleCustomException(HttpStatus.NOT_FOUND, "subject.not-found");
    }

    @ExceptionHandler(SubjectCopyException.class)
    public ResponseEntity<Map<String, Object>> handleSubjectCopyException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "subject.copy");
    }

    @ExceptionHandler(SubjectIsAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleSubjectIsAlreadyExistException() {
        return handleCustomException(HttpStatus.BAD_REQUEST, "subject.copy");
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
