package ru.hits.hitsback.timetable.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.hits.hitsback.timetable.model.entity.LessonType;

import java.util.UUID;

public interface LessonTypeRepository extends ListCrudRepository<LessonType, UUID> {


}