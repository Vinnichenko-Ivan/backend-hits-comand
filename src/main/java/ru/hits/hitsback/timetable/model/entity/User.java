package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.enums.Roles;

import java.util.UUID;
@Entity
@Table(name = "user")
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String name;
    private String lastName;
    private String patronymic;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    private String email;
    private String password;
    @ManyToOne
    private Group group;

    public User() {
    }
}
