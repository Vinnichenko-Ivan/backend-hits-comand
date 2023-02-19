package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Entity
@Table(name = "group")
@Getter @Setter

public class Group {
    @Id
    private Long id;
    private String number;
    @OneToMany
    private Set<User> user;

    public Group() {
    }
}
