package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "lesson_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonGroup {

    @Id
    private UUID id;
    @OneToMany(mappedBy = "lessonGroup",cascade = CascadeType.REMOVE)
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
