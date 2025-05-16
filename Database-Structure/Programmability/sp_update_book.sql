create
    definer = libman@localhost procedure update_book(IN `@isbn` varchar(50), IN `@title` varchar(255),
                                                     IN `@edition` int, IN `@year_published` int)
begin
    update book
        set book_title = `@title`,
                         edition = `@edition`,
                         year_published = `@year_published`
    where isbn = `@isbn`;
end;