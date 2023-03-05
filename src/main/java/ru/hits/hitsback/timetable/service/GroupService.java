package ru.hits.hitsback.timetable.service;

import ru.hits.hitsback.timetable.model.dto.group.GroupCreateDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;

import java.util.List;
public interface GroupService {
    List<GroupDto> fetchGroups();
    void modifyGroup(GroupDto groupDto);
    GroupIdDto createGroup(GroupCreateDto groupCreateDto);
    void deleteGroup(GroupIdDto groupIdDto);
}
