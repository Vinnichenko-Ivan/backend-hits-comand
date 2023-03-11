package ru.hits.hitsback.timetable.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.configuration.JwtAuthentication;
import ru.hits.hitsback.timetable.exception.GroupNotFoundException;
import ru.hits.hitsback.timetable.exception.NotAcceptedException;
import ru.hits.hitsback.timetable.exception.TeacherNotFoundException;
import ru.hits.hitsback.timetable.exception.WrongCredentialsException;
import ru.hits.hitsback.timetable.model.dto.authorisation.CredentialsDto;
import ru.hits.hitsback.timetable.model.dto.authorisation.StudentRegisterDto;
import ru.hits.hitsback.timetable.model.dto.authorisation.TeacherRegisterDto;
import ru.hits.hitsback.timetable.model.dto.authorisation.TokenDto;
import ru.hits.hitsback.timetable.model.entity.Account;
import ru.hits.hitsback.timetable.model.entity.Group;
import ru.hits.hitsback.timetable.model.entity.Teacher;
import ru.hits.hitsback.timetable.model.enums.Roles;
import ru.hits.hitsback.timetable.repository.AccountRepository;
import ru.hits.hitsback.timetable.repository.GroupRepository;
import ru.hits.hitsback.timetable.repository.TeacherRepository;
import ru.hits.hitsback.timetable.service.AuthorisationService;
import ru.hits.hitsback.timetable.service.JwtService;

@Service
@RequiredArgsConstructor
public class AuthorisationServiceImpl implements AuthorisationService {

    private final TeacherRepository teacherRepository;

    private final GroupRepository groupRepository;

    private final AccountRepository accountRepository;

    private final JwtService jwtService;

    @Override
    public TokenDto singIn(CredentialsDto credentialsDto) {
        Account account = accountRepository.getAccountByEmail(credentialsDto.getEmail());
        if (account == null || !credentialsDto.getPassword().equals(account.getPassword())) {
            throw new WrongCredentialsException();
        } else if (!account.getAccepted()) {
            throw new NotAcceptedException();
        }
        return new TokenDto(jwtService.generateToken(account));
    }

    @Override
    public Account getUser() {
        try {
            return ((JwtAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccount();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void singOut() {
        jwtService.deleteAllBySecret(((JwtAuthentication) SecurityContextHolder.getContext().getAuthentication()).getFirstName());
    }

    @Override
    public void singOutAll() {
        jwtService.deleteAllByAccount(getUser());
    }

    @Override
    public void registerStudent(StudentRegisterDto studentRegisterDto) {
        Account account = new Account();
        map(studentRegisterDto, account);
        accountRepository.save(account);
    }

    @Override
    public void registerTeacher(TeacherRegisterDto teacherRegisterDto) {
        Teacher teacher = teacherRepository.findById(teacherRegisterDto.getTeacherId().getId()).orElse(null);
        if (teacher == null) {
            throw new TeacherNotFoundException();
        }

        Account account = new Account();
        account.setTeacher(teacher);
        account.setPassword(teacherRegisterDto.getPassword());
        account.setEmail(teacherRegisterDto.getEmail());
        account.setFirstName(teacher.getFirstName());
        account.setLastName(teacher.getLastName());
        account.setPatronymicName(teacher.getPatronymicName());
        account.setAccepted(false);
        account.setRole(Roles.Teacher);

        accountRepository.save(account);
    }

    private void map(StudentRegisterDto studentRegisterDto, Account account) {
        account.setPassword(studentRegisterDto.getPassword());
        account.setEmail(studentRegisterDto.getEmail());
        account.setFirstName(studentRegisterDto.getFirstName());
        account.setLastName(studentRegisterDto.getLastName());
        account.setPatronymicName(studentRegisterDto.getPatronymicName());
        account.setRole(Roles.Student);


        Group group = groupRepository.findById(studentRegisterDto.getGroupId().getId()).orElse(null);

        if (group == null) {
            throw new GroupNotFoundException();
        }

        account.setGroup(group);
        account.setAccepted(false);
    }
}
