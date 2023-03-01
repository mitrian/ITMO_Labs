BEGIN;
CREATE TYPE translation_status AS ENUM(
    'Включена',
    'Выключена'
);
CREATE TABLE human(
    id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    height INT
);
CREATE TABLE planet(
    id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    type_of_surface VARCHAR(32) NOT NULL
);
CREATE TABLE  robot(
    id SERIAL PRIMARY KEY,
    research VARCHAR(32) NOT NULL,
    object_distance INT
);
CREATE TABLE human_group(
    id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    locationInSpace VARCHAR(32) NOT NULL,
    pozition_near_planet VARCHAR(32) NOT NULL,
    id_planet INT REFERENCES planet(id)
);
CREATE TABLE galaxy(
    id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    location VARCHAR(32) NOT NULL,
    id_planet INT REFERENCES planet(id)
);
CREATE TABLE eyes(
    id SERIAL PRIMARY KEY,
    possibility_of_translation BOOLEAN,
    translation_status translation_status,
    id_robot INT REFERENCES robot(id)
);
CREATE TABLE robots_of_group(
    id_group INT REFERENCES human_group(id),
    id_robot INT UNIQUE REFERENCES robot(id)
);
CREATE TABLE group_members(
    id_human INT REFERENCES human(id),
    id_group INT REFERENCES human_group(id)
);
COMMIT;