-- filepath: Database-Structure/Tables/test_all_tables.sql

-- Start transaction to avoid polluting the database
START TRANSACTION;

-- Test: Insert into role
INSERT INTO role(role_name) VALUES ('test_role');
SELECT * FROM role WHERE role_name = 'test_role';

-- Test: Insert into book_status
INSERT INTO book_status(status_name) VALUES ('test_status');
SELECT * FROM book_status WHERE status_name = 'test_status';

-- Test: Insert into publisher
INSERT INTO publisher(publisher_name) VALUES ('test_publisher');
SELECT * FROM publisher WHERE publisher_name = 'test_publisher';

-- Test: Insert into book_type
INSERT INTO book_type(type_name) VALUES ('test_type');
SELECT * FROM book_type WHERE type_name = 'test_type';

-- Test: Insert into genre
INSERT INTO genre(genre_name) VALUES ('test_genre');
SELECT * FROM genre WHERE genre_name = 'test_genre';

-- Test: Insert into author
INSERT INTO author(author_name) VALUES ('test_author');
SELECT * FROM author WHERE author_name = 'test_author';

-- Test: Insert into student
INSERT INTO student(student_id, role_id, first_name, last_name, email, cellphone, password)
VALUES ('test_student', (SELECT role_id FROM role WHERE role_name='student' LIMIT 1), 'Test', 'Student', 'test@student.com', '0123456789', 'pass');
SELECT * FROM student WHERE student_id = 'test_student';

-- Test: Insert into member
INSERT INTO member(member_id, role_id, first_name, last_name, email, cellphone, password)
VALUES ('test_member', (SELECT role_id FROM role WHERE role_name='member' LIMIT 1), 'Test', 'Member', 'test@member.com', '0123456789', 'pass');
SELECT * FROM member WHERE member_id = 'test_member';

-- Test: Insert into lecturer
INSERT INTO lecturer(lecturer_id, role_id, first_name, last_name, email, cellphone, password)
VALUES ('test_lecturer', (SELECT role_id FROM role WHERE role_name='lecturer' LIMIT 1), 'Test', 'Lecturer', 'test@lecturer.com', '0123456789', 'pass');
SELECT * FROM lecturer WHERE lecturer_id = 'test_lecturer';

-- Test: Insert into librarian
INSERT INTO librarian(librarian_id, role_id, first_name, last_name, email, cellphone, password)
VALUES ('test_librarian', (SELECT role_id FROM role WHERE role_name='librarian' LIMIT 1), 'Test', 'Librarian', 'test@librarian.com', '0123456789', 'pass');
SELECT * FROM librarian WHERE librarian_id = 'test_librarian';

-- Test: Insert into admin
INSERT INTO admin(admin_id, role_id, first_name, last_name, email, cellphone, password)
VALUES ('test_admin', (SELECT role_id FROM role WHERE role_name='admin' LIMIT 1), 'Test', 'Admin', 'test@admin.com', '0123456789', 'pass');
SELECT * FROM admin WHERE admin_id = 'test_admin';

-- Test: Insert into book
INSERT INTO book(isbn, book_status, publisher_id, type_id, book_title, edition, year_published)
VALUES ('1234567890123', (SELECT status_id FROM book_status WHERE status_name='available' LIMIT 1),
    (SELECT publisher_id FROM publisher WHERE publisher_name='test_publisher' LIMIT 1),
    (SELECT type_id FROM book_type WHERE type_name='test_type' LIMIT 1),
    'Test Book', 1, 2024);
SELECT * FROM book WHERE isbn = '1234567890123';

-- Test: Insert into book_genre
INSERT INTO book_genre(genre_id, isbn)
VALUES ((SELECT genre_id FROM genre WHERE genre_name='test_genre' LIMIT 1), '1234567890123');
SELECT * FROM book_genre WHERE isbn = '1234567890123';

-- Test: Insert into book_author
INSERT INTO book_author(author_id, isbn)
VALUES ((SELECT author_id FROM author WHERE author_name='test_author' LIMIT 1), '1234567890123');
SELECT * FROM book_author WHERE isbn = '1234567890123';

-- Test: Insert into reserved_books
INSERT INTO reserved_books(isbn, student_id, date_reserved)
VALUES ('1234567890123', 'test_student', NOW());
SELECT * FROM reserved_books WHERE isbn = '1234567890123' AND student_id = 'test_student';

-- Test: Insert into returned_books
INSERT INTO returned_books(isbn, student_id, date_returned)
VALUES ('1234567890123', 'test_student', NOW());
SELECT * FROM returned_books WHERE isbn = '1234567890123' AND student_id = 'test_student';

-- Test: Insert into borrowed_books
INSERT INTO borrowed_books(isbn, student_id, date_borrowed)
VALUES ('1234567890123', 'test_student', NOW());
SELECT * FROM borrowed_books WHERE isbn = '1234567890123' AND student_id = 'test_student';

-- Clean up: Rollback all changes
ROLLBACK;