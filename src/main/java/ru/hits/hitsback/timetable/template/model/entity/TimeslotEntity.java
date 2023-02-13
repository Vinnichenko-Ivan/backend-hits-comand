package ru.hits.hitsback.timetable.template.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import javax.xml.crypto.Data;
import java.util.Set;

public class TimeslotEntity {
    @Id
    private Long id;
    private Data data;
    @ManyToMany
    private Set<PairNumber> pairNumber;
    public void setPairNumber(Set<PairNumber> pairNumber) {
        this.pairNumber = pairNumber;
    }

    public TimeslotEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
