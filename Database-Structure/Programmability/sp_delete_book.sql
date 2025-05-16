create
    definer = libman@localhost procedure delete_book(IN `@isbn` varchar(50), OUT message varchar(150))
begin
    if exists(select isbn from book where isbn = `@isbn` and (book_status = 1 or book_status = 2 or book_status = 4)) then
        delete from book
            where isbn = `@isbn`;
        set message = 'Book Successfully Deleted';
    else
        set message = 'Book CANNOT be deleted due to its status';
    end if;
    select message;
end;
