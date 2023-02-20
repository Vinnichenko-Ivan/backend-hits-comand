package ru.hits.hitsback.timetable.dto.authorisation;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class StudentRegisterDto {
    private String firstName;
    private String lastName;
    private String patronymicName;
    private UUID groupId;
    private String email;
    private String password;
    private String confirmedPassword;
}
