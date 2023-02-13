package ru.hits.hitsback.timetable.template.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import ru.hits.hitsback.timetable.template.model.enums.TypeOfСlass;

import java.util.Set;

public class PairEntity {
    @Id
    private Long id;
    @ManyToMany
    private Set<StudentEntity> studentEntity;
    @ManyToOne
    private EducatorEntity educatorEntity;
    @ManyToOne
    private AudienceEntity audienceEntity;
    private TypeOfСlass typeOfСlass;

    public PairEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public TypeOfСlass getTypeOfСlass() {
        return typeOfСlass;
    }

    public void setTypeOfСlass(TypeOfСlass typeOfСlass) {
        this.typeOfСlass = typeOfСlass;
    }
}
