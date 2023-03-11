package ru.hits.hitsback.timetable.service.account;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.model.dto.account.AccountDto;
import ru.hits.hitsback.timetable.model.dto.account.PasswordModifyDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.repository.AccountRepository;
import ru.hits.hitsback.timetable.service.authorisation.AuthorisationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AuthorisationService authorisationService;

    @Override
    public ResponseEntity<AccountDto> fetchAccountInfo() {
        return null;
    }

    @Override
    public List<AccountDto> fetchAccountsInfo() {
        return null;
    }

    @Override
    public void changeGroup(GroupIdDto groupIdDto) {

    }

    @Override
    public void changePassword(PasswordModifyDto passwordModifyDto) {

    }
}
