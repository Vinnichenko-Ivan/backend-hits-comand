package ru.hits.hitsback.timetable.service;

import ru.hits.hitsback.timetable.model.dto.subject.SubjectCreateDto;
import ru.hits.hitsback.timetable.model.dto.subject.SubjectDto;
import ru.hits.hitsback.timetable.model.dto.subject.SubjectIdDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> fetchSubjects();

    SubjectIdDto createSubject(SubjectCreateDto subjectCreateDto);

    void modifySubject(SubjectDto subjectDto);

    void deleteSubject(SubjectIdDto subjectIdDto);
}
