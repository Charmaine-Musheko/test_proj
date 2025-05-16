
use lms;
drop procedure if exists approve_return;
create procedure approve_return(
    `@return_id` int,
    `@isbn` varchar(50)
)
begin
    update book
    set book_status = 1
    where isbn = `@isbn`;

    delete from returned_books
    where isbn = `@isbn` and return_id = `@return_id`;

    delete from borrowed_books
    where isbn = `@isbn`;


end;

drop procedure if exists deny_return;
create procedure deny_return(
    `@return_id` int,
    `@isbn` varchar(50)
)
begin
    update book
    set book_status = 3
    where isbn = `@isbn`;

    delete from returned_books
    where isbn = `@isbn` and return_id = `@return_id`;

end;
