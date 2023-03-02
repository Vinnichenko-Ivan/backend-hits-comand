package ru.hits.hitsback.timetable.service.schedule;

import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.dto.group.GroupIdDto;
import ru.hits.hitsback.timetable.dto.schedule.DayScheduleDto;
import ru.hits.hitsback.timetable.dto.schedule.LessonOptionsDto;
import ru.hits.hitsback.timetable.dto.schedule.LessonTimeDto;
import ru.hits.hitsback.timetable.dto.schedule.TimeIntervalDto;
import ru.hits.hitsback.timetable.dto.teacher.TeacherIdDto;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Override
    public List<DayScheduleDto> fetchSchedule(TimeIntervalDto timeIntervalDto) {
        return null;
    }

    @Override
    public List<DayScheduleDto> fetchGroupSchedule(TimeIntervalDto timeIntervalDto, GroupIdDto groupIdDto) {
        return null;
    }

    @Override
    public List<DayScheduleDto> fetchTeacherSchedule(TimeIntervalDto timeIntervalDto, TeacherIdDto teacherIdDto) {
        return null;
    }

    @Override
    public List<DayScheduleDto> fetchScheduleWithLessonOptions(TimeIntervalDto timeIntervalDto, LessonOptionsDto lessonOptionsDto) {
        return null;
    }

    @Override
    public List<LessonTimeDto> fetchLessonTimes() {
        return null;
    }
}
