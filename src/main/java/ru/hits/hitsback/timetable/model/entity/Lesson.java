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
    private UUID id;
    @ManyToMany
    private Set<Group> groups;

    @ManyToOne
    private StudyRoom studyRoom;
    @ManyToOne
    private ClassType classType;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private TimeSlot timeSlot;

    public Lesson() {
    }

}
