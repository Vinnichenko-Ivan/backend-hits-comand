package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherCreateDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherDto;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherIdDto;
import ru.hits.hitsback.timetable.model.entity.Teacher;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(target = "teacherIdDto.id", source = "id")
    TeacherDto toDto(Teacher teacher);

    Teacher toEntity(TeacherDto teacherDto);

    List<TeacherDto> map(List<Teacher> teachers);

    Teacher map(TeacherCreateDto teacherCreateDto);

    TeacherIdDto map(Teacher teacher);
}
