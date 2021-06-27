DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS employees;

CREATE TABLE clients (
    client_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    client_login VARCHAR(250) NOT NULL UNIQUE,
    client_access_code VARCHAR(250) NOT NULL
);

CREATE TABLE rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(3) NOT NULL UNIQUE,
    room_type ENUM('NORMAL', 'PREMIUM') NOT NULL,
    room_status ENUM('FREE', 'TAKEN') NOT NULL DEFAULT 'FREE',
    client_id INT,
    FOREIGN KEY (client_id) REFERENCES clients(client_id)
);

CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    employee_login VARCHAR(250) NOT NULL UNIQUE,
    employee_access_code VARCHAR(250) NOT NULL,
    is_admin BIT NOT NULL DEFAULT 0
);

INSERT INTO rooms (room_number, room_type) VALUES
    ('001', 'NORMAL'),
    ('002', 'NORMAL'),
    ('003', 'NORMAL'),
    ('011', 'PREMIUM'),
    ('012', 'PREMIUM'),
    ('013', 'PREMIUM');

INSERT INTO employees (first_name, last_name, employee_login, employee_access_code, is_admin) values
    ('John', 'Smith', 'jsmith', '1234', 1),
    ('Adam', 'Smith', 'asmith', '5678', 0);


INSERT INTO clients (first_name, last_name, client_login, client_access_code) values
    ('John', 'Smith', 'jsmithclient', '1234c'),
    ('Adam', 'Smith', 'asmithclient', '5678c');





