package ru.hits.hitsback.timetable.template.model.entity;

import jakarta.persistence.Id;

public class SubjectEntity {
    @Id
    private String id;
    private String title;

    public SubjectEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
