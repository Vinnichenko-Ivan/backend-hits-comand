
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

CREATE TABLE lesson_type
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_lessontype PRIMARY KEY (id)
);

CREATE TABLE lesson_type_lesson
(
    lessonType_id UUID NOT NULL,
    lesson_id      UUID NOT NULL,
    CONSTRAINT pk_lessontype_lesson PRIMARY KEY (lessonType_id, lesson_id)
);

CREATE TABLE groups
(
    id     UUID NOT NULL,
    number VARCHAR(255),
    CONSTRAINT pk_group PRIMARY KEY (id)
);

CREATE TABLE group_accounts
(
    group_id    UUID NOT NULL,
    accounts_id UUID NOT NULL,
    CONSTRAINT pk_group_accounts PRIMARY KEY (group_id, accounts_id)
);

CREATE TABLE lesson
(
    id             UUID NOT NULL,
    studyRoom_id  UUID,
    lessonType_id UUID,
    teacher_id     UUID,
    timeSlot_id   UUID,
    CONSTRAINT pk_lesson PRIMARY KEY (id)
);

CREATE TABLE lesson_groups
(
    lesson_id UUID NOT NULL,
    groups_id UUID NOT NULL,
    CONSTRAINT pk_lesson_groups PRIMARY KEY (lesson_id, groups_id)
);

CREATE TABLE lesson_time
(
    id            UUID NOT NULL,
    startTime    TIME WITHOUT TIME ZONE,
    endTime      TIME WITHOUT TIME ZONE,
    lessonNumber INTEGER,
    CONSTRAINT pk_lessontime PRIMARY KEY (id)
);

CREATE TABLE lesson_time_time_slot
(
    lessonTime_id UUID NOT NULL,
    timeSlot_id   UUID NOT NULL,
    CONSTRAINT pk_lessontime_timeslot PRIMARY KEY (lessonTime_id, timeSlot_id)
);

CREATE TABLE study_room
(
    id              UUID NOT NULL,
    buildingNumber INTEGER,
    floor           INTEGER,
    name            VARCHAR(255),
    number          INTEGER,
    CONSTRAINT pk_studyroom PRIMARY KEY (id)
);

CREATE TABLE study_room_lesson
(
    studyRoom_id UUID NOT NULL,
    lesson_id     UUID NOT NULL,
    CONSTRAINT pk_studyroom_lesson PRIMARY KEY (studyRoom_id, lesson_id)
);

CREATE TABLE subject
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_subject PRIMARY KEY (id)
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

CREATE TABLE teacher_lesson
(
    teacher_id UUID NOT NULL,
    lessons_id UUID NOT NULL,
    CONSTRAINT pk_teacher_lessons PRIMARY KEY (teacher_id, lessons_id)
);

CREATE TABLE time_slot
(
    id             UUID NOT NULL,
    date           TIME WITHOUT TIME ZONE,
    dayOfWeek    INTEGER,
    lessonTime_id UUID,
    CONSTRAINT pk_timeslot PRIMARY KEY (id)
);

CREATE TABLE time_slot_lesson
(
    timeSlot_id UUID NOT NULL,
    lessons_id   UUID NOT NULL,
    CONSTRAINT pk_timeslot_lessons PRIMARY KEY (timeSlot_id, lessons_id)
);

ALTER TABLE lesson_type_lesson
    ADD CONSTRAINT uc_class_type_lesson_lesson UNIQUE (lesson_id);

ALTER TABLE group_accounts
    ADD CONSTRAINT uc_group_accounts_accounts UNIQUE (accounts_id);

ALTER TABLE lesson_time_time_slot
    ADD CONSTRAINT uc_lesson_time_time_slot_timeslot UNIQUE (timeSlot_id);

ALTER TABLE study_room_lesson
    ADD CONSTRAINT uc_study_room_lesson_lesson UNIQUE (lesson_id);

ALTER TABLE teacher_lesson
    ADD CONSTRAINT uc_teacher_lessons_lessons UNIQUE (lessons_id);

ALTER TABLE time_slot_lesson
    ADD CONSTRAINT uc_time_slot_lessons_lessons UNIQUE (lessons_id);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_LESSONTYPE FOREIGN KEY (lessonType_id) REFERENCES lesson_type (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_STUDYROOM FOREIGN KEY (studyRoom_id) REFERENCES study_room (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_TIMESLOT FOREIGN KEY (timeSlot_id) REFERENCES time_slot (id);

ALTER TABLE teacher
    ADD CONSTRAINT FK_TEACHER_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE time_slot
    ADD CONSTRAINT FK_TIMESLOT_ON_LESSONTIME FOREIGN KEY (lessonTime_id) REFERENCES lesson_time (id);

ALTER TABLE lesson_type_lesson
    ADD CONSTRAINT fk_clatyples_on_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE lesson_type_lesson
    ADD CONSTRAINT fk_clatyples_on_lesson_type FOREIGN KEY (lessonType_id) REFERENCES lesson_type (id);

ALTER TABLE group_accounts
    ADD CONSTRAINT fk_groacc_on_account FOREIGN KEY (accounts_id) REFERENCES account (id);

ALTER TABLE group_accounts
    ADD CONSTRAINT fk_groacc_on_group FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE lesson_groups
    ADD CONSTRAINT fk_lesgro_on_group FOREIGN KEY (groups_id) REFERENCES groups (id);

ALTER TABLE lesson_groups
    ADD CONSTRAINT fk_lesgro_on_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE lesson_time_time_slot
    ADD CONSTRAINT fk_lestimtimslo_on_lesson_time FOREIGN KEY (lessonTime_id) REFERENCES lesson_time (id);

ALTER TABLE lesson_time_time_slot
    ADD CONSTRAINT fk_lestimtimslo_on_time_slot FOREIGN KEY (timeSlot_id) REFERENCES time_slot (id);

ALTER TABLE study_room_lesson
    ADD CONSTRAINT fk_sturooles_on_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE study_room_lesson
    ADD CONSTRAINT fk_sturooles_on_study_room FOREIGN KEY (studyRoom_id) REFERENCES study_room (id);

ALTER TABLE teacher_lesson
    ADD CONSTRAINT fk_teales_on_lesson FOREIGN KEY (lessons_id) REFERENCES lesson (id);

ALTER TABLE teacher_lesson
    ADD CONSTRAINT fk_teales_on_teacher FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE time_slot_lesson
    ADD CONSTRAINT fk_timsloles_on_lesson FOREIGN KEY (lessons_id) REFERENCES lesson (id);

ALTER TABLE time_slot_lesson
    ADD CONSTRAINT fk_timsloles_on_time_slot FOREIGN KEY (timeSlot_id) REFERENCES time_slot (id);