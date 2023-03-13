package ru.hits.hitsback.timetable.service.teacher;

import ru.hits.hitsback.timetable.model.dto.teacher.TeacherCreateDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherIdDto;

import java.util.List;

public interface TeacherService {

    List<TeacherDto> fetchTeachers();

    TeacherIdDto createTeacher(TeacherCreateDto teacherCreateDto);

    void modifyTeacher(TeacherDto teacherDto);

    void deleteTeacher(TeacherIdDto id);
}
