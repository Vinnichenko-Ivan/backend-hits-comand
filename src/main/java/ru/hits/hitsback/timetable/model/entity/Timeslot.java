package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
@Entity
@Table(name = "timeSlot")
@Getter @Setter
public class Timeslot {
    @Id
    private Long id;
    private Date date;
    private Short dayOfWeek;
    @ManyToMany
    private Set<LessonNumber> lessonNumber;
    public Timeslot() {
    }

}
