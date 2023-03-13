package ru.hits.hitsback.timetable.model.enums;


import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    Admin("Admin"),
    Student("Student"),
    Teacher("Teacher"),
    ScheduleWriter("ScheduleWriter");

    private final String val;

    Roles(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getAuthority() {
        return val;
    }
}
