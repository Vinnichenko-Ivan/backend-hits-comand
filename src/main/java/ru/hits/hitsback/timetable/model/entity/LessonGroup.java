package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="lesson_group")
@Getter
@Setter
public class LessonGroup {//123e4567-e89b-12d3-a456-426655440000,,,,,fox4080@gmail.com,111,,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToMany
    private Set<Lesson> lessons;
    private LocalTime startingDay;
    private LocalTime endDay;
    @ManyToOne
    private Subject subject;
    @ManyToOne
    private LessonType lessonType;
    @ManyToOne
    private Group group;

    public LessonGroup() {
    }


}
