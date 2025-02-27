-- Table: sys_account

-- DROP TABLE IF EXISTS sys_account;

CREATE TABLE IF NOT EXISTS sys_account
(
    account_id character varying(64) COLLATE pg_catalog."default" NOT NULL,
    account_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    password_salt character varying(255) COLLATE pg_catalog."default",
    status character varying(16) COLLATE pg_catalog."default",
    CONSTRAINT sys_account_pkey PRIMARY KEY (account_id)
)

COMMENT ON TABLE sys_account IS '系统账号表';