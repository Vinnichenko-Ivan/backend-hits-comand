package ru.hits.hitsback.timetable.exception.lesson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LessonsIntersectException extends RuntimeException {
    private String message;
}
