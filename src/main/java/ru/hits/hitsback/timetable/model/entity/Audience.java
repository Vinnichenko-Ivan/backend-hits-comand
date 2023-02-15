package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jdk.jfr.Enabled;

import java.util.Set;
@Entity
@Table(name = "audience")
public class Audience {
    @Id
    private Long id;
    private Long building;
    private Long floor;
    private String number;
    private String title;
    @OneToMany
    private Set<Pair> pair;

    public Audience() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Set<Pair> getPair() {
        return pair;
    }

    public void setPair(Set<Pair> pair) {
        this.pair = pair;
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
