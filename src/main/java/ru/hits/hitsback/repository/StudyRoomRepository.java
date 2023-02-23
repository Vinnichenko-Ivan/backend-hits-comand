package ru.hits.hitsback.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.StudyRoom;

import java.util.Set;
import java.util.UUID;

public interface StudyRoomRepository extends CrudRepository<StudyRoom, UUID> {
    Set<StudyRoom> findAll();
    StudyRoom getStudyRoomById (UUID id);

}