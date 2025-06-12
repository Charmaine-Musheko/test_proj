USE lms;
DROP PROCEDURE IF EXISTS add_new_book;

DELIMITER //

CREATE PROCEDURE add_new_book(
    IN p_title VARCHAR(255),
    IN p_year_published YEAR,
    IN p_edition INT,
    IN p_author VARCHAR(150),
    IN p_publisher VARCHAR(100),
    IN p_type VARCHAR(100),
    IN p_genre VARCHAR(50),
    OUT message TEXT
)
BEGIN
    DECLARE existing_author_id INT;
    DECLARE existing_genre_id INT;
    DECLARE existing_publisher_id INT;
    DECLARE existing_book_type_id INT;
    DECLARE book_id VARCHAR(20);

    -- Get or create author
    SELECT author_id INTO existing_author_id
    FROM author
    WHERE author_name = p_author
    LIMIT 1;

    IF existing_author_id IS NULL THEN
        INSERT INTO author(author_name) VALUES (p_author);
        SET existing_author_id = LAST_INSERT_ID();
    END IF;

    -- Get or create genre
    SELECT genre_id INTO existing_genre_id
    FROM genre
    WHERE genre_name = p_genre
    LIMIT 1;

    IF existing_genre_id IS NULL THEN
        INSERT INTO genre(genre_name) VALUES (p_genre);
        SET existing_genre_id = LAST_INSERT_ID();
    END IF;

    -- Get or create publisher
    SELECT publisher_id INTO existing_publisher_id
    FROM publisher
    WHERE publisher_name = p_publisher
    LIMIT 1;

    IF existing_publisher_id IS NULL THEN
        INSERT INTO publisher(publisher_name) VALUES (p_publisher);
        SET existing_publisher_id = LAST_INSERT_ID();
    END IF;

    -- Get or create book type
    SELECT type_id INTO existing_book_type_id
    FROM book_type
    WHERE type_name = p_type
    LIMIT 1;

    IF existing_book_type_id IS NULL THEN
        INSERT INTO book_type(type_name) VALUES (p_type);
        SET existing_book_type_id = LAST_INSERT_ID();
    END IF;

    -- Generate book_id
    SET book_id = CONCAT('BID', FLOOR(RAND() * 1000000), UPPER(LEFT(p_author, 3)));

    -- Insert book
    INSERT INTO book(isbn, book_status, publisher_id, type_id, book_title, edition, year_published)
    VALUES(book_id, 1, existing_publisher_id, existing_book_type_id, p_title, p_edition, p_year_published);

    -- Insert book-genre relationship
    INSERT INTO book_genre(genre_id, isbn)
    VALUES (existing_genre_id, book_id);

    -- Insert book-author relationship
    INSERT INTO book_author(author_id, isbn)
    VALUES (existing_author_id, book_id);

    SET message = CONCAT('Book added with ID: ', book_id);
END;
//

DELIMITER ;
