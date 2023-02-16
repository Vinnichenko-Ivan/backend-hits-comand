
CREATE TABLE class_type
(
    id    INTEGER NOT NULL,
    title VARCHAR(255),
    CONSTRAINT pk_classtype PRIMARY KEY (id)
);

CREATE TABLE "group"
(
    id     BIGINT NOT NULL,
    number VARCHAR(255),
    CONSTRAINT pk_group PRIMARY KEY (id)
);

CREATE TABLE group_user
(
    group_id BIGINT NOT NULL,
    user_id  UUID   NOT NULL,
    CONSTRAINT pk_group_user PRIMARY KEY (group_id, user_id)
);

CREATE TABLE lesson
(
    id            UUID NOT NULL,
    study_room_id BIGINT,
    class_type_id INTEGER,
    CONSTRAINT pk_lesson PRIMARY KEY (id)
);

CREATE TABLE lesson_groups
(
    lesson_id UUID   NOT NULL,
    groups_id BIGINT NOT NULL,
    CONSTRAINT pk_lesson_groups PRIMARY KEY (lesson_id, groups_id)
);

CREATE TABLE lesson_number
(
    id         INTEGER NOT NULL,
    start_time TIMESTAMP WITHOUT TIME ZONE,
    end_time   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_lessonnumber PRIMARY KEY (id)
);

CREATE TABLE study_room
(
    id              BIGINT NOT NULL,
    building_number BIGINT,
    floor           SMALLINT,
    name            VARCHAR(255),
    CONSTRAINT pk_studyroom PRIMARY KEY (id)
);

CREATE TABLE subject
(
    id    BIGINT NOT NULL,
    title VARCHAR(255),
    CONSTRAINT pk_subject PRIMARY KEY (id)
);

CREATE TABLE time_slot
(
    id          BIGINT NOT NULL,
    date        TIMESTAMP WITHOUT TIME ZONE,
    day_of_week SMALLINT,
    CONSTRAINT pk_timeslot PRIMARY KEY (id)
);

CREATE TABLE time_slot_lesson_number
(
    timeslot_id      BIGINT  NOT NULL,
    lesson_number_id INTEGER NOT NULL,
    CONSTRAINT pk_timeslot_lessonnumber PRIMARY KEY (timeslot_id, lesson_number_id)
);

CREATE TABLE "user"
(
    id         UUID NOT NULL,
    name       VARCHAR(255),
    last_name  VARCHAR(255),
    patronymic VARCHAR(255),
    roles      VARCHAR(255),
    email      VARCHAR(255),
    password   VARCHAR(255),
    group_id   BIGINT,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE group_user
    ADD CONSTRAINT uc_group_user_user UNIQUE (user_id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_CLASSTYPE FOREIGN KEY (class_type_id) REFERENCES class_type (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_STUDYROOM FOREIGN KEY (study_room_id) REFERENCES study_room (id);

ALTER TABLE "user"
    ADD CONSTRAINT FK_USER_ON_GROUP FOREIGN KEY (group_id) REFERENCES "group" (id);

ALTER TABLE group_user
    ADD CONSTRAINT fk_grouse_on_group FOREIGN KEY (group_id) REFERENCES "group" (id);

ALTER TABLE group_user
    ADD CONSTRAINT fk_grouse_on_user FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE lesson_groups
    ADD CONSTRAINT fk_lesgro_on_group FOREIGN KEY (groups_id) REFERENCES "group" (id);

ALTER TABLE lesson_groups
    ADD CONSTRAINT fk_lesgro_on_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id);

ALTER TABLE time_slot_lesson_number
    ADD CONSTRAINT fk_timslolesnum_on_lesson_number FOREIGN KEY (lesson_number_id) REFERENCES lesson_number (id);

ALTER TABLE time_slot_lesson_number
    ADD CONSTRAINT fk_timslolesnum_on_timeslot FOREIGN KEY (timeslot_id) REFERENCES time_slot (id);