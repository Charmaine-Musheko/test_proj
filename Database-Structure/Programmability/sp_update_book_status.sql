use lms;
drop procedure if exists update_book_status;
create procedure update_book_status
    (
    book_id varchar(20),
    new_status int
    )
begin
    update book
        set book_status = new_status
    where book.isbn = book_id;
end;