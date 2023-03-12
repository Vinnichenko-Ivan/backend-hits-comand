package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.UUID;

@Entity
@Table(name = "lesson")
@Getter @Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    private StudyRoom studyRoom;
    @ManyToOne
    private Teacher teacher;
    private Date date;
    private DayOfWeek dayOfWeek;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private LessonGroup lessonGroup;
    @ManyToOne
    private LessonTime lessonTime;
    public Lesson() {
    }

}
