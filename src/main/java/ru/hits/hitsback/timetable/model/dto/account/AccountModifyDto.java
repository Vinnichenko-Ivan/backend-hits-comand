package ru.hits.hitsback.timetable.model.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherIdDto;
import ru.hits.hitsback.timetable.model.enums.Roles;

import java.util.UUID;

@Data
public class AccountModifyDto {
    @NotNull
    @JsonProperty
    private UUID id;
    @NotNull
    @JsonProperty
    private String firstName;
    @NotNull
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String patronymicName;
    @NotNull
    @JsonProperty
    private Roles role;
    @JsonProperty
    private GroupIdDto groupId;
    @JsonProperty
    private TeacherIdDto teacherId;
    @NotNull
    @JsonProperty
    private String email;
    @NotNull
    @JsonProperty
    private String password;
}
