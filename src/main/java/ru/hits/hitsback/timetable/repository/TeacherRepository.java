package ru.hits.hitsback.timetable.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.Teacher;

import java.util.UUID;

public interface TeacherRepository extends CrudRepository<Teacher, UUID> {


}