--Получить список студентов, отчисленных после первого сентября 2012 года
-- с очной или заочной формы обучения (специальность: 230101). В результат включить:
--номер группы;
--номер, фамилию, имя и отчество студента;
--номер пункта приказа;
--Для реализации использовать подзапрос с EXISTS.
SELECT "Н_УЧЕНИКИ"."ГРУППА", "Н_УЧЕНИКИ"."ИД", "Н_ЛЮДИ"."ФАМИЛИЯ", "Н_ЛЮДИ"."ИМЯ",
       "Н_ЛЮДИ"."ОТЧЕСТВО", "Н_УЧЕНИКИ"."П_ПРКОК_ИД"
FROM "Н_УЧЕНИКИ"
  JOIN "Н_ЛЮДИ" ON "Н_УЧЕНИКИ"."ЧЛВК_ИД" = "Н_ЛЮДИ"."ИД"
  JOIN "Н_ПЛАНЫ" ON "Н_ПЛАНЫ"."ИД" = "Н_УЧЕНИКИ"."ПЛАН_ИД"
  JOIN "Н_ФОРМЫ_ОБУЧЕНИЯ" ON "Н_ФОРМЫ_ОБУЧЕНИЯ"."ИД" = "Н_ПЛАНЫ"."ФО_ИД"
    AND ("Н_ФОРМЫ_ОБУЧЕНИЯ"."НАИМЕНОВАНИЕ" = 'Заочная' OR "Н_ФОРМЫ_ОБУЧЕНИЯ"."НАИМЕНОВАНИЕ" = 'Очная')
  JOIN "Н_НАПРАВЛЕНИЯ_СПЕЦИАЛ" ON "Н_ПЛАНЫ"."НАПС_ИД" = "Н_НАПРАВЛЕНИЯ_СПЕЦИАЛ"."ИД"
  JOIN "Н_НАПР_СПЕЦ" ON "Н_НАПР_СПЕЦ"."ИД" = "Н_НАПРАВЛЕНИЯ_СПЕЦИАЛ"."НС_ИД"
    AND "Н_НАПР_СПЕЦ"."КОД_НАПРСПЕЦ" = '230101'
  WHERE EXISTS (
    SELECT *
    FROM "Н_УЧЕНИКИ"
    WHERE "Н_УЧЕНИКИ"."ПРИЗНАК" = 'отчисл'
      AND "Н_УЧЕНИКИ"."СОСТОЯНИЕ" = 'утвержден'
      AND "Н_УЧЕНИКИ"."ИД" = "Н_УЧЕНИКИ"."ИД"
      AND DATE("Н_УЧЕНИКИ"."КОНЕЦ") > '2012-09-01'
  );