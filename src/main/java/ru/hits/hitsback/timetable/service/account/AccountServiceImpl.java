package ru.hits.hitsback.timetable.service.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.exception.GroupNotFoundException;
import ru.hits.hitsback.timetable.exception.IncorrectPasswordException;
import ru.hits.hitsback.timetable.mapper.AccountMapper;
import ru.hits.hitsback.timetable.model.dto.account.AccountDto;
import ru.hits.hitsback.timetable.model.dto.account.PasswordModifyDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.model.entity.Account;
import ru.hits.hitsback.timetable.model.entity.Group;
import ru.hits.hitsback.timetable.model.entity.GroupChangingRequest;
import ru.hits.hitsback.timetable.repository.AccountRepository;
import ru.hits.hitsback.timetable.repository.GroupChangingRequestRepository;
import ru.hits.hitsback.timetable.repository.GroupRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final GroupChangingRequestRepository groupChangingRequestRepository;
    private final GroupRepository groupRepository;
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Override
    public AccountDto fetchAccountInfo(Account account) {
        return accountMapper.toDTO(account);
    }

    @Override
    public List<AccountDto> fetchAccountsInfo() {
        return accountRepository.findAll().stream().map(Account::toDto).collect(Collectors.toList());
    }

    @Override
    public void changeGroup(GroupIdDto groupIdDto, Account account) {
        GroupChangingRequest groupChangingRequest = new GroupChangingRequest();
        Group group = groupRepository.findById(groupIdDto.getId()).orElse(null);

        if(group==null){
            throw new GroupNotFoundException();
        }

        groupChangingRequest.setAccount(account);
        groupChangingRequest.setGroup(group);
        groupChangingRequestRepository.save(groupChangingRequest);

    }

    @Override
    public void changePassword(PasswordModifyDto passwordModifyDto, Account account) {

        if(!Objects.equals(account.getPassword(), passwordModifyDto.getOldPassword())){
            throw new IncorrectPasswordException();
        }

        account.setPassword(passwordModifyDto.getNewPassword());
        accountRepository.save(account);
    }
}
