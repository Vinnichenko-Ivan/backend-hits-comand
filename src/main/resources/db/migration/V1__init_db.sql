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
    dateCreated TIMESTAMP WITHOUT TIME ZONE,
    dateExp     TIMESTAMP WITHOUT TIME ZONE,
    account_id   UUID,
    CONSTRAINT pk_jwt_token PRIMARY KEY (id)
);

CREATE TABLE lesson
(
    id              UUID NOT NULL,
    studyRoom_id   UUID,
    teacher_id      UUID,
    date            TIMESTAMP WITHOUT TIME ZONE,
    dayOfWeek     INTEGER,
    lessonGroup_id UUID,
    lessonTime_id  UUID,
    CONSTRAINT pk_lesson PRIMARY KEY (id)
);

CREATE TABLE lesson_group
(
    id             UUID NOT NULL,
    startDate     DATE,
    endDate       DATE,
    frequency      INTEGER,
    subject_id     UUID,
    lessonType_id UUID,
    CONSTRAINT pk_lesson_group PRIMARY KEY (id)
);

CREATE TABLE lesson_group_groups
(
    lessonGroup_id UUID NOT NULL,
    groups_id    UUID NOT NULL,
    CONSTRAINT pk_lesson_group_group PRIMARY KEY (lessonGroup_id, groups_id)
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
    buildingNumber INTEGER,
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
    firstName      VARCHAR(255),
    lastName       VARCHAR(255),
    patronymicName VARCHAR(255),
    account_id      UUID,
    CONSTRAINT pk_teacher PRIMARY KEY (id)
);

ALTER TABLE lesson_group_lesson
    ADD CONSTRAINT uc_lesson_group_lessons_lessons UNIQUE (lessons_id);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id) on delete cascade;

ALTER TABLE group_changing_request
    ADD CONSTRAINT FK_GROUP_CHANGING_REQUEST_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (id) on delete set null ;

ALTER TABLE group_changing_request
    ADD CONSTRAINT FK_GROUP_CHANGING_REQUEST_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id) on delete set null;

ALTER TABLE jwt_token
    ADD CONSTRAINT FK_JWT_TOKEN_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE lesson_group
    ADD CONSTRAINT FK_LESSON_GROUP_ON_LESSONTYPE FOREIGN KEY (lessonType_id) REFERENCES lesson_type (id);

ALTER TABLE lesson_group
    ADD CONSTRAINT FK_LESSON_GROUP_ON_SUBJECT FOREIGN KEY (subject_id) REFERENCES subject (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_LESSONGROUP FOREIGN KEY (lessonGroup_id) REFERENCES lesson_group (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_LESSONTIME FOREIGN KEY (lessonTime_id) REFERENCES lesson_time (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_STUDYROOM FOREIGN KEY (studyRoom_id) REFERENCES study_room (id);

ALTER TABLE lesson
    ADD CONSTRAINT FK_LESSON_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id);

ALTER TABLE teacher
    ADD CONSTRAINT FK_TEACHER_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE lesson_group_groups
    ADD CONSTRAINT fk_lesgrogro_on_group FOREIGN KEY (groups_id) REFERENCES groups (id) on delete cascade ;

ALTER TABLE lesson_group_groups
    ADD CONSTRAINT fk_lesgrogro_on_lesson_group FOREIGN KEY (lessonGroup_id) REFERENCES lesson_group (id) on delete cascade ;

ALTER TABLE lesson_group_lesson
    ADD CONSTRAINT fk_lesgroles_on_lesson FOREIGN KEY (lessons_id) REFERENCES lesson (id);

ALTER TABLE lesson_group_lesson
    ADD CONSTRAINT fk_lesgroles_on_lesson_group FOREIGN KEY (lessonGroup_id) REFERENCES lesson_group (id);