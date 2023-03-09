package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomCreateDto;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomDto;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomIdDto;
import ru.hits.hitsback.timetable.model.entity.StudyRoom;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyRoomMapper {

    @Mapping(target = "studyRoomIdDto.id", source = "id")
    StudyRoomDto toDto(StudyRoom studyRoom);

    List<StudyRoomDto> map(List<StudyRoom> studyRooms);

    StudyRoom map(StudyRoomCreateDto studyRoomCreateDto);

    StudyRoomIdDto map(StudyRoom studyRoom);

    StudyRoom map(StudyRoomDto studyRoomDto);
}
