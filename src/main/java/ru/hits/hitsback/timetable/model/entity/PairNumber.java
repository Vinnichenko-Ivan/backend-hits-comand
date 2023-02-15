package ru.hits.hitsback.timetable.model.entity;

import javax.xml.crypto.Data;

public class PairNumber {
    private Integer id;
    private Data startTime;
    private Data endTime;

    public PairNumber() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Data getStartTime() {
        return startTime;
    }

    public void setStartTime(Data startTime) {
        this.startTime = startTime;
    }

    public Data getEndTime() {
        return endTime;
    }

    public void setEndTime(Data endTime) {
        this.endTime = endTime;
    }
}
