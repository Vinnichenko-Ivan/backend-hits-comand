package ru.hits.hitsback.timetable.service;

import ru.hits.hitsback.timetable.exception.UnauthorizedException;
import ru.hits.hitsback.timetable.model.dto.authorisation.CredentialsDto;
import ru.hits.hitsback.timetable.model.dto.authorisation.StudentRegisterDto;
import ru.hits.hitsback.timetable.model.dto.authorisation.TeacherRegisterDto;
import ru.hits.hitsback.timetable.model.dto.authorisation.TokenDto;
import ru.hits.hitsback.timetable.model.entity.Account;

public interface AuthorisationService {
    TokenDto singIn(CredentialsDto credentialsDto) throws UnauthorizedException;

    Account getUser();

    void singOut();

    void singOutAll();

    void registerStudent(StudentRegisterDto studentRegisterDto);

    void registerTeacher(TeacherRegisterDto teacherRegisterDto);
}
