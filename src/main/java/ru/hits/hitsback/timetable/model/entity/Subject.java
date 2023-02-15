package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Id;

public class Subject {
    @Id
    private Long id;
    private String title;

    public Subject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
