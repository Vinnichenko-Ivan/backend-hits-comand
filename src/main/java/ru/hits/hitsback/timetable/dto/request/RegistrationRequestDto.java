package ru.hits.hitsback.timetable.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.acoount.AccountDto;

@Getter
@Setter
public class RegistrationRequestDto {
    @JsonProperty("id")
    private RegistrationRequestIdDto registrationRequestIdDto;
    private AccountDto account;
}
