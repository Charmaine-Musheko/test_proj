-- DROP DATABASE IF IT ALREADY EXISTS
DROP DATABASE IF EXISTS lms;
-- Create the database
CREATE DATABASE lms;

USE lms;
-- CREATE TABLES WITHOUT FOREIGN KEYS
CREATE TABLE role(
	role_id int auto_increment primary key,
    role_name varchar(25)
);
CREATE TABLE book_status(
	status_id int auto_increment primary key,
    status_name varchar(50)
);
CREATE TABLE publisher(
	publisher_id int auto_increment primary key,
    publisher_name varchar(50)
);
CREATE TABLE book_type(
	type_id int auto_increment primary key,
    type_name varchar(50)
);
CREATE TABLE genre(
	genre_id int auto_increment primary key,
    genre_name varchar(50)
);
CREATE TABLE author(
	author_id int auto_increment primary key,
    author_name varchar(150) not null
);

-- CREATE TABLES WITH ONLY 1 FOREIGN KEY
CREATE TABLE student(
	student_id varchar(50) primary key not null,
    role_id int,
    first_name varchar(50) not null,
    middle_name varchar(50) ,
    last_name varchar(50) not null,
    email varchar(100) not null,
    cellphone char(10) not null,
    password varchar(100) not null,
    constraint fk_stud_role foreign key(role_id) references	role(role_id)
);
CREATE TABLE member(
	member_id varchar(50) primary key not null,
    role_id int,
    first_name varchar(50) not null,
    middle_name varchar(50) ,
    last_name varchar(50) not null,
    email varchar(100) not null,
    cellphone char(10) not null,
    password varchar(100) not null,
    constraint fk_mem_role foreign key(role_id) references	role(role_id)
);
CREATE TABLE lecturer(
	lecturer_id varchar(50) primary key not null,
    role_id int,
    first_name varchar(50) not null,
    middle_name varchar(50) ,
    last_name varchar(50) not null,
    email varchar(100) not null,
    cellphone char(10) not null,
    password varchar(100) not null,
    constraint fk_lec_role foreign key(role_id) references	role(role_id)
);
CREATE TABLE librarian(
	librarian_id varchar(50) primary key not null,
    role_id int,
    first_name varchar(50) not null,
    middle_name varchar(50) ,
    last_name varchar(50) not null,
    email varchar(100) not null,
    cellphone char(10) not null,
    password varchar(100) not null,
    constraint fk_user_role foreign key(role_id) references	role(role_id)
);
CREATE TABLE admin(
	admin_id varchar(50) primary key not null,
    role_id int,
    first_name varchar(50) not null,
    middle_name varchar(50) ,
    last_name varchar(50) not null,
    email varchar(100) not null,
    cellphone char(10) not null,
    password varchar(100) not null,
    constraint fk_adm_role foreign key(role_id) references	role(role_id)
);

-- CREATE TABLES WITH MANY FOREIGN KEYS
CREATE TABLE book(
	isbn char(13) primary key not null,
    book_status int,
    publisher_id int,
    type_id int,
    book_title varchar(255) not null,
    edition int,
    year_published year not null,
    constraint fk_book_status foreign key(book_status) references book_status(status_id),
    constraint fk_book_publisher foreign key(publisher_id) references publisher(publisher_id),
    constraint fk_book_type foreign key(type_id) references book_type(type_id)
);
CREATE TABLE book_genre(
	book_genre_id int auto_increment primary key,
    genre_id int,
    isbn char(13),
    constraint fk_genre_book foreign key(isbn) references book(isbn) on delete cascade,
    constraint fk_book_genre foreign key(genre_id) references genre(genre_id)
);
CREATE TABLE book_author(
	book_author_id int auto_increment primary key,
    author_id int,
    isbn char(13),
    constraint fk_author_book foreign key(isbn) references book(isbn) on delete cascade,
    constraint fk_book_author foreign key(author_id) references author(author_id)
);
CREATE TABLE reserved_books(
	reservation_id int auto_increment primary key not null,
    isbn char(13) not null,
    student_id varchar(50),
    member_id varchar(50),
    lecturer_id varchar(50),
    date_reserved datetime not null,
    constraint fk_book_reserved foreign key(isbn) references book(isbn),
    constraint fk_student_reserver foreign key(student_id) references student(student_id),
    constraint fk_lecturer_reserver foreign key(lecturer_id) references lecturer(lecturer_id),
    constraint fk_member_reserver foreign key(member_id) references member(member_id)
);
CREATE TABLE returned_books(
	return_id int auto_increment primary key not null,
    isbn char(13) not null,
    student_id varchar(50),
    member_id varchar(50),
    lecturer_id varchar(50),
    date_returned datetime not null,
    constraint fk_book_returned foreign key(isbn) references book(isbn),
    constraint fk_student_returner foreign key(student_id) references student(student_id),
    constraint fk_lecturer_returner foreign key(lecturer_id) references lecturer(lecturer_id),
    constraint fk_member_returner foreign key(member_id) references member(member_id)
);
CREATE TABLE borrowed_books(
	borrow_id int auto_increment primary key not null,
    isbn char(13) not null,
    student_id varchar(50),
    member_id varchar(50),
    lecturer_id varchar(50),
    date_borrowed datetime not null,
    constraint fk_book_borrowed foreign key(isbn) references book(isbn),
    constraint fk_student_borrower foreign key(student_id) references student(student_id),
    constraint fk_lecturer_borrower foreign key(lecturer_id) references lecturer(lecturer_id),
    constraint fk_member_borrower foreign key(member_id) references member(member_id)
);

-- Adding default values
insert into book_status(status_name)
values  ('available'),
		('unavailable'),
		('borrowed'),
		('lost'),
		('returned'),
		('late'),
		('reserved');

insert into genre(genre_name)
values  ('Fantasy'),
		('Science Fiction'),
		('Dystopian'),
		('Action & Adventure'),
		('Mystery'),
		('Horror'),
		('Thriller & Suspense'),
		('Historical Fiction'),
		('Romance'),
		('Young Adult'),
		('Autobiography'),
		('Biography'),
		('Food & Drink'),
		('Art & Photography'),
		('Self-help'),
		('History'),
		('True Crime'),
		('Religion & Spirituality'),
		('Humanities & Social Sciences'),
		('Science & Technology'),
		('Mathematics'),
		('Statistics'),
		('Computer Science'),
		('Education');
        
insert into publisher(publisher_name)
values  ('Macmillan'),
		('Simon and Schuster'),
		('Harper Collins'),
		('Hachette Book Group'),
		('Penguin/Random House');

insert into role(role_name)
values  ('student'),
		('lecturer'),
		('member'),
		('admin'),
		('librarian');

insert into book_type(type_name)
values  ('Article'),
		('Novel'),
		('Journal'),
		('Graphic Novel');