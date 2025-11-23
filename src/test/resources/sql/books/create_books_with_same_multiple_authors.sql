-- 4 books
insert into book (TITLE)
values ('book 1 with 2 authors'); /*1*/

insert into book (TITLE)
values ('book 2 with 2 authors'); /*2*/

insert into book (TITLE)
values ('book 3 with only author 1'); /*3*/

insert into book (TITLE)
values ('book 4 with only author 2'); /*4*/

insert into book (TITLE)
values ('book 5 with only author 2'); /*4*/

-- 2 authors
insert into author (NAME)
values ('author 1'); /*1*/

insert into author (NAME)
values ('author 2'); /*2*/

--relations
insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'book 1 with 2 authors',
           select id from AUTHOR where name = 'author 1');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'book 1 with 2 authors',
           select id from AUTHOR where name = 'author 2');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'book 2 with 2 authors',
           select id from AUTHOR where name = 'author 1');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'book 2 with 2 authors',
           select id from AUTHOR where name = 'author 2');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'book 3 with only author 1',
           select id from AUTHOR where name = 'author 1');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'book 4 with only author 2',
           select id from AUTHOR where name = 'author 2');


insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'book 5 with only author 2',
           select id from AUTHOR where name = 'author 2');

