package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table (name = "group_changing_request")
@Getter @Setter
public class GroupChangingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToOne
    private Account account;
    @OneToOne
    private Group group;

    public GroupChangingRequest() {
    }

}
