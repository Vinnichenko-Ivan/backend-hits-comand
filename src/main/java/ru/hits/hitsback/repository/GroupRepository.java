package ru.hits.hitsback.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hits.hitsback.timetable.model.entity.Group;

import java.util.Set;
import java.util.UUID;
@Repository
public interface GroupRepository extends CrudRepository<Group, UUID> {
    Set<Group> findAll();
    Group getGroupById (UUID id);

}