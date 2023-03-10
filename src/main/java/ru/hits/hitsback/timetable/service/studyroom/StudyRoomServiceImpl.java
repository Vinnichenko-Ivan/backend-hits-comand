package ru.hits.hitsback.timetable.service.studyroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.exception.studyroom.StudyRoomIsAlreadyExistsException;
import ru.hits.hitsback.timetable.mapper.StudyRoomMapper;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomCreateDto;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomDto;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomIdDto;
import ru.hits.hitsback.timetable.model.entity.StudyRoom;
import ru.hits.hitsback.timetable.repository.StudyRoomRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyRoomServiceImpl implements StudyRoomService {

    private final StudyRoomMapper studyRoomMapper;

    private final StudyRoomRepository studyRoomRepository;

    @Override
    public List<StudyRoomDto> fetchStudyRooms() {
        return studyRoomMapper.map(studyRoomRepository.findAll());
    }

    @Override
    public StudyRoomIdDto createStudyRoom(StudyRoomCreateDto studyRoomCreateDto) {
        if (studyRoomRepository.existsByBuildingNumberAndFloorAndNumber(
                studyRoomCreateDto.getBuildingNumber(),
                studyRoomCreateDto.getFloor(),
                studyRoomCreateDto.getNumber()
        )) {
            throw new StudyRoomIsAlreadyExistsException();
        }
        StudyRoom studyRoom = studyRoomMapper.map(studyRoomCreateDto);
        studyRoomRepository.save(studyRoom);
        return studyRoomMapper.map(studyRoom);
    }

    @Override
    public void modifyStudyRoom(StudyRoomDto studyRoomDto) {
        if (studyRoomRepository.existsByBuildingNumberAndFloorAndNumber(
                studyRoomDto.getBuildingNumber(),
                studyRoomDto.getFloor(),
                studyRoomDto.getNumber()
        )) {
            throw new StudyRoomIsAlreadyExistsException();
        }
        StudyRoom studyRoom = studyRoomMapper.toEntity(studyRoomDto);
        studyRoomRepository.save(studyRoom);
    }

    @Override
    public void deleteStudyRoom(StudyRoomIdDto id) {
        studyRoomRepository.deleteById(id.getId());
    }
}
