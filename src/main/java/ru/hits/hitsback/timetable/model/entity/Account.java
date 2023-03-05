package ru.hits.hitsback.timetable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hits.hitsback.timetable.model.dto.account.AccountDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherIdDto;
import ru.hits.hitsback.timetable.model.enums.Roles;

import java.util.UUID;
@Entity
@Table(name = "account")
@Getter @Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
    private String patronymicName;
    @Enumerated(EnumType.STRING)
    private Roles role;
    private String email;
    private String password;
    @ManyToOne
    private Group group;
    @OneToOne
    private Teacher teacher;
    @OneToOne
    private GroupChangingRequest groupChangingRequest;

    private Boolean accepted;

    public Account() {

    }


    public AccountDto toDto() {
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(email);
        accountDto.setFirstName(firstName);
        accountDto.setLastName(lastName);
        accountDto.setPatronymicName(patronymicName);
        accountDto.setGroup(group.getDto());
        accountDto.setTeacherId(teacher != null ? new TeacherIdDto(teacher.getId().toString()) : null);
        accountDto.setId(id);
        accountDto.setRole(role);
        return accountDto;
    }
}
