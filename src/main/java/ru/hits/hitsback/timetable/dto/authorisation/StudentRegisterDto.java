package ru.hits.hitsback.timetable.dto.authorisation;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.group.GroupIdDto;

@Getter
@Setter
public class StudentRegisterDto {
    @NotEmpty(message = "first-name.empty")
    private String firstName;
    @NotEmpty(message = "last-name.empty")
    private String lastName;
    @NotEmpty(message = "patronymic-name.empty")
    private String patronymicName;
    @NotNull(message = "group-id.required")
    private GroupIdDto groupId;
    @NotEmpty(message = "email.empty")
    @Email
    private String email;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).*", message = "password.too-simple")
    @Size(min = 6, max = 64, message = "password.too-long-or-short")
    private String password;
    @NotEmpty(message = "password.not-confirmed")
    private String confirmedPassword;
}
