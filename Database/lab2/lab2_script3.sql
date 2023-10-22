--Вывести число студентов группы 3102, которые младше 20 лет.
--Ответ должен содержать только одно число.
SELECT COUNT(*)
FROM "Н_УЧЕНИКИ"
         JOIN "Н_ЛЮДИ" ON "Н_ЛЮДИ"."ИД" = "Н_УЧЕНИКИ"."ЧЛВК_ИД"
WHERE "Н_УЧЕНИКИ"."ГРУППА" = '3102'
    AND "Н_УЧЕНИКИ"."ПРИЗНАК" <> 'отчисл'
    AND "Н_УЧЕНИКИ"."ПРИЗНАК" <> 'академ'
    AND (
        (date_part('year',age("Н_ЛЮДИ"."ДАТА_СМЕРТИ","Н_ЛЮДИ"."ДАТА_РОЖДЕНИЯ")) < 20
             AND "Н_ЛЮДИ"."ДАТА_СМЕРТИ" <> '9999-09-09 00:00:00.000000')
        OR
        (date_part('year',age("Н_ЛЮДИ"."ДАТА_РОЖДЕНИЯ")) < 20
             AND "Н_ЛЮДИ"."ДАТА_СМЕРТИ" = '9999-09-09 00:00:00.000000'));

select * from "Н_ГРУППЫ_ПЛАНОВ" LEFT JOIN "Н_ПЛАНЫ" ON "Н_ПЛАНЫ"."ИД" = "Н_ГРУППЫ_ПЛАНОВ"."ПЛАН_ИД" where "ГРУППА" = '3102'

