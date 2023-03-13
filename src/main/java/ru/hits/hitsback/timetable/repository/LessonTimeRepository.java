package ru.hits.hitsback.timetable.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.hits.hitsback.timetable.model.entity.LessonTime;

import java.util.Optional;
import java.util.UUID;

public interface LessonTimeRepository extends ListCrudRepository<LessonTime, UUID> {
    Optional<LessonTime> findByLessonNumber(int lessonNumber);
}
