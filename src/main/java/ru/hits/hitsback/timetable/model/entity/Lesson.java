package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @ManyToOne
    private TimeSlot timeSlot;
    @ManyToOne
    private LessonGroup lessonGroup;
    public Lesson() {
    }


}
