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

INSERT INTO employees (first_name, last_name, employee_login, employee_access_code, is_admin) values
    ('John', 'Smith', 'jsmith', '1234', 1),
    ('Adam', 'Smith', 'asmith', '5678', 0);

INSERT INTO clients (first_name, last_name, client_login, client_access_code) values
    ('John', 'Smith', 'jsmithclient', '1234c'),
    ('Adam', 'Smith', 'asmithclient', '5678c'),
    ('Joseph', 'Smith', 'josmith', '1234c'),
    ('Jonathan', 'Smith', 'jnsmith', '5678c');

INSERT INTO rooms (room_number, room_type, room_status, client_id) VALUES
    ('001', 'NORMAL', 'FREE', null),
    ('002', 'NORMAL', 'FREE', null),
    ('003', 'NORMAL', 'FREE', null),
    ('004', 'NORMAL', 'TAKEN', 1),
    ('011', 'PREMIUM', 'FREE', null),
    ('012', 'PREMIUM', 'FREE', null),
    ('013', 'PREMIUM', 'FREE', null),
    ('014', 'NORMAL', 'TAKEN', 2),
    ('015', 'PREMIUM', 'TAKEN', 4);



