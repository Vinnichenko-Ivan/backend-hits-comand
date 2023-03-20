package ru.hits.hitsback.timetable.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.account.AccountDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupDto;

@Getter
@Setter
public class GroupChangingRequestDto {
    @NotNull
    @JsonProperty("id") private GroupChangingRequestIdDto groupChangingRequestIdDto;
    @NotNull
    @JsonProperty private AccountDto account;
    @NotNull
    @JsonProperty private GroupDto group;
}
