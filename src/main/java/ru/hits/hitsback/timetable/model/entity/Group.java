package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.dto.group.GroupDto;
import ru.hits.hitsback.timetable.dto.group.GroupIdDto;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "groups")
@Getter @Setter

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String number;
    @OneToMany
    private Set<Account> accounts;
    @OneToMany
    private Set<LessonGroup> lessonGroups;

    public Group() {
    }

    public Group(String number, Set<Account> accounts) {
        this.number = number;
        this.accounts = accounts;
    }

    public GroupDto getDto() {
        GroupDto groupDto = new GroupDto();
        groupDto.setGroupIdDto(new GroupIdDto(id.toString()));
        groupDto.setNumber(number);
        return groupDto;
    }
}
