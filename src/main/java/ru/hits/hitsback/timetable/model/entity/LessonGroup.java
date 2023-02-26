package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="lesson_group")
@Getter
@Setter
public class LessonGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToMany
    private Set<Lesson> lessons;
    private LocalTime startingDay;
    private LocalTime endDay;
    @ManyToOne
    private Subject subject;
    @ManyToOne
    private LessonType lessonType;
    @ManyToOne
    private Group group;

    public LessonGroup() {
    }


}
