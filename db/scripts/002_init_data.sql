INSERT INTO employee(inn, name, surname, hired)
VALUES
    ('123456789012', 'Михаил', 'Михаийлов', CURRENT_TIMESTAMP),
    ('123456274012', 'Александр', 'Александров', CURRENT_TIMESTAMP),
    ('123456589712', 'Лев', 'Левов', CURRENT_TIMESTAMP);

INSERT INTO person (login, password, employee_id)
VALUES
    ('m_mikhailov', '123', (SELECT id from employee WHERE name = 'Михаил')),
    ('admin', '123', (SELECT id from employee WHERE name = 'Михаил')),
    ('moder', '123', (SELECT id from employee WHERE name = 'Александр')),
    ('alexandr', '123', (SELECT id from employee WHERE name = 'Александр')),
    ('lev4ik', '123', (SELECT id from employee WHERE name = 'Лев'));