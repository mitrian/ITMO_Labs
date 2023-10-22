BEGIN;

DROP TABLE human CASCADE;
DROP TABLE planet CASCADE;
DROP TABLE robot CASCADE;
DROP TABLE human_group CASCADE;
DROP TABLE group_members CASCADE;
DROP TABLE galaxy CASCADE ;
DROP TABLE eyes CASCADE;
DROP TABLE robots_of_group CASCADE;
DROP TYPE translation_status CASCADE;
--DROP FUNCTION mitrian_trigger_function();
--DROP FUNCTION count_group_members(g_id INT);

COMMIT;


