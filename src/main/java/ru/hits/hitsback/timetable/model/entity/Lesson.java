package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "lesson")
@Getter @Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToMany
    private Set<Group> groups;
    @ManyToOne
    private StudyRoom studyRoom;
    @ManyToOne
    private LessonType lessonType;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private TimeSlot timeSlot;
    public Lesson() {
    }

    public Lesson(Set<Group> groups, StudyRoom studyRoom, LessonType lessonType, Teacher teacher, TimeSlot timeSlot) {
        this.groups = groups;
        this.studyRoom = studyRoom;
        this.lessonType = lessonType;
        this.teacher = teacher;
        this.timeSlot = timeSlot;
    }
}
