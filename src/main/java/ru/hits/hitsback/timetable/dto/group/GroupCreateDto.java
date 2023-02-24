package ru.hits.hitsback.timetable.dto.group;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupCreateDto {
    @NotEmpty(message = "number.empty")
    private String number;
}
