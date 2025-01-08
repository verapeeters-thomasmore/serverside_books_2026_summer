insert into book (TITLE, DESCRIPTION)
values ('Test Automation', 'Test Automation Description'); /*1*/

insert into author (NAME, DESCRIPTION, COUNTRY)
values ('Lasse Koskela', 'Description Lasse Koskela', 'Germany'); /*1*/

insert into author (NAME, DESCRIPTION, COUNTRY)
values ('Lisa Crispin', 'Description Lisa Crispin', 'USA'); /*1*/


insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Test Automation',
           select id from AUTHOR where name = 'Lasse Koskela');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Test Automation',
           select id from AUTHOR where name = 'Lisa Crispin');
