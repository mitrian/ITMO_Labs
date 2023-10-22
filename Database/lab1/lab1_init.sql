BEGIN;
CREATE TYPE translation_status AS ENUM(
    'Включена',
    'Выключена'
);
CREATE TABLE human(
    id SERIAL PRIMARY KEY,
    name VARCHAR(31) NOT NULL,
    height INT NOT NULL
);
CREATE TABLE galaxy(
    id SERIAL PRIMARY KEY,
    name VARCHAR(31) NOT NULL,
    location VARCHAR(31) NOT NULL
);
CREATE TABLE planet(
    id SERIAL PRIMARY KEY,
    name VARCHAR(31) NOT NULL,
    type_of_surface VARCHAR(31) NOT NULL,
    id_galaxy INT REFERENCES galaxy(id) ON DELETE CASCADE
);
CREATE TABLE  robot(
    id SERIAL PRIMARY KEY,
    research VARCHAR(31) NOT NULL,
    object_distance INT
);
CREATE TABLE human_group(
    id SERIAL PRIMARY KEY,
    name VARCHAR(31) NOT NULL,
    locationInSpace VARCHAR(31) NOT NULL,
    pozition_near_planet VARCHAR(31) NOT NULL,
    id_planet INT REFERENCES  planet(id) ON DELETE CASCADE
);
CREATE TABLE eyes(
    id SERIAL PRIMARY KEY,
    possibility_of_translation BOOLEAN,
    translation_status translation_status,
    id_robot INT REFERENCES robot(id) ON DELETE CASCADE
);
CREATE TABLE robots_of_group(
    id_group INT REFERENCES human_group(id) ON DELETE CASCADE,
    id_robot INT REFERENCES robot(id) ON DELETE CASCADE
);
CREATE TABLE group_members(
    id_human INT REFERENCES human(id) ON DELETE CASCADE,
    id_group INT REFERENCES human_group(id) ON DELETE CASCADE
);
COMMIT;

ALTER TABLE human_group ADD COLUMN people_amount INT DEFAULT 0;

CREATE OR REPLACE FUNCTION count_group_members(g_id INT) RETURNS INT AS $$
    DECLARE
        result_value INT;
    BEGIN

        SELECT COUNT(*)
        FROM group_members
        WHERE id_group = g_id
        INTO result_value;

        RETURN result_value;
    END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION mitrian_trigger_function() RETURNS TRIGGER AS $$
    DECLARE
        current_people_amount INT;
        affected_group INT;
    BEGIN


        affected_group = new.id_group;


        SELECT count_group_members(affected_group)
        INTO current_people_amount;

        UPDATE human_group
        SET people_amount = current_people_amount
        WHERE id = affected_group;

        RETURN new;

    END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER mitrian_trigger
AFTER INSERT OR UPDATE ON group_members
FOR EACH ROW
EXECUTE FUNCTION mitrian_trigger_function();


COMMIT;