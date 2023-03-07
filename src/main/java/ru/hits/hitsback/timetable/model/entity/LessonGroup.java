package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "lesson_group")
@Getter
@Setter
public class LessonGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalTime startDate;
    private LocalTime endDate;
    private Integer frequency;
    @ManyToOne
    private Subject subject;
    @ManyToOne
    private LessonType lessonType;
    @ManyToMany
    private Set<Group> group;
    @OneToMany
    private Set<Lesson> lessons;

    public LessonGroup() {
    }

}
