package ru.hits.hitsback.timetable.template.model.entity;

import javax.xml.crypto.Data;

public class PairNumber {
    private String id;
    private Data startTime;
    private Data endTime;

    public PairNumber() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
