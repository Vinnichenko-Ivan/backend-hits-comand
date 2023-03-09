package ru.hits.hitsback.timetable.model.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.group.GroupDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherIdDto;
import ru.hits.hitsback.timetable.model.enums.Roles;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class AccountDto implements Serializable {
    @NotNull
    @JsonProperty private UUID id;
    @NotNull
    @JsonProperty private String firstName;
    @NotNull
    @JsonProperty private String lastName;
    @JsonProperty private String patronymicName;
    @NotNull
    @JsonProperty private Roles role;
    @JsonProperty private GroupDto group;
    @JsonProperty private TeacherIdDto teacherId;
    @NotNull
    @JsonProperty private String email;
}
