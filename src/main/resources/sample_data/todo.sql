DROP TABLE IF EXISTS  tasks;
DROP TYPE IF EXISTS TASK_TYPE;

CREATE TYPE TASK_TYPE AS ENUM
(
    'WORK',
    'HOME',
    'HOBBY'
);

CREATE TABLE tasks
(
    id          SERIAL NOT NULL,
    type        TASK_TYPE,
    name        VARCHAR,
    due_date    TIMESTAMP WITHOUT TIME ZONE,
    estimate    INT,
    completed   BOOLEAN
);

ALTER TABLE ONLY tasks
    ADD CONSTRAINT pk_id PRIMARY KEY (id);