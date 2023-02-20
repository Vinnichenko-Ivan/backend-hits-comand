package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "lessonNumber")
@Getter @Setter
public class LessonTime {
    @Id
    private UUID id;

    private LocalTime startTime;
    private LocalTime endTime;
    private Integer lessonNumber;
    @OneToMany
    private Set<TimeSlot> timeSlot;
    public LessonTime() {
    }
}
