CREATE TABLE cat
(
    id       BIGSERIAL PRIMARY KEY,
    name     TEXT,
    image_url TEXT,
    selected bigserial,
    is_deleted  BOOLEAN   NOT NULL DEFAULT FALSE,
    created  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);