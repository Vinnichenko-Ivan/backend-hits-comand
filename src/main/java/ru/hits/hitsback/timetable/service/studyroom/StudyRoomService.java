package ru.hits.hitsback.timetable.service.studyroom;

import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomCreateDto;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomDto;
import ru.hits.hitsback.timetable.model.dto.studyroom.StudyRoomIdDto;

import java.util.List;

public interface StudyRoomService {

    List<StudyRoomDto> fetchStudyRooms();

    StudyRoomIdDto createStudyRoom(StudyRoomCreateDto studyRoomCreateDto);

    void modifyStudyRoom(StudyRoomDto studyRoomDto);

    void deleteStudyRoom(StudyRoomIdDto id);

}
