CREATE TABLE IF NOT EXISTS account_role (
    id VARCHAR(150) NOT NULL PRIMARY KEY DEFAULT REPLACE(uuid_generate_v4()::text, '-',''),
    role VARCHAR(150) NOT NULL,
    account_id VARCHAR(60) NOT NULL,
    CONSTRAINT fk_account_role FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE ON UPDATE NO ACTION
);