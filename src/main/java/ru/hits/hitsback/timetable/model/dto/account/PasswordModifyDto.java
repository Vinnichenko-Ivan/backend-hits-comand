package ru.hits.hitsback.timetable.model.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordModifyDto {
    @NotEmpty(message = "old-password.empty")
    @JsonProperty private String oldPassword;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).*", message = "password.too-simple")
    @Size(min = 6, max = 64, message = "password.too-long-or-short")
    @NotEmpty(message = "new-password.empty")
    @JsonProperty private String newPassword;
}
