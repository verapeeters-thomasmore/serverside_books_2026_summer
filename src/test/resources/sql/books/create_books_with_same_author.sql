insert into book (TITLE, DESCRIPTION)
values ('Test Automation part1', 'Test Automation Description part1'); /*1*/

insert into book (TITLE, DESCRIPTION)
values ('Test Automation part2', 'Test Automation Description part2'); /*2*/

insert into book (TITLE, DESCRIPTION)
values ('Test Automation part3', 'Test Automation Description part3'); /*3*/

insert into author (NAME, DESCRIPTION, COUNTRY)
values ('Lasse Koskela', 'Description Lasse Koskela', 'Germany'); /*1*/


insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Test Automation part1',
           select id from AUTHOR where name = 'Lasse Koskela');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Test Automation part2',
           select id from AUTHOR where name = 'Lasse Koskela');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Test Automation part3',
           select id from AUTHOR where name = 'Lasse Koskela');
