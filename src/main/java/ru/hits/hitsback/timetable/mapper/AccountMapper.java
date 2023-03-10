package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.hitsback.timetable.model.dto.account.AccountDto;
import ru.hits.hitsback.timetable.model.entity.Account;

@Mapper(componentModel = "spring", uses = {GroupMapper.class,TeacherMapper.class})
public interface AccountMapper {
    @Mapping(target = "group", source = "group")
    @Mapping(target = "teacherId", source = "teacher")
    AccountDto toDTO(Account account);
    Account toEntity(AccountDto accountDto);
}