package ru.hits.hitsback.timetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hits.hitsback.timetable.repository.*;

@RestController
@RequestMapping("/")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    StudyRoomRepository studyRoomRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    LessonGroupRepository lessonGroupRepository;
    @Autowired
     SubjectRepository subjectRepository;
    @Autowired
     LessonTypeRepository lessonTypeRepository;
    @Autowired
     TimeSlotRepository timeSlotRepository;
    @Autowired
    private LessonTimeRepository lessonTimeRepository;


    @GetMapping
    public void fetchAccountInfo(){


    }
}
