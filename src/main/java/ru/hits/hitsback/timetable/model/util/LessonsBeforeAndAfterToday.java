package ru.hits.hitsback.timetable.model.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.entity.Lesson;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LessonsBeforeAndAfterToday {
    private List<Lesson> lessonsBeforeToday;
    private List<Lesson> lessonsAfterToday;
}
