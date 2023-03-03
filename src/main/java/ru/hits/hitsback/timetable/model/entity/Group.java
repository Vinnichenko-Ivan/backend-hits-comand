package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.group.GroupDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;

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

    public GroupDto getDto() {
        GroupDto groupDto = new GroupDto();
        groupDto.setGroupIdDto(new GroupIdDto(id.toString()));
        groupDto.setNumber(number);
        return groupDto;
    }

    public static GroupDto toModel(Group groupEntity){
       GroupDto groupDto = new GroupDto();
       groupDto.setGroupIdDto(new GroupIdDto(groupEntity.getId().toString()));
       groupDto.setNumber(groupEntity.getNumber());
       return groupDto;
    }
}
