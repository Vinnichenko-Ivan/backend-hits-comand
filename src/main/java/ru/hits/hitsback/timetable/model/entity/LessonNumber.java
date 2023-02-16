package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "lessonNumber")
@Getter @Setter
public class LessonNumber {
    @Id
    private Integer id;
    private Date startTime;
    private Date endTime;

    public LessonNumber() {
    }
}
