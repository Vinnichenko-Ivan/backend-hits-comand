package ru.hits.hitsback.timetable.model.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.group.GroupDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherDto;
import ru.hits.hitsback.timetable.model.enums.Roles;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class AccountDto implements Serializable {
    @JsonProperty private UUID id;
    @JsonProperty private String firstName;
    @JsonProperty private String lastName;
    @JsonProperty private String patronymicName;
    @JsonProperty private Roles role;
    @JsonProperty private GroupDto group;
    @JsonProperty private TeacherDto teacher;
    @JsonProperty private String email;
}
