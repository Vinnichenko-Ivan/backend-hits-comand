package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.UUID;

@Entity
@Table(name = "lesson")
@Getter @Setter
@NoArgsConstructor
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
    @ManyToOne
    @JoinColumn(name = "lessonGroup_id")
    private LessonGroup lessonGroup;
    @ManyToOne
    private LessonTime lessonTime;

    public Lesson(UUID id, StudyRoom studyRoom, Teacher teacher, Date date, DayOfWeek dayOfWeek, LessonTime lessonTime) {
        this.id = id;
        this.studyRoom = studyRoom;
        this.teacher = teacher;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.lessonTime = lessonTime;
    }
}
