insert into author (NAME, DESCRIPTION, COUNTRY)
values ('Thomas Mann', 'Description Thomas Mann', 'Germany'); /*1*/

insert into book (TITLE, DESCRIPTION)
values ('Test Automation', 'Test Automation Description'); /*1*/

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Test Automation',
           select id from AUTHOR where name = 'Thomas Mann');
