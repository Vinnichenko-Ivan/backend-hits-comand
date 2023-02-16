
CREATE TABLE audience
(
    id       BIGINT NOT NULL,
    building BIGINT,
    floor    BIGINT,
    number   VARCHAR(255),
    title    VARCHAR(255),
    CONSTRAINT pk_audience PRIMARY KEY (id)
);

CREATE TABLE audience_pair
(
    audience_id BIGINT NOT NULL,
    pair_id     UUID   NOT NULL,
    CONSTRAINT pk_audience_pair PRIMARY KEY (audience_id, pair_id)
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

CREATE TABLE pair
(
    id                 UUID NOT NULL,
    audience_entity_id BIGINT,
    "type_of_сlass_id" INTEGER,
    CONSTRAINT pk_pair PRIMARY KEY (id)
);

CREATE TABLE pair_number
(
    id         INTEGER NOT NULL,
    start_time TIMESTAMP WITHOUT TIME ZONE,
    end_time   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_pairnumber PRIMARY KEY (id)
);

CREATE TABLE pair_users
(
    pair_id  UUID NOT NULL,
    users_id UUID NOT NULL,
    CONSTRAINT pk_pair_users PRIMARY KEY (pair_id, users_id)
);

CREATE TABLE subject
(
    id    BIGINT NOT NULL,
    title VARCHAR(255),
    CONSTRAINT pk_subject PRIMARY KEY (id)
);

CREATE TABLE timeslot
(
    id   BIGINT NOT NULL,
    date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_timeslot PRIMARY KEY (id)
);

CREATE TABLE timeslot_pair_number
(
    timeslot_id    BIGINT  NOT NULL,
    pair_number_id INTEGER NOT NULL,
    CONSTRAINT pk_timeslot_pairnumber PRIMARY KEY (timeslot_id, pair_number_id)
);

CREATE TABLE type_of_class
(
    id    INTEGER NOT NULL,
    title VARCHAR(255),
    CONSTRAINT pk_typeofclass PRIMARY KEY (id)
);

CREATE TABLE type_of_class_pair_entities
(
    "type_of_сlass_id" INTEGER NOT NULL,
    pair_entities_id   UUID    NOT NULL,
    CONSTRAINT pk_typeofclass_pairentities PRIMARY KEY ("type_of_сlass_id", pair_entities_id)
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

ALTER TABLE audience_pair
    ADD CONSTRAINT uc_audience_pair_pair UNIQUE (pair_id);

ALTER TABLE group_user
    ADD CONSTRAINT uc_group_user_user UNIQUE (user_id);

ALTER TABLE type_of_class_pair_entities
    ADD CONSTRAINT uc_type_of_class_pair_entities_pairentities UNIQUE (pair_entities_id);

ALTER TABLE pair
    ADD CONSTRAINT FK_PAIR_ON_AUDIENCEENTITY FOREIGN KEY (audience_entity_id) REFERENCES audience (id);

ALTER TABLE pair
    ADD CONSTRAINT "FK_PAIR_ON_TYPEOFСLASS" FOREIGN KEY ("type_of_сlass_id") REFERENCES type_of_class (id);

ALTER TABLE "user"
    ADD CONSTRAINT FK_USER_ON_GROUP FOREIGN KEY (group_id) REFERENCES "group" (id);

ALTER TABLE audience_pair
    ADD CONSTRAINT fk_audpai_on_audience FOREIGN KEY (audience_id) REFERENCES audience (id);

ALTER TABLE audience_pair
    ADD CONSTRAINT fk_audpai_on_pair FOREIGN KEY (pair_id) REFERENCES pair (id);

ALTER TABLE group_user
    ADD CONSTRAINT fk_grouse_on_group FOREIGN KEY (group_id) REFERENCES "group" (id);

ALTER TABLE group_user
    ADD CONSTRAINT fk_grouse_on_user FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE pair_users
    ADD CONSTRAINT fk_paiuse_on_pair FOREIGN KEY (pair_id) REFERENCES pair (id);

ALTER TABLE pair_users
    ADD CONSTRAINT fk_paiuse_on_user FOREIGN KEY (users_id) REFERENCES "user" (id);

ALTER TABLE timeslot_pair_number
    ADD CONSTRAINT fk_timpainum_on_pair_number FOREIGN KEY (pair_number_id) REFERENCES pair_number (id);

ALTER TABLE timeslot_pair_number
    ADD CONSTRAINT fk_timpainum_on_timeslot FOREIGN KEY (timeslot_id) REFERENCES timeslot (id);

ALTER TABLE type_of_class_pair_entities
    ADD CONSTRAINT fk_typofclapaient_on_pair FOREIGN KEY (pair_entities_id) REFERENCES pair (id);

ALTER TABLE type_of_class_pair_entities
    ADD CONSTRAINT "fk_typofclapaient_on_type_ofсlass" FOREIGN KEY ("type_of_сlass_id") REFERENCES type_of_class (id);