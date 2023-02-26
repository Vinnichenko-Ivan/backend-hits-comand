package ru.hits.hitsback.timetable.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.LessonGroup;

import java.util.UUID;

public interface LessonGroupRepository extends CrudRepository<LessonGroup, UUID> {
}