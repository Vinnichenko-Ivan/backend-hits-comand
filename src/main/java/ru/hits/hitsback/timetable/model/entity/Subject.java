package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "subject")
@Getter @Setter

public class Subject {
    @Id
    private UUID id;
    private String name;

    public Subject() {
    }
}
