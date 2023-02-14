package ru.hits.hitsback.timetable.model;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Set;
import java.util.UUID;

public class PairEntity {
    @Id
    private UUID id;
    @ManyToMany
    private Set<StudentEntity> studentEntity;
    @ManyToOne
    private EducatorEntity educatorEntity;
    @ManyToOne
    private AudienceEntity audienceEntity;
    @ManyToOne
    private TypeOfСlassEntity typeOfСlass;

    public PairEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<StudentEntity> getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(Set<StudentEntity> studentEntity) {
        this.studentEntity = studentEntity;
    }

    public EducatorEntity getEducatorEntity() {
        return educatorEntity;
    }

    public void setEducatorEntity(EducatorEntity educatorEntity) {
        this.educatorEntity = educatorEntity;
    }

    public AudienceEntity getAudienceEntity() {
        return audienceEntity;
    }

    public void setAudienceEntity(AudienceEntity audienceEntity) {
        this.audienceEntity = audienceEntity;
    }

    public TypeOfСlassEntity getTypeOfСlass() {
        return typeOfСlass;
    }

    public void setTypeOfСlass(TypeOfСlassEntity typeOfСlass) {
        this.typeOfСlass = typeOfСlass;
    }
}
