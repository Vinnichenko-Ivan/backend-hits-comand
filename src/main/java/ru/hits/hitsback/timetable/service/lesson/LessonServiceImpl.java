package ru.hits.hitsback.timetable.service.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.mapper.LessonTypeMapper;
import ru.hits.hitsback.timetable.model.dto.lesson.*;
import ru.hits.hitsback.timetable.repository.LessonTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonTypeRepository lessonTypeRepository;
    private final LessonTypeMapper lessonTypeMapper;

    @Override
    public void deleteLesson(LessonIdDto lessonIdDto) {

    }

    @Override
    public void modifyLessonGroup(LessonGroupModifyDto lessonGroupModifyDto) {

    }

    @Override
    public void modifyLesson(LessonModifyDto lessonModifyDto) {

    }

    @Override
    public List<LessonIdDto> createLesson(LessonGroupCreateDto lessonGroupCreateDto) {
        return null;
    }

    @Override
    public List<LessonTypeDto> fetchLessonTypes() {
        return lessonTypeRepository
                .findAll()
                .stream()
                .map(lessonTypeMapper::toDto)
                .toList();
    }

    @Override
    public LessonDto fetchLesson(LessonIdDto lessonIdDto) {
        return null;
    }
}