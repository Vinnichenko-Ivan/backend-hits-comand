package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "lesson")
public class Pair {
    @Id
    private UUID id;
    @ManyToMany
    private Set<Group> groups;

    @ManyToOne
    private Studyroom studyroom;
    @ManyToOne
    private TypeOfСlass classType;

    public Pair() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Studyroom getStudyroom() {
        return studyroom;
    }

    public void setStudyroom(Studyroom studyroom) {
        this.studyroom = studyroom;
    }

    public TypeOfСlass getClassType() {
        return classType;
    }

    public void setClassType(TypeOfСlass classType) {
        this.classType = classType;
    }
}
