CREATE TABLE short_code (
    id BIGSERIAL PRIMARY KEY,
    short_code_value TEXT NOT NULL UNIQUE
);

CREATE TABLE shorten_url (
    id BIGSERIAL PRIMARY KEY,
    original_url TEXT NOT NULL,
    short_code_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_short_code
        FOREIGN KEY (short_code_id)
        REFERENCES short_code(id)
        ON DELETE CASCADE
);