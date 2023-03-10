package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "lesson_group")
@Getter
@Setter
public class LessonGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToMany
    private Set<Lesson> lessons;
    private Date startDate;
    private Date endDate;
    private Integer frequency;
    @ManyToOne
    private Subject subject;
    @ManyToOne
    private LessonType lessonType;
    @ManyToMany
    private List<Group> groups;
}
