package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.Set;
@Entity
@Table(name = "timeslot")
public class Timeslot {
    @Id
    private Long id;
    private Date date;
    @ManyToMany
    private Set<PairNumber> pairNumber;
    public void setPairNumber(Set<PairNumber> pairNumber) {
        this.pairNumber = pairNumber;
    }

    public Timeslot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
