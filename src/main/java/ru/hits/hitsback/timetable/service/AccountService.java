package ru.hits.hitsback.timetable.service;

import org.springframework.http.ResponseEntity;
import ru.hits.hitsback.timetable.model.dto.account.AccountDto;
import ru.hits.hitsback.timetable.model.dto.account.PasswordModifyDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;

import java.util.List;

public interface AccountService {

    public ResponseEntity<AccountDto> fetchAccountInfo();

    List<AccountDto> fetchAccountsInfo();

    void changeGroup(GroupIdDto groupIdDto);

    void changePassword(PasswordModifyDto passwordModifyDto);

}
