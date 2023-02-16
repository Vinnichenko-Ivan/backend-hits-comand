package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "lessonNumber")
public class LessonNumber {
    @Id
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private Date startTime;
    @Getter @Setter
    private Date endTime;

    public LessonNumber() {
    }
}
