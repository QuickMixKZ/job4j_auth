CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    name TEXT,
    surname TEXT,
    inn VARCHAR,
    hired TIMESTAMP
);

create table person (
    id SERIAL PRIMARY KEY,
    login VARCHAR(2000),
    password VARCHAR(2000),
    employee_id INT REFERENCES employee(id) ON DELETE CASCADE
);