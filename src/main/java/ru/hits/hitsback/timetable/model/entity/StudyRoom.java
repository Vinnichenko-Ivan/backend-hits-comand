package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "study_room")
@Getter @Setter

public class StudyRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Integer buildingNumber;
    private Integer floor;
    private String name;
    private Integer number;
    @OneToMany
    private Set<Lesson> lesson;

    public StudyRoom() {
    }

    public StudyRoom(Integer buildingNumber, Integer floor, String name, Integer number, Set<Lesson> lesson) {
        this.buildingNumber = buildingNumber;
        this.floor = floor;
        this.name = name;
        this.number = number;
        this.lesson = lesson;
    }
}
