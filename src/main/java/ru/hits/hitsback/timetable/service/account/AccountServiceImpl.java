package ru.hits.hitsback.timetable.service.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.exception.GroupChangingRequestIsAlreadyExistException;
import ru.hits.hitsback.timetable.exception.IncorrectPasswordException;
import ru.hits.hitsback.timetable.exception.SamePasswordsException;
import ru.hits.hitsback.timetable.exception.UserIsAlreadyInThisGroupException;
import ru.hits.hitsback.timetable.exception.group.GroupNotFoundException;
import ru.hits.hitsback.timetable.exception.request.AccountNotFoundException;
import ru.hits.hitsback.timetable.exception.teacher.TeacherNotFoundException;
import ru.hits.hitsback.timetable.mapper.AccountMapper;
import ru.hits.hitsback.timetable.model.dto.account.AccountDto;
import ru.hits.hitsback.timetable.model.dto.account.AccountModifyDto;
import ru.hits.hitsback.timetable.model.dto.account.PasswordModifyDto;
import ru.hits.hitsback.timetable.model.dto.account.RegisterAccountDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.model.entity.Account;
import ru.hits.hitsback.timetable.model.entity.Group;
import ru.hits.hitsback.timetable.model.entity.GroupChangingRequest;
import ru.hits.hitsback.timetable.model.entity.Teacher;
import ru.hits.hitsback.timetable.repository.AccountRepository;
import ru.hits.hitsback.timetable.repository.GroupChangingRequestRepository;
import ru.hits.hitsback.timetable.repository.GroupRepository;
import ru.hits.hitsback.timetable.repository.TeacherRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final GroupChangingRequestRepository groupChangingRequestRepository;
    private final GroupRepository groupRepository;
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;


    private final TeacherRepository teacherRepository;

    @Override
    public AccountDto fetchAccountInfo(Account account) {
        return accountMapper.toDto(account);
    }

    @Override
    public List<AccountDto> fetchAccountsInfo() {
        return accountRepository.findAll().stream().map(accountMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void changeGroup(GroupIdDto groupIdDto, Account account) {
        GroupChangingRequest groupChangingRequest = new GroupChangingRequest();
        Group group = groupRepository.findById(groupIdDto.getId()).orElse(null);

        if(group==null){
            throw new GroupNotFoundException();
        }

        if(Objects.equals(groupIdDto.getId(),account.getGroup().getId())){
            throw new UserIsAlreadyInThisGroupException();
        }
        GroupChangingRequest oldGroupChangingRequest= groupChangingRequestRepository.getGroupChangingRequestByAccount_Id(account.getId());
        if(oldGroupChangingRequest!=null){
            if(Objects.equals(oldGroupChangingRequest.getGroup().getId(),groupIdDto.getId())){
                throw new GroupChangingRequestIsAlreadyExistException();
            }
            oldGroupChangingRequest.setGroup(group);
            groupChangingRequestRepository.save(oldGroupChangingRequest);
        }
        else{
        groupChangingRequest.setAccount(account);
        groupChangingRequest.setGroup(group);
        groupChangingRequestRepository.save(groupChangingRequest);
        }


    }

    @Override
    public void changePassword(PasswordModifyDto passwordModifyDto, Account account) {

        if(!Objects.equals(account.getPassword(), passwordModifyDto.getOldPassword())){
            throw new IncorrectPasswordException();
        }

        if (Objects.equals(passwordModifyDto.getOldPassword(), passwordModifyDto.getNewPassword())) {
            throw new SamePasswordsException();
        }

        account.setPassword(passwordModifyDto.getNewPassword());
        accountRepository.save(account);
    }

    @Override
    public UUID registerAccount(RegisterAccountDto registerAccountDto) {
        Account account = accountMapper.map(registerAccountDto);

        if (registerAccountDto.getGroupId() != null) {
            Group group = groupRepository.findById(registerAccountDto.getGroupId().getId()).orElse(null);
            if (group == null) {
                throw new GroupNotFoundException();
            }
            account.setGroup(group);
        }

        if (registerAccountDto.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(registerAccountDto.getTeacherId().getId()).orElse(null);
            if (teacher == null) {
                throw new TeacherNotFoundException();
            }
            account.setTeacher(teacher);
        }

        account = accountRepository.save(account);
        return account.getId();
    }

    @Override
    public void modifyAccount(AccountModifyDto accountModifyDto) {
        if (accountRepository.existsById(accountModifyDto.getId())) {
            Account account = accountMapper.map(accountModifyDto);
            account.setId(accountModifyDto.getId());

            if (accountModifyDto.getGroupId() != null) {
                Group group = groupRepository.findById(accountModifyDto.getGroupId().getId()).orElse(null);
                if (group == null) {
                    throw new GroupNotFoundException();
                }
                account.setGroup(group);
            }

            if (accountModifyDto.getTeacherId() != null) {
                Teacher teacher = teacherRepository.findById(accountModifyDto.getTeacherId().getId()).orElse(null);
                if (teacher == null) {
                    throw new TeacherNotFoundException();
                }
                account.setTeacher(teacher);
            }

            accountRepository.save(account);
        } else {
            throw new AccountNotFoundException();
        }
    }

    @Override
    public void deleteAccount(UUID id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
        } else {
            throw new AccountNotFoundException();
        }
    }
}
