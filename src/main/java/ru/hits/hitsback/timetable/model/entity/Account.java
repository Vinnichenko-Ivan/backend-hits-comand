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
    private Roles role;
    private String email;
    private String password;
    @ManyToOne
    private Group group;
    @OneToOne (mappedBy = "account", cascade = CascadeType.REMOVE)
    GroupChangingRequest groupChangingRequest;
    @OneToOne
    private Teacher teacher;
    private Boolean accepted;

    public Account() {

    }
}
