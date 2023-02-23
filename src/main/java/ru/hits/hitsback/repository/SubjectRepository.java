package ru.hits.hitsback.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.Subject;

import java.util.Set;
import java.util.UUID;

public interface SubjectRepository extends CrudRepository<Subject, UUID> {
    Set<Subject> findAll();
    Subject getSubjectById (UUID id);

}