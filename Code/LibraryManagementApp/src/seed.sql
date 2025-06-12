CREATE TABLE IF NOT EXISTS student (
    student_id VARCHAR(10) PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    home_address VARCHAR(255),
    telephone VARCHAR(15),
    password VARCHAR(100),
    repeat_password VARCHAR(100),
    dateof_birth DATE
);
INSERT INTO student (student_id, first_name, last_name, email, home_address, telephone, password, repeat_password, dateof_birth)
VALUES ('stu001', 'Tuhafeni', 'Nangolo', 'tuhafeni@example.com', '123 Main St', '0812345678', 'stuPass123', 'stuPass123', '2000-01-01');

CREATE TABLE IF NOT EXISTS book (
    book_id VARCHAR(10) PRIMARY KEY,
    title VARCHAR(255),
    isbn VARCHAR(13),
    status VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS publisher (
    publisher_name VARCHAR(100) PRIMARY KEY
);
INSERT INTO publisher (publisher_name) VALUES ('Publisher1');

CREATE TABLE IF NOT EXISTS book_type (
    type_name VARCHAR(100) PRIMARY KEY
);
INSERT INTO book_type (type_name) VALUES ('Novel');