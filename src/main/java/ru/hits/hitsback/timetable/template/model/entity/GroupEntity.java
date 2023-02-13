package ru.hits.hitsback.timetable.template.model.entity;

import jakarta.persistence.Id;

public class GroupEntity {
    @Id
    private Long id;
    private String name;

    public GroupEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
