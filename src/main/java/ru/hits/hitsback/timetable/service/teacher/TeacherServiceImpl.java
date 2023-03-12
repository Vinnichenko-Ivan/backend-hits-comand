package ru.hits.hitsback.timetable.service.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.exception.teacher.TeacherIsAlreadyExistException;
import ru.hits.hitsback.timetable.mapper.TeacherMapper;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherCreateDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherIdDto;
import ru.hits.hitsback.timetable.model.entity.Teacher;
import ru.hits.hitsback.timetable.repository.TeacherRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherMapper teacherMapper;

    private final TeacherRepository teacherRepository;

    @Override
    public List<TeacherDto> fetchTeachers() {
        return teacherMapper.map(teacherRepository.findAll());
    }

    @Override
    public TeacherIdDto createTeacher(TeacherCreateDto teacherCreateDto) {
        if (teacherRepository.existsTeacherByFirstNameAndLastNameAndPatronymicName(
                teacherCreateDto.getFirstName(),
                teacherCreateDto.getLastName(),
                teacherCreateDto.getPatronymicName()
        )) {
            throw new TeacherIsAlreadyExistException();
        }
        Teacher teacher = teacherMapper.map(teacherCreateDto);
        teacher = teacherRepository.save(teacher);
        return teacherMapper.map(teacher);
    }

    @Override
    public void modifyTeacher(TeacherDto teacherDto) {
        if (teacherRepository.existsTeacherByFirstNameAndLastNameAndPatronymicName(
                teacherDto.getFirstName(),
                teacherDto.getLastName(),
                teacherDto.getPatronymicName()
        )) {
            throw new TeacherIsAlreadyExistException();
        }
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(TeacherIdDto id) {
        teacherRepository.deleteById(id.getId());
    }
}
