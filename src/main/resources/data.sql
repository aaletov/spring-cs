INSERT INTO diagnosis (name) VALUES
    ('Cancer'), ('Tuberculosis'), ('Pneumonia'), ('Tonsillitis'), ('Rhinitis');

INSERT INTO ward (name, max_count) VALUES
    ('Ward 1', 3), ('Ward 2', 4), ('Ward 3', 5), ('Ward 4', 6);

INSERT INTO people (first_name, pather_name, last_name, diagnosis_id, ward_id) VALUES
    ('Ivan', 'Smirnov', 'Eugenevich', 4, 1),
    ('Sergey', 'Mitrakov', 'Vladivladovich', 4, 1),
    ('Islam', 'Ivanov', 'Inalovich', 2, 1),
    ('Ekaterina', 'Brown', 'Ekaterinovna', 1, 2),
    ('Faizullo', 'Valiev', 'Shakhrierovich', 1, 2),
    ('Ivan', 'Bochkov','Alexeevich', 1, 2),
    ('Nikita', 'Gaevoy', 'Sergeevich', 5, 3),
    ('Boris', 'Zolotov', 'Alexeevich', 5, 3),
    ('Constantin', 'Kalinin', 'Mihailovivch', 2, 4);