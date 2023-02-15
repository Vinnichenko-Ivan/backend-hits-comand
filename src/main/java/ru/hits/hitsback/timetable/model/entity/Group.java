package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Set;

public class Group {
    @Id
    private Long id;
    private String number;
    @OneToMany
    private Set<User> user;

    public Group() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
