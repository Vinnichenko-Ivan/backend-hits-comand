package ru.hits.hitsback.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.Teacher;

import java.util.Set;
import java.util.UUID;

public interface TeacherRepository extends CrudRepository<Teacher, UUID> {
    Set<Teacher> findAll();
    Teacher getTeacherById (UUID id);

}