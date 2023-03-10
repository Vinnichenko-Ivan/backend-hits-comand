package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import ru.hits.hitsback.timetable.model.dto.account.AccountDto;
import ru.hits.hitsback.timetable.model.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toDTO(Account account);
    Account toEntity(AccountDto accountDto);
}