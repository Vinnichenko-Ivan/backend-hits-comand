package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "subject")
@Getter @Setter

public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @ManyToOne
    private Teacher teacher;
    @OneToMany
    private Set<LessonGroup> lessonGroup;

    public Subject() {
    }

}
