CREATE TABLE shorten_url_stats (
    id BIGSERIAL PRIMARY KEY,
    shorten_url_id BIGINT NOT NULL,
    access_count BIGINT DEFAULT 0,
    CONSTRAINT fk_shorten_url
        FOREIGN KEY (shorten_url_id)
        REFERENCES shorten_url(id)
        ON DELETE CASCADE
);