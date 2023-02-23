package ru.hits.hitsback.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.LessonTime;

import java.util.Set;
import java.util.UUID;

public interface LessonTimeRepository extends CrudRepository<LessonTime, UUID> {
    Set<LessonTime> findAll();
    LessonTime getLessonTimeById(UUID id);


}
