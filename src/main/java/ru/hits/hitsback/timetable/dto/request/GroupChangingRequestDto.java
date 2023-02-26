package ru.hits.hitsback.timetable.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.acoount.AccountDto;
import ru.hits.hitsback.timetable.dto.group.GroupDto;

@Getter
@Setter
public class GroupChangingRequestDto {
    @JsonProperty("id")
    private GroupChangingRequestIdDto groupChangingRequestIdDto;
    private AccountDto account;
    private GroupDto group;
}
