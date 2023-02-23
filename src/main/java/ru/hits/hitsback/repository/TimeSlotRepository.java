package ru.hits.hitsback.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.TimeSlot;

import java.util.Set;
import java.util.UUID;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, UUID> {
    Set<TimeSlot> findAll();
    TimeSlot getTimeSlotsById (UUID id);

}