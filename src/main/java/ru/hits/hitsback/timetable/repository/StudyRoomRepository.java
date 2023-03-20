package ru.hits.hitsback.timetable.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.StudyRoom;

import java.util.List;
import java.util.UUID;

public interface StudyRoomRepository extends CrudRepository<StudyRoom, UUID> {

    List<StudyRoom> findAll();

    void deleteById(UUID uuid);

    Boolean existsByBuildingNumberAndFloorAndNumber(Integer buildingNumber, Integer floor, String number);
}