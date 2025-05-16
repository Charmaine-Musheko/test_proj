use lms;
drop procedure if exists approve_reservation;
create procedure approve_reservation(
    `@reservation_id` int,
    `@isbn` varchar(50),
    `@student_id` varchar(50),
    `@member_id` varchar(50),
    `@lecturer_id` varchar(50)
)
begin
    insert into borrowed_books(isbn, student_id, member_id, lecturer_id, date_borrowed)
        values (`@isbn`, `@student_id`, `@member_id`, `@lecturer_id`, current_timestamp);

    update book
    set book_status = 3
    where isbn = `@isbn`;

    delete from reserved_books
        where isbn = `@isbn` and reservation_id = `@reservation_id`;

end;

drop procedure if exists deny_reservation;
create procedure deny_reservation(
    `@reservation_id` int,
    `@isbn` varchar(50)
)
begin
    update book
    set book_status = 1
    where isbn = `@isbn`;

    delete from reserved_books
    where isbn = `@isbn` and reservation_id = `@reservation_id`;
end;
