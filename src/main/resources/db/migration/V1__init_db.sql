
CREATE TABLE account
(
    id              UUID NOT NULL,
    first_name      VARCHAR(255),
    last_name       VARCHAR(255),
    patronymic_name VARCHAR(255),
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

CREATE TABLE groups_accounts
(
    group_id    UUID NOT NULL,
    accounts_id UUID NOT NULL,
    CONSTRAINT pk_groups_accounts PRIMARY KEY (group_id, accounts_id)
);

CREATE TABLE groups_lessongroups
(
    group_id        UUID NOT NULL,
    lessongroups_id UUID NOT NULL,
    CONSTRAINT pk_groups_lessongroups PRIMARY KEY (group_id, lessongroups_id)
);

CREATE TABLE lesson
(
    id             UUID NOT NULL,
    studyroom_id   UUID,
    teacher_id     UUID,
    timeslot_id    UUID,
    lessongroup_id UUID,
    CONSTRAINT pk_lesson PRIMARY KEY (id)
);

CREATE TABLE lesson_group
(
    id             UUID NOT NULL,
    starting_day   TIME WITHOUT TIME ZONE,
    end_day        TIME WITHOUT TIME ZONE,
    subject_id     UUID,
    lesson_type_id UUID,
    group_id       UUID,
    CONSTRAINT pk_lesson_group PRIMARY KEY (id)
);

CREATE TABLE lesson_group_lessons
(
    lesson_group_id UUID NOT NULL,
    lessons_id      UUID NOT NULL,
    CONSTRAINT pk_lesson_group_lessons PRIMARY KEY (lesson_group_id, lessons_id)
);

CREATE TABLE lesson_time
(
    id            UUID NOT NULL,
    start_time    TIME WITHOUT TIME ZONE,
    end_time      TIME WITHOUT TIME ZONE,
    lesson_number INTEGER,
    CONSTRAINT pk_lesson_time PRIMARY KEY (id)
);

CREATE TABLE lesson_time_time_slot
(
    lesson_time_id UUID NOT NULL,
    time_slot_id   UUID NOT NULL,
    CONSTRAINT pk_lesson_time_timeslot PRIMARY KEY (lesson_time_id, time_slot_id)
);

CREATE TABLE lesson_type
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_lesson_type PRIMARY KEY (id)
);

CREATE TABLE lesson_type_lesson_groups
(
    lesson_type_id   UUID NOT NULL,
    lesson_groups_id UUID NOT NULL,
    CONSTRAINT pk_lesson_type_lessongroups PRIMARY KEY (lesson_type_id, lesson_groups_id)
);

CREATE TABLE study_room
(
    id              UUID NOT NULL,
    building_number INTEGER,
    floor           INTEGER,
    name            VARCHAR(255),
    number          INTEGER,
    CONSTRAINT pk_study_room PRIMARY KEY (id)
);

CREATE TABLE study_room_lesson
(
    study_room_id UUID NOT NULL,
    lesson_id     UUID NOT NULL,
    CONSTRAINT pk_study_room_lesson PRIMARY KEY (study_room_id, lesson_id)
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
    lesson_group_id UUID NOT NULL,
    CONSTRAINT pk_subject_lessongroup PRIMARY KEY (subject_id, lesson_group_id)
);

CREATE TABLE teacher
(
    id              UUID NOT NULL,
    first_name      VARCHAR(255),
    last_name       VARCHAR(255),
    patronymic_name VARCHAR(255),
    account_id      UUID,
    CONSTRAINT pk_teacher PRIMARY KEY (id)
);

CREATE TABLE teacher_lessons
(
    teacher_id UUID NOT NULL,
    lessons_id UUID NOT NULL,
    CONSTRAINT pk_teacher_lessons PRIMARY KEY (teacher_id, lessons_id)
);

CREATE TABLE teacher_subjects
(
    teacher_id  UUID NOT NULL,
    subjects_id UUID NOT NULL,
    CONSTRAINT pk_teacher_subjects PRIMARY KEY (teacher_id, subjects_id)
);

CREATE TABLE time_slot
(
    id             UUID NOT NULL,
    date           TIME WITHOUT TIME ZONE,
    day_of_week    INTEGER,
    lesson_time_id UUID,
    CONSTRAINT pk_time_slot PRIMARY KEY (id)
);

CREATE TABLE time_slot_lessons
(
    time_slot_id UUID NOT NULL,
    lessons_id   UUID NOT NULL,
    CONSTRAINT pk_time_slot_lessons PRIMARY KEY (time_slot_id, lessons_id)
);

ALTER TABLE groups_accounts
    ADD CONSTRAINT uc_groups_accounts_accounts UNIQUE (accounts_id);

ALTER TABLE groups_lessongroups
    ADD CONSTRAINT uc_groups_lessongroups_lessongroups UNIQUE (lessongroups_id);

ALTER TABLE lesson_group_lessons
    ADD CONSTRAINT uc_lesson_group_lessons_lessons UNIQUE (lessons_id);

ALTER TABLE lesson_time_time_slot
    ADD CONSTRAINT uc_lesson_time_time_slot_timeslot UNIQUE (time_slot_id);

ALTER TABLE lesson_type_lesson_groups
    ADD CONSTRAINT uc_lesson_type_lesson_groups_lessongroups UNIQUE (lesson_groups_id);

ALTER TABLE study_room_lesson
    ADD CONSTRAINT uc_study_room_lesson_lesson UNIQUE (lesson_id);

ALTER TABLE subject_lesson_group
    ADD CONSTRAINT uc_subject_lesson_group_lessongroup UNIQUE (lesson_group_id);

ALTER TABLE teacher_lessons
    ADD CONSTRAINT uc_teacher_lessons_lessons UNIQUE (lessons_id);

ALTER TABLE teacher_subjects
    ADD CONSTRAINT uc_teacher_subjects_subjects UNIQUE (subjects_id);

ALTER TABLE time_slot_lessons
    ADD CONSTRAINT uc_time_slot_lessons_lessons UNIQUE (lessons_id);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE lesson_group
    ADD CONSTRAINT FK_LESSON_GROUP_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE lesson_group
    ADD CONSTRAINT FK_LESSON_GROUP_ON_LESSONTYPE FOREIGN KEY (lesson_type_id) REFERENCES lesson_type (id);

ALTER TABLE lesson_group
    ADD CONSTRAINT FK_LESSON_GROUP_ON_SUBJECT FOREIGN KEY (subject_id) REFERENCES subject (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_LESSONGROUP FOREIGN KEY (lessongroup_id) REFERENCES lesson_group (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_STUDYROOM FOREIGN KEY (studyroom_id) REFERENCES study_room (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_TIMESLOT FOREIGN KEY (timeslot_id) REFERENCES time_slot (id);

ALTER TABLE subject
    ADD CONSTRAINT FK_SUBJECT_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE teacher
    ADD CONSTRAINT FK_TEACHER_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE time_slot
    ADD CONSTRAINT FK_TIME_SLOT_ON_LESSONTIME FOREIGN KEY (lesson_time_id) REFERENCES lesson_time (id);

ALTER TABLE groups_accounts
    ADD CONSTRAINT fk_groacc_on_account FOREIGN KEY (accounts_id) REFERENCES account (id);

ALTER TABLE groups_accounts
    ADD CONSTRAINT fk_groacc_on_group FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE groups_lessongroups
    ADD CONSTRAINT fk_groles_on_group FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE groups_lessongroups
    ADD CONSTRAINT fk_groles_on_lesson_group FOREIGN KEY (lessongroups_id) REFERENCES lesson_group (id);

ALTER TABLE lesson_group_lessons
    ADD CONSTRAINT fk_lesgroles_on_lesson FOREIGN KEY (lessons_id) REFERENCES lesson (id);

ALTER TABLE lesson_group_lessons
    ADD CONSTRAINT fk_lesgroles_on_lesson_group FOREIGN KEY (lesson_group_id) REFERENCES lesson_group (id);

ALTER TABLE lesson_time_time_slot
    ADD CONSTRAINT fk_lestimtimslo_on_lesson_time FOREIGN KEY (lesson_time_id) REFERENCES lesson_time (id);

ALTER TABLE lesson_time_time_slot
    ADD CONSTRAINT fk_lestimtimslo_on_time_slot FOREIGN KEY (time_slot_id) REFERENCES time_slot (id);

ALTER TABLE lesson_type_lesson_groups
    ADD CONSTRAINT fk_lestyplesgro_on_lesson_group FOREIGN KEY (lesson_groups_id) REFERENCES lesson_group (id);

ALTER TABLE lesson_type_lesson_groups
    ADD CONSTRAINT fk_lestyplesgro_on_lesson_type FOREIGN KEY (lesson_type_id) REFERENCES lesson_type (id);

ALTER TABLE study_room_lesson
    ADD CONSTRAINT fk_sturooles_on_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE study_room_lesson
    ADD CONSTRAINT fk_sturooles_on_study_room FOREIGN KEY (study_room_id) REFERENCES study_room (id);

ALTER TABLE subject_lesson_group
    ADD CONSTRAINT fk_sublesgro_on_lesson_group FOREIGN KEY (lesson_group_id) REFERENCES lesson_group (id);

ALTER TABLE subject_lesson_group
    ADD CONSTRAINT fk_sublesgro_on_subject FOREIGN KEY (subject_id) REFERENCES subject (id);

ALTER TABLE teacher_lessons
    ADD CONSTRAINT fk_teales_on_lesson FOREIGN KEY (lessons_id) REFERENCES lesson (id);

ALTER TABLE teacher_lessons
    ADD CONSTRAINT fk_teales_on_teacher FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE teacher_subjects
    ADD CONSTRAINT fk_teasub_on_subject FOREIGN KEY (subjects_id) REFERENCES subject (id);

ALTER TABLE teacher_subjects
    ADD CONSTRAINT fk_teasub_on_teacher FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE time_slot_lessons
    ADD CONSTRAINT fk_timsloles_on_lesson FOREIGN KEY (lessons_id) REFERENCES lesson (id);

ALTER TABLE time_slot_lessons
    ADD CONSTRAINT fk_timsloles_on_time_slot FOREIGN KEY (time_slot_id) REFERENCES time_slot (id);