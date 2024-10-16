CREATE TABLE avenger (
    id bigserial NOT NULL,
    nick VARCHAR(36),
    person VARCHAR(128),
    description VARCHAR(128),
    history text
    PRIMARY KEY (id)
);

ALTER TABLE avenger ADD CONSTRAINT UK_5r88eemotwgru6k0ilqb2ledh UNIQUE (nick);