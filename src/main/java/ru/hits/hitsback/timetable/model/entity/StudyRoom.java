package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "studyRoom")
@Getter @Setter

public class StudyRoom {
    @Id
    private UUID id;
    private Integer buildingNumber;
    private Integer floor;
    private String name;
    private Integer number;
    @OneToMany
    private Set<Lesson> lesson;

    public StudyRoom() {
    }
}
