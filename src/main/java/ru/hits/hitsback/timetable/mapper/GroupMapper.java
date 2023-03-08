package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.hitsback.timetable.model.dto.group.GroupDto;
import ru.hits.hitsback.timetable.model.entity.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    @Mapping(target = "groupIdDto.id", source = "id")
    GroupDto toDto(Group group);
    Group toEntity(GroupDto groupDto);
}
