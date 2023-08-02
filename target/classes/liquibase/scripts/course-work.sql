-- liquibase formatted sql

--changeset golikovk1: create_notification_task
CREATE TABLE notification_task
(
    id BIGINT PRIMARY KEY,
    message TEXT NOT NULL,
    chat_id BIGINT NOT NULL,
    notification_date_time TIMESTAMP NOT NULL

);





