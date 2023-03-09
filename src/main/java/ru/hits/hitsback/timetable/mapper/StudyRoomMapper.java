package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomDto;
import ru.hits.hitsback.timetable.model.entity.StudyRoom;

@Mapper(componentModel = "spring")
public interface StudyRoomMapper {
    @Mapping(target = "studyRoomIdDto.id", source = "id")
    StudyRoomDto toDto(StudyRoom studyRoom);
    StudyRoom toEntity(StudyRoomDto studyRoomDto);
}
