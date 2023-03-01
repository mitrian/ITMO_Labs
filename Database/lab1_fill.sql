BEGIN;
INSERT INTO human(name, height)
VALUES ('Vlad',78),
       ('DIMA',86),
       ('Andrey',100),
       ('Anton',63),
       ('Vanya', 93),
       ('Ignat', 67);
INSERT INTO planet(name, type_of_surface)
VALUES ('I-8124','гладкий'),
       ('Bounty','со впадинами'),
       ('Obezyan', 'водяной');
INSERT INTO robot(research, object_distance)
VALUES ('поверхность',0),
       ('планета',6),
       ('живность', 22);
INSERT INTO human_group(name, locationinspace, pozition_near_planet, id_planet)
VALUES ('Чебурашки','рядом с планетой I-8124','литосфера',1),
       ('Умпалумпы','планет Bounty','атмосфера',2),
       ('Добряки','рядом с планетой Obezyan','биосфера',3);
INSERT INTO galaxy(name, location, id_planet)
VALUES ('Milkiway','вселенная 15',1),
       ('Snikers','вселенная Mars',2),
       ('Bongobongo','вселенная Tron',3);
INSERT INTO eyes(possibility_of_translation, translation_status,id_robot)
VALUES (TRUE, 'Включена',1),
       (TRUE, 'Выключена',2),
       (FALSE, 'Выключена',3);
INSERT INTO robots_of_group(id_group, id_robot)
VALUES (1,1),
       (2,2),
       (3,3);
INSERT INTO  group_members(id_human, id_group)
VALUES (1,1),
       (2,1),
       (3,2),
       (4,2),
       (5,2),
       (6,2);
COMMIT;