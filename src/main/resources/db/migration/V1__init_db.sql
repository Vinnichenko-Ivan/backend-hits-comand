
CREATE TABLE account
(
    id              UUID NOT NULL,
    firstName      VARCHAR(255),
    lastName       VARCHAR(255),
    patronymicName VARCHAR(255),
    roles           VARCHAR(255),
    email           VARCHAR(255),
    password        VARCHAR(255),
    group_id        UUID,
    teacher_id      UUID,
    CONSTRAINT pk_account PRIMARY KEY (id)
);

CREATE TABLE groups
(
    id     UUID NOT NULL,
    number VARCHAR(255),
    CONSTRAINT pk_groups PRIMARY KEY (id)
);

CREATE TABLE groups_account
(
    group_id    UUID NOT NULL,
    accounts_id UUID NOT NULL,
    CONSTRAINT pk_groups_accounts PRIMARY KEY (group_id, accounts_id)
);

CREATE TABLE groups_lesson_group
(
    group_id         UUID NOT NULL,
    lessonGroups_id UUID NOT NULL,
    CONSTRAINT pk_groups_lessongroups PRIMARY KEY (group_id, lessonGroups_id)
);

CREATE TABLE lesson
(
    id              UUID NOT NULL,
    studyRoom_id   UUID,
    timeSlot_id    UUID,
    lessonGroup_id UUID,
    CONSTRAINT pk_lesson PRIMARY KEY (id)
);

CREATE TABLE lesson_group
(
    id             UUID NOT NULL,
    startingDay   TIME WITHOUT TIME ZONE,
    endDay        TIME WITHOUT TIME ZONE,
    subject_id     UUID,
    lessonType_id UUID,
    group_id       UUID,
    CONSTRAINT pk_lesson_group PRIMARY KEY (id)
);

CREATE TABLE lesson_group_lesson
(
    lessonGroup_id UUID NOT NULL,
    lessons_id      UUID NOT NULL,
    CONSTRAINT pk_lesson_group_lessons PRIMARY KEY (lessonGroup_id, lessons_id)
);

CREATE TABLE lesson_time
(
    id            UUID NOT NULL,
    startTime    TIME WITHOUT TIME ZONE,
    endTime      TIME WITHOUT TIME ZONE,
    lessonNumber INTEGER,
    CONSTRAINT pk_lessonTime PRIMARY KEY (id)
);

CREATE TABLE lesson_time_time_slot
(
    lessonTime_id UUID NOT NULL,
    timeSlot_id   UUID NOT NULL,
    CONSTRAINT pk_lesson_time_timeslot PRIMARY KEY (lessonTime_id, timeSlot_id)
);

CREATE TABLE lesson_type
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_lesson_type PRIMARY KEY (id)
);

CREATE TABLE lesson_type_lesson_group
(
    lessonType_id   UUID NOT NULL,
    lessonGroups_id UUID NOT NULL,
    CONSTRAINT pk_lesson_type_lessongroups PRIMARY KEY (lessonType_id, lessonGroups_id)
);

CREATE TABLE study_room
(
    id              UUID NOT NULL,
    buildingNumber INTEGER,
    floor           INTEGER,
    name            VARCHAR(255),
    number          INTEGER,
    CONSTRAINT pk_studyRoom PRIMARY KEY (id)
);

CREATE TABLE study_room_lesson
(
    studyRoom_id UUID NOT NULL,
    lesson_id     UUID NOT NULL,
    CONSTRAINT pk_study_room_lesson PRIMARY KEY (studyRoom_id, lesson_id)
);

CREATE TABLE subject
(
    id         UUID NOT NULL,
    name       VARCHAR(255),
    teacher_id UUID,
    CONSTRAINT pk_subject PRIMARY KEY (id)
);

CREATE TABLE subject_lesson_group
(
    subject_id      UUID NOT NULL,
    lessonGroup_id UUID NOT NULL,
    CONSTRAINT pk_subject_lessongroup PRIMARY KEY (subject_id, lessonGroup_id)
);

CREATE TABLE teacher
(
    id              UUID NOT NULL,
    firstName      VARCHAR(255),
    lastName       VARCHAR(255),
    patronymicName VARCHAR(255),
    account_id      UUID,
    CONSTRAINT pk_teacher PRIMARY KEY (id)
);


CREATE TABLE teacher_subject
(
    teacher_id  UUID NOT NULL,
    subjects_id UUID NOT NULL,
    CONSTRAINT pk_teacher_subjects PRIMARY KEY (teacher_id, subjects_id)
);

CREATE TABLE time_slot
(
    id             UUID NOT NULL,
    date           TIME WITHOUT TIME ZONE,
    dayOfWeek    INTEGER,
    lessonTime_id UUID,
    CONSTRAINT pk_timeSlot PRIMARY KEY (id)
);

CREATE TABLE time_slot_lesson
(
    timeSlot_id UUID NOT NULL,
    lessons_id   UUID NOT NULL,
    CONSTRAINT pk_time_slot_lessons PRIMARY KEY (timeSlot_id, lessons_id)
);

ALTER TABLE groups_account
    ADD CONSTRAINT uc_groups_accounts_accounts UNIQUE (accounts_id);

ALTER TABLE groups_lesson_group
    ADD CONSTRAINT uc_groups_lesson_groups_lessongroups UNIQUE (lessonGroups_id);

ALTER TABLE lesson_group_lesson
    ADD CONSTRAINT uc_lesson_group_lessons_lessons UNIQUE (lessons_id);

ALTER TABLE lesson_time_time_slot
    ADD CONSTRAINT uc_lesson_time_time_slot_timeslot UNIQUE (timeSlot_id);

ALTER TABLE lesson_type_lesson_group
    ADD CONSTRAINT uc_lesson_type_lesson_groups_lessongroups UNIQUE (lessonGroups_id);

ALTER TABLE study_room_lesson
    ADD CONSTRAINT uc_study_room_lesson_lesson UNIQUE (lesson_id);

ALTER TABLE subject_lesson_group
    ADD CONSTRAINT uc_subject_lesson_group_lessongroup UNIQUE (lessonGroup_id);

ALTER TABLE teacher_subject
    ADD CONSTRAINT uc_teacher_subjects_subjects UNIQUE (subjects_id);

ALTER TABLE time_slot_lesson
    ADD CONSTRAINT uc_time_slot_lessons_lessons UNIQUE (lessons_id);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE lesson_group
    ADD CONSTRAINT FK_LESSON_GROUP_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE lesson_group
    ADD CONSTRAINT FK_LESSON_GROUP_ON_LESSONTYPE FOREIGN KEY (lessonType_id) REFERENCES lesson_type (id);

ALTER TABLE lesson_group
    ADD CONSTRAINT FK_LESSON_GROUP_ON_SUBJECT FOREIGN KEY (subject_id) REFERENCES subject (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_LESSONGROUP FOREIGN KEY (lessonGroup_id) REFERENCES lesson_group (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_STUDYROOM FOREIGN KEY (studyRoom_id) REFERENCES study_room (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_TIMESLOT FOREIGN KEY (timeSlot_id) REFERENCES time_slot (id);

ALTER TABLE subject
    ADD CONSTRAINT FK_SUBJECT_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE teacher
    ADD CONSTRAINT FK_TEACHER_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE time_slot
    ADD CONSTRAINT FK_TIME_SLOT_ON_LESSONTIME FOREIGN KEY (lessonTime_id) REFERENCES lesson_time (id);

ALTER TABLE groups_account
    ADD CONSTRAINT fk_groacc_on_account FOREIGN KEY (accounts_id) REFERENCES account (id);

ALTER TABLE groups_account
    ADD CONSTRAINT fk_groacc_on_group FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE groups_lesson_group
    ADD CONSTRAINT fk_grolesgro_on_group FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE groups_lesson_group
    ADD CONSTRAINT fk_grolesgro_on_lesson_group FOREIGN KEY (lessonGroups_id) REFERENCES lesson_group (id);

ALTER TABLE lesson_group_lesson
    ADD CONSTRAINT fk_lesgroles_on_lesson FOREIGN KEY (lessons_id) REFERENCES lesson (id);

ALTER TABLE lesson_group_lesson
    ADD CONSTRAINT fk_lesgroles_on_lesson_group FOREIGN KEY (lessonGroup_id) REFERENCES lesson_group (id);

ALTER TABLE lesson_time_time_slot
    ADD CONSTRAINT fk_lestimtimslo_on_lesson_time FOREIGN KEY (lessonTime_id) REFERENCES lesson_time (id);

ALTER TABLE lesson_time_time_slot
    ADD CONSTRAINT fk_lestimtimslo_on_time_slot FOREIGN KEY (timeSlot_id) REFERENCES time_slot (id);

ALTER TABLE lesson_type_lesson_group
    ADD CONSTRAINT fk_lestyplesgro_on_lesson_group FOREIGN KEY (lessonGroups_id) REFERENCES lesson_group (id);

ALTER TABLE lesson_type_lesson_group
    ADD CONSTRAINT fk_lestyplesgro_on_lesson_type FOREIGN KEY (lessonType_id) REFERENCES lesson_type (id);

ALTER TABLE study_room_lesson
    ADD CONSTRAINT fk_sturooles_on_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE study_room_lesson
    ADD CONSTRAINT fk_sturooles_on_study_room FOREIGN KEY (studyRoom_id) REFERENCES study_room (id);

ALTER TABLE subject_lesson_group
    ADD CONSTRAINT fk_sublesgro_on_lesson_group FOREIGN KEY (lessonGroup_id) REFERENCES lesson_group (id);

ALTER TABLE subject_lesson_group
    ADD CONSTRAINT fk_sublesgro_on_subject FOREIGN KEY (subject_id) REFERENCES subject (id);

ALTER TABLE teacher_subject
    ADD CONSTRAINT fk_teasub_on_subject FOREIGN KEY (subjects_id) REFERENCES subject (id);

ALTER TABLE teacher_subject
    ADD CONSTRAINT fk_teasub_on_teacher FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE time_slot_lesson
    ADD CONSTRAINT fk_timsloles_on_lesson FOREIGN KEY (lessons_id) REFERENCES lesson (id);

ALTER TABLE time_slot_lesson
    ADD CONSTRAINT fk_timsloles_on_time_slot FOREIGN KEY (timeSlot_id) REFERENCES time_slot (id);
