drop view if exists lib_view_all_books;
create view lib_view_all_books as
select book.isbn, book.book_title, a.author_name, book.edition, p.publisher_name, book.year_published, bs.status_name
from book
inner join publisher p on p.publisher_id = book.publisher_id
inner join book_status bs on bs.status_id = book.book_status
inner join book_author ba on book.isbn = ba.isbn
inner join author a on ba.author_id = a.author_id;

drop view if exists view_available_books;
create view view_available_books as
select book.isbn, book.book_title, a.author_name, book.edition, p.publisher_name, book.year_published
from book
         inner join publisher p on p.publisher_id = book.publisher_id
         inner join book_status bs on bs.status_id = book.book_status
         inner join book_author ba on book.isbn = ba.isbn
         inner join author a on ba.author_id = a.author_id
where status_id = 1;
