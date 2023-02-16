package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subject")
@Getter @Setter

public class Subject {
    @Id
    private Long id;
    private String title;

    public Subject() {
    }
}
