package ru.hits.hitsback.timetable.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.account.AccountDto;

@Getter
@Setter
public class RegistrationRequestDto {
    @NotNull
    @JsonProperty("id") private RegistrationRequestIdDto registrationRequestIdDto;
    @NotNull
    @JsonProperty private AccountDto account;
}
