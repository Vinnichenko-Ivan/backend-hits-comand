package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "studyRoom")
@Getter @Setter

public class StudyRoom {
    @Id
    private Long id;
    private Integer buildingNumber;
    private Short floor;
    private String name;
    private Integer number;
//    @OneToMany
//    private Set<Lesson> lesson;

    public StudyRoom() {
    }
}
