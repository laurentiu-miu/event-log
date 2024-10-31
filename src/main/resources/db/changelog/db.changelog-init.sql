-- liquibase formatted sql

-- changeset init:001
CREATE TABLE EVENT_LOG (
     mes_uuid UUID NOT NULL,
     trx_uuid UUID NOT NULL,
     event_type VARCHAR(100),
     log_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
     PRIMARY KEY (mes_uuid)
);
-- changeset init:002
CREATE TABLE ERROR_LOG (
       uuid UUID NOT NULL,
       ex_log VARCHAR(300),
       event_json JSON,
       log_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
       PRIMARY KEY (uuid)
);
-- changeset init:003
CREATE TABLE TAB_1 (
       uuid UUID NOT NULL,
       mes_uuid UUID NOT NULL UNIQUE,
       event_json JSON,
       PRIMARY KEY (uuid)
);
-- changeset init:004
CREATE TABLE TAB_2 (
       uuid UUID NOT NULL,
       mes_uuid UUID NOT NULL UNIQUE,
       event_json JSON,
       PRIMARY KEY (uuid)
);
-- changeset init:005
CREATE TABLE TAB_3 (
       uuid UUID NOT NULL,
       mes_uuid UUID NOT NULL UNIQUE,
       event_json JSON,
       PRIMARY KEY (uuid)
);