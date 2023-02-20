package ru.hits.hitsback.timetable.dto.acoount;

import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.group.GroupDto;
import ru.hits.hitsback.timetable.dto.teacher.TeacherDto;
import ru.hits.hitsback.timetable.model.enums.Roles;

import java.util.UUID;

@Getter
@Setter
public class AccountDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String patronymicName;
    private Roles role;
    private GroupDto group;
    private TeacherDto teacher;
    private String email;
}
