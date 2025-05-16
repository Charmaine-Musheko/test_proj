
USE lms;
DROP PROCEDURE IF EXISTS add_new_book;
CREATE PROCEDURE `add_new_book` (
    `@title` varchar(255),
    `@year_published` year,
    `@edition` int,
    `@author` varchar(150),
    `@publisher` varchar(100),
    `@type` varchar(100),
    `@genre` varchar(50),
    out message text
)
BEGIN
    declare existing_author_id int;
    declare existing_genre_id int;
    declare existing_publisher_id int;
    declare existing_book_type_id int;
    declare book_id varchar(20) ;

    SET @@SESSION.max_sp_recursion_depth=10;

    if exists(select author_id from lms.author where author_name like `@author`)
    then
-- if the author exists store its id in the variable existing_author_id
        select author_id into existing_author_id  from lms.author where author_name like `@author`;

        set book_id = CONCAT('BID', LEFT(RAND()*1000000000+9999999999, 7) , UPPER(left(`@author`, 3)));
        -- If the created ID exists make a new one
        -- while (exists(select COUNT(isbn) from book WHERE isbn = book_id)) DO
        --     begin
        --         set book_id = CONCAT('BID', LEFT(RAND()*1000000000+9999999999, 7) , UPPER(left(`@author`, 3)));
        --     end;
        -- end while;

        if exists(select genre_id from lms.genre where genre_name like `@genre`)
        then
            -- if the genre exists store its id in the variable existing_genre_id
            select genre_id into existing_genre_id from lms.genre where genre_name like `@genre`;

            if exists(select publisher_id from publisher where publisher_name like `@publisher`)
                then
                    select publisher_id into existing_publisher_id from publisher where publisher_name like `@publisher`;

                    if exists(select type_id from book_type where type_name like `@type`)
                        then
                        select type_id into existing_book_type_id from book_type where type_name like `@type`;

                        INSERT INTO `lms`.`book`(`isbn`, `book_status`, `publisher_id`, `type_id`, `book_title`, `edition`, `year_published`)
                        VALUES(book_id,
                               1,
                               existing_publisher_id,
                               existing_book_type_id,
                               `@title`,
                               `@edition`,
                               `@year_published`);
                        INSERT INTO `lms`.`book_genre` (`genre_id`, `isbn`)
                        VALUES (existing_genre_id, book_id);
                        INSERT INTO `lms`.`book_author`(author_id, isbn)
                        VALUES (existing_author_id, book_id);
                    else
                        insert into book_type(type_name) values (`@type`);
                        call add_new_book(`@title`, `@year_published`, `@edition`, `@author`, `@publisher`, `@type`, `@genre`);
                    end if;
            else
                insert into publisher(publisher_name) values (`@publisher`);
                call add_new_book(`@title`, `@year_published`, `@edition`, `@author`, `@publisher`, `@type`, `@genre`);
            end if;
        else
--  Otherwise create a new Genre and call the method again
            insert into `lms`.`genre`(genre_name)
            values (`@genre`);
            call add_new_book(`@title`, `@year_published`, `@edition`, `@author`, `@publisher`, `@type`, `@genre`);
        end if;
    else
        --            Otherwise create a new Author and call the method again
        insert into `lms`.`author`(author_name) values (`@author`);
        call add_new_book(`@title`, `@year_published`, `@edition`, `@author`, `@publisher`, `@type`, `@genre`);
    end if;
END;


