package ru.hits.hitsback.timetable.template.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Set;

public class EducatorEntity {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String patronymic;
    private String email;
    private String password;
    @OneToMany
    private Set<PairEntity> pairEntities;

    public EducatorEntity() {
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
