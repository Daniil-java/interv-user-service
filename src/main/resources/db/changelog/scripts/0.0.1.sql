--liquibase formatted sql

--changeset DanielK:1

CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     name TEXT,
                                     balance DECIMAL DEFAULT 0,
                                     telegram_id BIGINT,
                                     job_title TEXT,
                                     properties TEXT,
                                     updated TIMESTAMP,
                                     created TIMESTAMP DEFAULT CURRENT_TIMESTAMP

    );

