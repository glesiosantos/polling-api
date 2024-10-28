CREATE TABLE IF NOT EXISTS accounts (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    name VARCHAR(150) NOT NULL,
    username VARCHAR(60) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL,
    created_at DATE NOT NULL DEFAULT 'now()',
    updated_at DATE
);