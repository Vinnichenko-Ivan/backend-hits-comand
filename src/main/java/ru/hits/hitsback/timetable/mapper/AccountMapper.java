package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.hitsback.timetable.model.dto.account.AccountDto;
import ru.hits.hitsback.timetable.model.entity.Account;

@Mapper(componentModel = "spring", uses = {GroupMapper.class,TeacherMapper.class})
public interface AccountMapper {
    @Mapping(target = "teacherId.id", source = "teacher.id")
    AccountDto toDto(Account account);
    Account toEntity(AccountDto accountDto);
}