
CREATE TABLE class_type
(
    id   INTEGER NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_classtype PRIMARY KEY (id)
);

CREATE TABLE class_type_lesson
(
    lesson_type_id INTEGER NOT NULL,
    lesson_id      UUID    NOT NULL,
    CONSTRAINT pk_classtype_lesson PRIMARY KEY (lesson_type_id, lesson_id)
);

CREATE TABLE "group"
(
    id     UUID NOT NULL,
    number VARCHAR(255),
    CONSTRAINT pk_group PRIMARY KEY (id)
);

CREATE TABLE group_users
(
    group_id UUID NOT NULL,
    users_id UUID NOT NULL,
    CONSTRAINT pk_group_users PRIMARY KEY (group_id, users_id)
);

CREATE TABLE lesson
(
    id             UUID NOT NULL,
    study_room_id  UUID,
    lesson_type_id INTEGER,
    teacher_id     UUID,
    time_slot_id   UUID,
    CONSTRAINT pk_lesson PRIMARY KEY (id)
);

CREATE TABLE lesson_groups
(
    lesson_id UUID NOT NULL,
    groups_id UUID NOT NULL,
    CONSTRAINT pk_lesson_groups PRIMARY KEY (lesson_id, groups_id)
);

CREATE TABLE lesson_number
(
    id         INTEGER NOT NULL,
    start_time TIMESTAMP WITHOUT TIME ZONE,
    end_time   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_lessonnumber PRIMARY KEY (id)
);

CREATE TABLE lesson_number_time_slot
(
    lesson_number_id INTEGER NOT NULL,
    time_slot_id     UUID    NOT NULL,
    CONSTRAINT pk_lessonnumber_timeslot PRIMARY KEY (lesson_number_id, time_slot_id)
);

CREATE TABLE study_room
(
    id              UUID NOT NULL,
    building_number INTEGER,
    floor           INTEGER,
    name            VARCHAR(255),
    number          INTEGER,
    CONSTRAINT pk_studyroom PRIMARY KEY (id)
);

CREATE TABLE study_room_lesson
(
    study_room_id UUID NOT NULL,
    lesson_id     UUID NOT NULL,
    CONSTRAINT pk_studyroom_lesson PRIMARY KEY (study_room_id, lesson_id)
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
    first_name      VARCHAR(255),
    last_name       VARCHAR(255),
    patronymic_name VARCHAR(255),
    CONSTRAINT pk_teacher PRIMARY KEY (id)
);

CREATE TABLE teacher_lessons
(
    teacher_id UUID NOT NULL,
    lessons_id UUID NOT NULL,
    CONSTRAINT pk_teacher_lessons PRIMARY KEY (teacher_id, lessons_id)
);

CREATE TABLE time_slot
(
    id               UUID NOT NULL,
    date             TIMESTAMP WITHOUT TIME ZONE,
    day_of_week      INTEGER,
    lesson_number_id INTEGER,
    CONSTRAINT pk_timeslot PRIMARY KEY (id)
);

CREATE TABLE time_slot_lessons
(
    time_slot_id UUID NOT NULL,
    lessons_id   UUID NOT NULL,
    CONSTRAINT pk_timeslot_lessons PRIMARY KEY (time_slot_id, lessons_id)
);

CREATE TABLE "user"
(
    id              UUID NOT NULL,
    first_name      VARCHAR(255),
    last_name       VARCHAR(255),
    patronymic_name VARCHAR(255),
    roles           VARCHAR(255),
    email           VARCHAR(255),
    password        VARCHAR(255),
    group_id        UUID,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE class_type_lesson
    ADD CONSTRAINT uc_class_type_lesson_lesson UNIQUE (lesson_id);

ALTER TABLE group_users
    ADD CONSTRAINT uc_group_users_users UNIQUE (users_id);

ALTER TABLE lesson_number_time_slot
    ADD CONSTRAINT uc_lesson_number_time_slot_timeslot UNIQUE (time_slot_id);

ALTER TABLE study_room_lesson
    ADD CONSTRAINT uc_study_room_lesson_lesson UNIQUE (lesson_id);

ALTER TABLE teacher_lessons
    ADD CONSTRAINT uc_teacher_lessons_lessons UNIQUE (lessons_id);

ALTER TABLE time_slot_lessons
    ADD CONSTRAINT uc_time_slot_lessons_lessons UNIQUE (lessons_id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_LESSONTYPE FOREIGN KEY (lesson_type_id) REFERENCES class_type (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_STUDYROOM FOREIGN KEY (study_room_id) REFERENCES study_room (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_TIMESLOT FOREIGN KEY (time_slot_id) REFERENCES time_slot (id);

ALTER TABLE time_slot
    ADD CONSTRAINT FK_TIMESLOT_ON_LESSONNUMBER FOREIGN KEY (lesson_number_id) REFERENCES lesson_number (id);

ALTER TABLE "user"
    ADD CONSTRAINT FK_USER_ON_GROUP FOREIGN KEY (group_id) REFERENCES "group" (id);

ALTER TABLE class_type_lesson
    ADD CONSTRAINT fk_clatyples_on_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE class_type_lesson
    ADD CONSTRAINT fk_clatyples_on_lesson_type FOREIGN KEY (lesson_type_id) REFERENCES class_type (id);

ALTER TABLE group_users
    ADD CONSTRAINT fk_grouse_on_group FOREIGN KEY (group_id) REFERENCES "group" (id);

ALTER TABLE group_users
    ADD CONSTRAINT fk_grouse_on_user FOREIGN KEY (users_id) REFERENCES "user" (id);

ALTER TABLE lesson_groups
    ADD CONSTRAINT fk_lesgro_on_group FOREIGN KEY (groups_id) REFERENCES "group" (id);

ALTER TABLE lesson_groups
    ADD CONSTRAINT fk_lesgro_on_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE lesson_number_time_slot
    ADD CONSTRAINT fk_lesnumtimslo_on_lesson_number FOREIGN KEY (lesson_number_id) REFERENCES lesson_number (id);

ALTER TABLE lesson_number_time_slot
    ADD CONSTRAINT fk_lesnumtimslo_on_time_slot FOREIGN KEY (time_slot_id) REFERENCES time_slot (id);

ALTER TABLE study_room_lesson
    ADD CONSTRAINT fk_sturooles_on_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE study_room_lesson
    ADD CONSTRAINT fk_sturooles_on_study_room FOREIGN KEY (study_room_id) REFERENCES study_room (id);

ALTER TABLE teacher_lessons
    ADD CONSTRAINT fk_teales_on_lesson FOREIGN KEY (lessons_id) REFERENCES lesson (id);

ALTER TABLE teacher_lessons
    ADD CONSTRAINT fk_teales_on_teacher FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE time_slot_lessons
    ADD CONSTRAINT fk_timsloles_on_lesson FOREIGN KEY (lessons_id) REFERENCES lesson (id);

ALTER TABLE time_slot_lessons
    ADD CONSTRAINT fk_timsloles_on_time_slot FOREIGN KEY (time_slot_id) REFERENCES time_slot (id);