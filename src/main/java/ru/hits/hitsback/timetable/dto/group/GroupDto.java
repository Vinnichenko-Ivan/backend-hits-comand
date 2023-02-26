package ru.hits.hitsback.timetable.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDto {
    @JsonProperty("id")
    private GroupIdDto groupIdDto;
    private String number;
}
