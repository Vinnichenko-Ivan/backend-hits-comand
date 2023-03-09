package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.hitsback.timetable.model.dto.teacher.TeacherDto;
import ru.hits.hitsback.timetable.model.entity.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(target = "teacherIdDto.id", source = "id")
    TeacherDto toDto(Teacher teacher);
    Teacher toEntity(TeacherDto teacherDto);
}
