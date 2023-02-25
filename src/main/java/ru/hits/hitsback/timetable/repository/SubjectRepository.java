package ru.hits.hitsback.timetable.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.Subject;

import java.util.UUID;

public interface SubjectRepository extends CrudRepository<Subject, UUID> {


}