package ru.hits.hitsback.timetable.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.GroupChangingRequest;

import java.util.UUID;

public interface GroupChangingRequestRepository extends CrudRepository<GroupChangingRequest, UUID> {
}