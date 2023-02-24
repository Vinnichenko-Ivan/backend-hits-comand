package ru.hits.hitsback.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.LessonType;

import java.util.UUID;

public interface LessonTypeRepository extends CrudRepository<LessonType, UUID> {


}