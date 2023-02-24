package ru.hits.hitsback.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.Lesson;

import java.util.UUID;

public interface LessonRepository extends CrudRepository<Lesson, UUID> {

}