package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.enums.Roles;

import java.util.UUID;
@Entity
@Table(name = "account")
@Getter @Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;
    private String lastName;
    private String patronymicName;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    private String email;
    private String password;
    @ManyToOne
    private Group group;
    @OneToOne
    private Teacher teacher;
    public Account() {
    }

    public Account(String firstName, String lastName, String patronymicName, Roles roles, String email, String password, Group group, Teacher teacher) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.roles = roles;
        this.email = email;
        this.password = password;
        this.group = group;
        this.teacher = teacher;
    }
}
