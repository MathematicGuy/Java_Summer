DROP TABLE IF EXISTS Run;

CREATE TABLE IF NOT EXISTS Run(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    started_on TIMESTAMP,
    completed_on TIMESTAMP,
    kilometers INTEGER,
    location VARCHAR(255),
    version INTEGER
);

