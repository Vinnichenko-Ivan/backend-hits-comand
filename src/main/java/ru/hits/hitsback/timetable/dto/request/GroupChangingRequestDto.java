package ru.hits.hitsback.timetable.dto.request;

import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.acoount.AccountDto;
import ru.hits.hitsback.timetable.dto.group.GroupDto;

@Getter
@Setter
public class GroupChangingRequestDto {
    private GroupChangingRequestIdDto id;
    private AccountDto account;
    private GroupDto group;
}
