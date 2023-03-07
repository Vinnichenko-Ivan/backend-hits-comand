
CREATE TABLE account
(
    id              UUID NOT NULL,
    first_name      VARCHAR(255),
    last_name       VARCHAR(255),
    patronymic_name VARCHAR(255),
    role            VARCHAR(255),
    email           VARCHAR(255),
    password        VARCHAR(255),
    group_id        UUID,
    teacher_id      UUID,
    accepted        BOOLEAN,
    CONSTRAINT pk_account PRIMARY KEY (id)
);

CREATE TABLE group_changing_request
(
    id         UUID NOT NULL,
    account_id UUID,
    group_id   UUID,
    CONSTRAINT pk_group_changing_request PRIMARY KEY (id)
);

CREATE TABLE groups
(
    id     UUID NOT NULL,
    number VARCHAR(255),
    CONSTRAINT pk_groups PRIMARY KEY (id)
);

CREATE TABLE jwt_token
(
    id           UUID NOT NULL,
    secret       VARCHAR(255),
    date_created TIMESTAMP WITHOUT TIME ZONE,
    date_exp     TIMESTAMP WITHOUT TIME ZONE,
    account_id   UUID,
    CONSTRAINT pk_jwt_token PRIMARY KEY (id)
);

CREATE TABLE lesson
(
    id              UUID NOT NULL,
    study_room_id   UUID,
    teacher_id      UUID,
    date            date,
    day_of_week     INTEGER,
    lesson_group_id UUID,
    lesson_time_id  UUID,
    CONSTRAINT pk_lesson PRIMARY KEY (id)
);

CREATE TABLE lesson_group
(
    id             UUID NOT NULL,
    start_date     TIME WITHOUT TIME ZONE,
    end_date       TIME WITHOUT TIME ZONE,
    frequency      INTEGER,
    subject_id     UUID,
    lesson_type_id UUID,
    CONSTRAINT pk_lesson_group PRIMARY KEY (id)
);

CREATE TABLE lesson_group_group
(
    accounts_id UUID NOT NULL,
    group_id    UUID NOT NULL,
    CONSTRAINT pk_lesson_group_group PRIMARY KEY (accounts_id, group_id)
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

CREATE TABLE lesson_type
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_lesson_type PRIMARY KEY (id)
);

CREATE TABLE study_room
(
    id              UUID NOT NULL,
    building_number INTEGER,
    floor           INTEGER,
    name            VARCHAR(255),
    number          VARCHAR(255),
    CONSTRAINT pk_study_room PRIMARY KEY (id)
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
    account_id      UUID,
    CONSTRAINT pk_teacher PRIMARY KEY (id)
);

ALTER TABLE lesson_group_lessons
    ADD CONSTRAINT uc_lesson_group_lessons_lessons UNIQUE (lessons_id);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE group_changing_request
    ADD CONSTRAINT FK_GROUP_CHANGING_REQUEST_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE group_changing_request
    ADD CONSTRAINT FK_GROUP_CHANGING_REQUEST_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE jwt_token
    ADD CONSTRAINT FK_JWT_TOKEN_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE lesson_group
    ADD CONSTRAINT FK_LESSON_GROUP_ON_LESSONTYPE FOREIGN KEY (lesson_type_id) REFERENCES lesson_type (id);

ALTER TABLE lesson_group
    ADD CONSTRAINT FK_LESSON_GROUP_ON_SUBJECT FOREIGN KEY (subject_id) REFERENCES subject (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_LESSONGROUP FOREIGN KEY (lesson_group_id) REFERENCES lesson_group (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_LESSONTIME FOREIGN KEY (lesson_time_id) REFERENCES lesson_time (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_STUDYROOM FOREIGN KEY (study_room_id) REFERENCES study_room (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE teacher
    ADD CONSTRAINT FK_TEACHER_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE lesson_group_group
    ADD CONSTRAINT fk_lesgrogro_on_group FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE lesson_group_group
    ADD CONSTRAINT fk_lesgrogro_on_lesson_group FOREIGN KEY (accounts_id) REFERENCES lesson_group (id);

ALTER TABLE lesson_group_lessons
    ADD CONSTRAINT fk_lesgroles_on_lesson FOREIGN KEY (lessons_id) REFERENCES lesson (id);

ALTER TABLE lesson_group_lessons
    ADD CONSTRAINT fk_lesgroles_on_lesson_group FOREIGN KEY (lesson_group_id) REFERENCES lesson_group (id);