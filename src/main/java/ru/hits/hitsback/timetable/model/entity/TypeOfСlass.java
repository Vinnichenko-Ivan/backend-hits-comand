package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;
@Entity
@Table(name = "typeOfClass")
public class TypeOfСlass {
    @Id
    private Integer id;
    private String title;

    @OneToMany
    private Set<Pair> pairEntities;
    public TypeOfСlass() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
