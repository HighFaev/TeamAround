CREATE TABLE roles
(
    role_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(32) UNIQUE NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (role_id)
)