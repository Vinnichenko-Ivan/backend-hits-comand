package ru.hits.hitsback.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.Lesson;

import java.util.Set;
import java.util.UUID;

public interface LessonRepository extends CrudRepository<Lesson, UUID> {
    Set<Lesson> findAll();
    Lesson getLessonById (UUID id);

}