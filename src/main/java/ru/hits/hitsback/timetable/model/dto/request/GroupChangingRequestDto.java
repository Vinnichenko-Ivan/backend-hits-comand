package ru.hits.hitsback.timetable.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.account.AccountDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupDto;

@Getter
@Setter
public class GroupChangingRequestDto {
    @JsonProperty("id") private GroupChangingRequestIdDto groupChangingRequestIdDto;
    @JsonProperty private AccountDto account;
    @JsonProperty private GroupDto group;
}
