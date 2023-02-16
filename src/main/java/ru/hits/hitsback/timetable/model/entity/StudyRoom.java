package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "studyRoom")
public class StudyRoom {
    @Id
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private Long buildingNumber;
    @Getter @Setter
    private Short floor;
    @Getter @Setter
    private String name;
//    @OneToMany
//    private Set<Lesson> lesson;

    public StudyRoom() {
    }
}
