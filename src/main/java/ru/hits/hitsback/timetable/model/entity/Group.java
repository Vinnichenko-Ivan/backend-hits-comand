package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "group")
@Getter @Setter

public class Group {
    @Id
    private UUID id;
    private String number;
    @OneToMany
    private Set<User> users;

    public Group() {
    }
}
