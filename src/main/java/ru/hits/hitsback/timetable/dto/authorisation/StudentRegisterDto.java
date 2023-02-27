package ru.hits.hitsback.timetable.dto.authorisation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.group.GroupIdDto;

@Getter
@Setter
public class StudentRegisterDto {
    @NotEmpty(message = "first-name.empty")
    @JsonProperty private String firstName;
    @NotEmpty(message = "last-name.empty")
    @JsonProperty private String lastName;
    @NotEmpty(message = "patronymic-name.empty")
    @JsonProperty private String patronymicName;
    @NotNull(message = "group-id.required")
    @JsonProperty private GroupIdDto groupId;
    @NotEmpty(message = "email.empty")
    @Email
    @JsonProperty private String email;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).*", message = "password.too-simple")
    @Size(min = 6, max = 64, message = "password.too-long-or-short")
    @JsonProperty private String password;
    @NotEmpty(message = "password.not-confirmed")
    @JsonProperty private String confirmedPassword;
}
