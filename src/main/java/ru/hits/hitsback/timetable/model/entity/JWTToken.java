package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "jwt_token")
public class JWTToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String secret;

    private Date dateCreated;

    private Date dateExp;

    @ManyToOne
    private Account account;

    @Override
    public String toString() {
        return null;
    }
}
