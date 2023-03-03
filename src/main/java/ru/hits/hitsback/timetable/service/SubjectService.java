package ru.hits.hitsback.timetable.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.exception.SubjectCopyException;
import ru.hits.hitsback.timetable.exception.SubjectIsAlreadyExistException;
import ru.hits.hitsback.timetable.mapper.SubjectMapper;
import ru.hits.hitsback.timetable.model.dto.subject.SubjectCreateDto;
import ru.hits.hitsback.timetable.model.dto.subject.SubjectDto;
import ru.hits.hitsback.timetable.model.dto.subject.SubjectIdDto;
import ru.hits.hitsback.timetable.model.entity.Subject;
import ru.hits.hitsback.timetable.repository.SubjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectMapper subjectMapper;

    private final SubjectRepository subjectRepository;

    public List<SubjectDto> fetchSubjects() {
        return subjectRepository.findAll().stream().map(subjectMapper::map).collect(Collectors.toList());
    }

    public SubjectIdDto createSubject(SubjectCreateDto subjectCreateDto) {
        if (subjectRepository.existsByName(subjectCreateDto.getName())) {
            throw new SubjectCopyException();
        } else {
            subjectRepository.save(subjectMapper.map(subjectCreateDto));
        }
        return new SubjectIdDto(subjectRepository.getSubjectByName(subjectCreateDto.getName()).getId().toString());
    }

    public void modifySubject(SubjectDto subjectDto) {
        Subject subject = subjectRepository.findById(subjectDto.getSubjectIdDto().getId()).orElse(null);
        if (subject == null) {
            throw new SubjectIsAlreadyExistException();
        } else if (subjectRepository.existsByName(subjectDto.getName())) {
            throw new SubjectCopyException();
        } else {
            subject.setName(subjectDto.getName());
            subjectRepository.save(subject);
        }
    }

    public void deleteSubject(SubjectIdDto subjectIdDto) {
        Subject subject = subjectRepository.findById(subjectIdDto.getId()).orElse(null);
        if (subject == null) {
            throw new SubjectIsAlreadyExistException();
        } else {
            subjectRepository.delete(subject);
        }
    }
}
