package ru.hits.hitsback.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hits.hitsback.timetable.model.entity.Group;
import ru.hits.hitsback.timetable.model.entity.Lesson;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {
    @Query(value = "select l " +
            "from Lesson l " +
            "where l.date between :startDate and :endDate " +
            "   and (l.teacher.id = :teacherId " +
            "   or l.studyRoom.id = :studyRoomId " +
            "   or exists (select g from l.lessonGroup.groups g where g in :groups))")
    List<Lesson> findAllByLessonOptions(Date startDate, Date endDate, UUID teacherId, UUID studyRoomId, List<Group> groups);
}