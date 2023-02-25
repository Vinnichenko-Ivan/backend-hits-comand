package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "lesson_time")
@Getter @Setter
public class LessonTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalTime startTime;
    private LocalTime endTime;
    private Integer lessonNumber;
    @OneToMany
    private Set<TimeSlot> timeSlot;
    public LessonTime() {
    }

    public LessonTime(LocalTime startTime, LocalTime endTime, Integer lessonNumber, Set<TimeSlot> timeSlot) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.lessonNumber = lessonNumber;
        this.timeSlot = timeSlot;
    }
}