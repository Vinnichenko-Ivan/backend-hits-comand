package ru.hits.hitsback.timetable.service.account;

import ru.hits.hitsback.timetable.model.dto.account.AccountDto;
import ru.hits.hitsback.timetable.model.dto.account.PasswordModifyDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.model.entity.Account;

import java.util.List;

public interface AccountService {

    AccountDto fetchAccountInfo(Account account);

    List<AccountDto> fetchAccountsInfo();

    void changeGroup(GroupIdDto groupIdDto, Account account);

    void changePassword(PasswordModifyDto passwordModifyDto,Account account);

}
