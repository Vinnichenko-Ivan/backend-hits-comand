package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "lessonNumber")
@Getter @Setter
public class LessonNumber {
    @Id
    private Integer id;
    private Date startTime;
    private Date endTime;
    @OneToMany
    private Set<TimeSlot> timeSlot;
    public LessonNumber() {
    }
}
