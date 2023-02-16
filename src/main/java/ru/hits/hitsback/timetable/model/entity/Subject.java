package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String title;

    public Subject() {
    }
}
