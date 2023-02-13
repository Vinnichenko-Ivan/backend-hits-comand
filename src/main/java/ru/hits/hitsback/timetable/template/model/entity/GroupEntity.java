package ru.hits.hitsback.timetable.template.model.entity;

import jakarta.persistence.Id;

public class GroupEntity {
    @Id
    private String id;
    private String name;

    public GroupEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
