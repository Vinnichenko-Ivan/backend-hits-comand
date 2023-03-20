package ru.hits.hitsback.timetable.service.account;

import ru.hits.hitsback.timetable.model.dto.account.AccountDto;
import ru.hits.hitsback.timetable.model.dto.account.AccountModifyDto;
import ru.hits.hitsback.timetable.model.dto.account.PasswordModifyDto;
import ru.hits.hitsback.timetable.model.dto.account.RegisterAccountDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.model.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountDto fetchAccountInfo(Account account);

    List<AccountDto> fetchAccountsInfo();

    void changeGroup(GroupIdDto groupIdDto, Account account);

    void changePassword(PasswordModifyDto passwordModifyDto, Account account);

    UUID registerAccount(RegisterAccountDto registerAccountDto);

    void modifyAccount(AccountModifyDto accountModifyDto);

    void deleteAccount(UUID id);
}