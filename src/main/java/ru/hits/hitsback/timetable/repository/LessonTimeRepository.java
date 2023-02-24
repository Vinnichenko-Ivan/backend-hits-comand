package ru.hits.hitsback.timetable.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hits.hitsback.timetable.model.entity.LessonTime;

import java.util.UUID;

public interface LessonTimeRepository extends CrudRepository<LessonTime, UUID> {


}
