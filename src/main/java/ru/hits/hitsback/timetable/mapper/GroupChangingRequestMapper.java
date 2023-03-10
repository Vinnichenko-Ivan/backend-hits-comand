package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.hitsback.timetable.model.dto.request.GroupChangingRequestDto;
import ru.hits.hitsback.timetable.model.entity.GroupChangingRequest;

@Mapper(componentModel = "spring", uses = {GroupMapper.class})
public interface GroupChangingRequestMapper {
    @Mapping(target = "groupChangingRequestIdDto.id", source = "id")
    GroupChangingRequestDto toDto (GroupChangingRequest groupChangingRequest);
}