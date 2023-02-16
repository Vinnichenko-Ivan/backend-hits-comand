package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "classType")
@Getter @Setter
public class ClassType {
    @Id
    private Integer id;
    private String name;

    @OneToMany
    private Set<Lesson> lesson;
    public ClassType() {
    }

}
