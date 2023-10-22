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

--      Get affected group id
        affected_group = new.id_group;

--      Get current people amount
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
AFTER INSERT OR UPDATE OR DELETE ON group_members
FOR EACH ROW
EXECUTE FUNCTION mitrian_trigger_function();

SELECT * FROM human_group;
