package ru.hits.hitsback.timetable.template.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Set;

public class AudienceEntity {
    @Id
    private Long id;
    private Long building;
    private Long floor;
    private String room;
    private String title;
    @OneToMany
    private Set<PairEntity> pairEntities;

    public AudienceEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuilding() {
        return building;
    }

    public void setBuilding(Long building) {
        this.building = building;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
