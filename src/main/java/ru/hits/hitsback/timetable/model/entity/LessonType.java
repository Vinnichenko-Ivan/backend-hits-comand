package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "lesson_type")
@Getter @Setter
public class LessonType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    @OneToMany
    private Set<Lesson> lesson;
    public LessonType() {
    }

    public LessonType(String name, Set<Lesson> lesson) {
        this.name = name;
        this.lesson = lesson;
    }
}
