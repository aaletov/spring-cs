TRUNCATE TABLE people CASCADE;
TRUNCATE TABLE ward CASCADE;
TRUNCATE TABLE diagnosis CASCADE;

INSERT INTO diagnosis (id, name) VALUES
    (1, 'Cancer'), (2, 'Tuberculosis'), (3, 'Pneumonia'), (4, 'Tonsillitis'), (5, 'Rhinitis');

INSERT INTO ward (id, name, max_count) VALUES
    (1, 'Ward 1', 3), (2, 'Ward 2', 4), (3, 'Ward 3', 5), (4, 'Ward 4', 6);

INSERT INTO people (id, first_name, pather_name, last_name, diagnosis_id, ward_id) VALUES
    (1, 'Ivan', 'Smirnov', 'Eugenevich', 4, 1),
    (2, 'Sergey', 'Mitrakov', 'Vladivladovich', 4, 1),
    (3, 'Islam', 'Ivanov', 'Inalovich', 2, 1),
    (4, 'Ekaterina', 'Brown', 'Ekaterinovna', 1, 2),
    (5, 'Faizullo', 'Valiev', 'Shakhrierovich', 1, 2),
    (6, 'Ivan', 'Bochkov','Alexeevich', 1, 2),
    (7, 'Nikita', 'Gaevoy', 'Sergeevich', 5, 3),
    (8, 'Boris', 'Zolotov', 'Alexeevich', 5, 3),
    (9, 'Constantin', 'Kalinin', 'Mihailovivch', 2, 4);