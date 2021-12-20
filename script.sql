CREATE SCHEMA godel;

SET search_path TO godel;

CREATE TABLE employee (
    employee_id SERIAL PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    department_id INTEGER,
    job_title VARCHAR(255),
    gender VARCHAR(30) NOT NULL,
    date_of_birth DATE NOT NULL
);

INSERT INTO employee(first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES
('Viktor', 'Shilay', 1, 'Java Developer', 'MALE', '1996-01-22'),
('Evgeniy', 'Galinevsky', 2, 'JavaScript Developer', 'MALE', '1997-06-13'),
('Darya', 'Oreshko', 3, 'HR', 'FEMALE', '1997-05-20'),
('Igor', 'Chehov', 4, 'DevOps', 'MALE', '1992-02-21'),
('Artem', 'Sotnikov', 2, 'JavaScript Developer', 'MALE', '1994-01-13'),
('Olya', 'Anisimova', 3, 'HR', 'FEMALE', '1997-04-10'),
('Anastasiya', 'Romanova', 3, 'HR', 'FEMALE', '1995-07-17'),
('Dmitry', 'Soloviev', 1, 'Java Developer', 'MALE', '1996-06-12'),
('Maxim', 'Ermolin', '5', 'PR Manager', 'MALE', '1991-09-12'),
('Alexandr', 'Ivanov', '1', 'Java Developer', 'MALE', '1995-06-10');
