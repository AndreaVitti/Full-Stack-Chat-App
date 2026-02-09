CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL
);

CREATE TABLE messages
(
    id       BIGSERIAL PRIMARY KEY,
    type     VARCHAR(10),
    sender_id BIGINT,
    payload TEXT,
    CONSTRAINT fk_sender
        FOREIGN KEY (sender_id)
            REFERENCES users(id)
);