package ru.hits.hitsback.timetable.dto.request;

import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.acoount.AccountDto;

@Getter
@Setter
public class RegistrationRequestDto {
    private RegistrationRequestIdDto id;
    private AccountDto account;
}
