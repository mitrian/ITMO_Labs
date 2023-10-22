---ALTER TABLE human_group ADD COLUMN people_amount INT;

--степень отношения, виды тригеров, что-то там функции



 --подсчет количетсва людей в группе
UPDATE human_group
SET people_amount = (
  SELECT COUNT(*) FROM group_members AS gm
  WHERE gm.id_group = human_group.id
);

CREATE OR REPLACE FUNCTION update_am ()
RETURNS trigger AS $$
    DECLARE
        counter INT;
BEGIN
    SELECT COUNT(*) INTO counter FROM human_group RIGHT OUTER JOIN group_members ON group_members.id_group = human_group.id
    WHERE group_members.id_group = human_group.id;
    UPDATE human_group
    SET people_amount = counter;
    RETURN NEW;
END
$$ LANGUAGE PLPGSQL;


CREATE OR REPLACE TRIGGER INSERT_CHANGES
AFTER INSERT OR UPDATE ON group_members
FOR EACH ROW
EXECUTE PROCEDURE update_am();

UPDATE group_members SET id_group = 1 WHERE id_human = 2;

