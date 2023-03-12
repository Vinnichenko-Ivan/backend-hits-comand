package ru.hits.hitsback.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hits.hitsback.timetable.model.entity.LessonType;

import java.util.UUID;

public interface LessonTypeRepository extends JpaRepository<LessonType, UUID> {


}