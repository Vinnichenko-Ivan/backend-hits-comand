package ru.hits.hitsback.timetable.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hits.hitsback.timetable.model.entity.Group;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
@Repository
public interface GroupRepository extends CrudRepository<Group, UUID> {
    boolean existsByNumber (String number);
    List<Group> findAllByIdIn (Collection<UUID> id);
}