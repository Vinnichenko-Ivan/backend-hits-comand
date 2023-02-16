package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @Getter @Setter
    private UUID id;
    @Getter @Setter
    @ManyToMany
    private Set<Group> groups;

    @Getter @Setter
    @ManyToOne
    private StudyRoom studyRoom;
    @Getter @Setter
    @ManyToOne
    private ClassType classType;

    public Lesson() {
    }

}
