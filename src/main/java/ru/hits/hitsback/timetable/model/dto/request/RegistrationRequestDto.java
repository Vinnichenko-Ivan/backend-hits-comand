package ru.hits.hitsback.timetable.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.account.AccountDto;

@Getter
@Setter
public class RegistrationRequestDto {
    @JsonProperty("id") private RegistrationRequestIdDto registrationRequestIdDto;
    @JsonProperty private AccountDto account;
}
