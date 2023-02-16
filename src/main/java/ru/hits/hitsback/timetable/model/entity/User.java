package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.enums.Roles;

import java.util.UUID;
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private UUID id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String patronymic;
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private Roles roles;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;
    @ManyToOne
    @Getter @Setter
    private Group group;


    public User() {
    }
}
