package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Set;
import java.util.UUID;

public class Pair {
    @Id
    private UUID id;
    @ManyToMany
    private Set<User> users;

    @ManyToOne
    private Audience audienceEntity;
    @ManyToOne
    private TypeOfСlass typeOfСlass;

    public Pair() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Audience getAudienceEntity() {
        return audienceEntity;
    }

    public void setAudienceEntity(Audience audienceEntity) {
        this.audienceEntity = audienceEntity;
    }

    public TypeOfСlass getTypeOfСlass() {
        return typeOfСlass;
    }

    public void setTypeOfСlass(TypeOfСlass typeOfСlass) {
        this.typeOfСlass = typeOfСlass;
    }
}
