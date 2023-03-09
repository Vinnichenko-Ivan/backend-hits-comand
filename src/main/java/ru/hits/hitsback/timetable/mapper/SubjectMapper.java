package ru.hits.hitsback.timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hits.hitsback.timetable.model.dto.subject.SubjectCreateDto;
import ru.hits.hitsback.timetable.model.dto.subject.SubjectDto;
import ru.hits.hitsback.timetable.model.entity.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(target = "subjectIdDto.id", source = "id")
    SubjectDto toDto(Subject subjects);
    Subject toEntity(SubjectCreateDto subjectCreateDto);

}
