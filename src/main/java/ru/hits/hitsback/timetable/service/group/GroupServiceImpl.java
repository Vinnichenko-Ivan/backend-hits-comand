package ru.hits.hitsback.timetable.service.group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.exception.group.GroupIsAlreadyExistException;
import ru.hits.hitsback.timetable.exception.group.GroupNotFoundException;
import ru.hits.hitsback.timetable.model.dto.group.GroupCreateDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupDto;
import ru.hits.hitsback.timetable.model.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.model.entity.Group;
import ru.hits.hitsback.timetable.repository.GroupRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    public List<GroupDto> fetchGroups() {
        Iterable<Group> groups = groupRepository.findAll();
        return StreamSupport.stream(groups.spliterator(), false).toList().stream().map(Group::toModel).toList();
    }

    public GroupIdDto createGroup(GroupCreateDto groupCreateDto) {
        if (groupRepository.existsByNumber(groupCreateDto.getNumber())) {
            throw new GroupIsAlreadyExistException();
        }
        Group group = new Group();
        group.setNumber(groupCreateDto.getNumber());
        group = groupRepository.save(group);
        return group.getDto().getGroupIdDto();
    }

    public void modifyGroup(GroupDto groupDto) {
        Group group = groupRepository.findById(groupDto.getGroupIdDto().getId()).orElse(null);
        if (group == null) {
            throw new GroupNotFoundException();
        }
        if (groupRepository.existsByNumber(groupDto.getNumber())) {
            throw new GroupIsAlreadyExistException();
        }
        group.setNumber(groupDto.getNumber());
        groupRepository.save(group);
    }

    public void deleteGroup(GroupIdDto groupIdDto) {
        Group group = groupRepository.findById(groupIdDto.getId()).orElse(null);
        if (group == null) {
            throw new GroupNotFoundException();
        }
        groupRepository.delete(group);
    }
}
