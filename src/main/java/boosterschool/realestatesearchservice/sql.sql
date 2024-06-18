INSERT INTO deal_types (id, type_name, active)
VALUES (1, 'Продажа', true),
       (2, 'Снять', true);

INSERT INTO property_types (id, type_name, active)
VALUES (1, 'Квартира', true),
       (2, 'Дом', true),
       (3, 'Коммерческая недвижмость', true),
       (4, 'Комната', true),
       (5, 'Участок', true),
       (6, 'Паркинг', true),
       (7, 'Гараж', true);

INSERT INTO room_counts (id, room_count, active)
VALUES (1, 1, true),
       (2, 2, true),
       (3, 3, true),
       (4, 4, true),
       (5, 5, true),
       (6, 6, true),
       (7, 7, true);

INSERT INTO housing_complexes (id, housing_complex_name, active)
VALUES (1, 'СМАРТ (SMART)', true),
       (2, 'Жилой комплекс "VENEZIAN"', true),
       (3, 'Ак-Ордо', true),
       (4, 'ЖК Миллион', true),
       (5, 'ЖК Монреаль', true),
       (6, 'ЖК Миллениум', true),
       (7, 'ЖК Изумруд', true);

INSERT INTO serieses (id, series, active)
VALUES (1, '102', true),
       (2, '104', true),
       (3, '104 улучшенная', true),
       (4, '105', true),
       (5, '105 улучшенная', true),
       (6, '106', true),
       (7, '106 улучшенная', true),
       (8, 'Элитка', true);

INSERT INTO building_types (id, building_type, active)
VALUES (1, 'Кирпич', true),
       (2, 'Монолит', true),
       (3, 'Панельный', true);

-- UPDATE building_types
-- SET active = false
-- WHERE building_type = 'Панельный';

INSERT INTO heatings (id, heating_type, active)
VALUES (1, 'Центральное', true),
       (2, 'На газе', true),
       (3, 'Электрическое', true);

INSERT INTO conditions (id, condition_type, active)
VALUES (1, 'Евроремонт', true),
       (2, 'Хорошее', true),
       (3, 'Среднее', true);



INSERT INTO location_types (id, location_type, active)
VALUES (1, 'Регион', true),
       (2, 'Город', true),
       (3, 'Район', true);

INSERT INTO locations (id, location_name, active, location_type_id)
VALUES (1, 'Любой регион', true, 1),
       (2, 'Любой Город', true, 1),
       (3, 'Любой Район', true, 1),
       (4, 'Чуйская область/Бишкек', true, 1),
       (5, 'Ошская область', true, 1),
       (6, 'Казакстан', true, 1);
INSERT INTO locations (id, location_name, active, location_id, location_type_id)
VALUES (7, 'Бишкек', true, 4, 2),
       (8, 'Кара-балта', true, 4, 2),
       (9, 'Ош', true, 5, 2),
       (10, 'Узген', true, 5, 2),
       (11, 'Алматы', true, 6, 2),
       (12, 'Шымкент', true, 6, 2),

       (13, 'Азия Молл', true, 7, 3),
       (14, 'Магистраль', true, 7, 3),
       (15, 'БГУ', true, 7, 3),
       (16, 'Филармония', true, 7, 3),
       (17, 'АУЦА', true, 7, 3);

INSERT INTO currencies (id, currency_type, active)
VALUES (1, 'Доллар', true),
       (2, 'Сом', true);

INSERT INTO price_types(id, price_type, active)
VALUES (1, 'За всё', true),
       (2, 'За квадратный метр', true);


INSERT INTO installment_plans (id, mark, active)
VALUES (1, 'YES', true),
       (2, 'NO', true);

INSERT INTO mortgages (id, mark, active)
VALUES (1, 'YES', true),
       (2, 'NO', true);

INSERT INTO exchange_options(id, option, active)
VALUES (1, 'Рассмотрю варианты', true),
       (2, 'С доплатой покупателя', true),
       (3, 'Ключ на ключ', true),
       (4, 'С доплатой продавца', true);

-- SELECT * FROM real_estate_objects ro
--                   JOIN deal_types dt ON ro.deal_type_id = dt.id
-- WHERE dt.type_name LIKE '%Продажа%';

-- SELECT re.id,
--        re.year_built,
--        re.floor,
--        re.street_name,
--        re.house_number,
--        p.total_price,
--        c.currency_type,
--        pt.price_type
-- FROM real_estate_objects re
--          JOIN prices p ON re.price_id = p.id
--          JOIN currencies c ON p.currency_id = c.id
--          JOIN price_types pt ON p.price_type_id = pt.id;

-- select l1_0.id,l1_0.active,
--        l2_0.id,l2_0.active,
--        l2_0.location_id,
--        l2_0.location_name,lt1_0.id,lt1_0.active,lt1_0.location_type,
--        l1_0.location_name,lt2_0.id,lt2_0.active,lt2_0.location_type
-- from locations l1_0 left join locations l2_0 on l2_0.id=l1_0.location_id
--     left join location_types lt1_0 on lt1_0.id=l2_0.location_type_id
--     left join location_types lt2_0 on lt2_0.id=l1_0.location_type_id where l1_0.id=14