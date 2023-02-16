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
public class Timeslot {
    @Id
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private Date date;
    @Getter @Setter
    private Short dayOfWeek;
    @Getter @Setter
    @ManyToMany
    private Set<LessonNumber> lessonNumber;
    public Timeslot() {
    }

}
