-- this file contains extra test data to add in the persistent database

insert into book (title) values ('Anderland: De Stad Van Gouden Schaduw'); /*7*/
insert into book (title) values ('Anderland:  Rivier van blauw vuur'); /*8*/
insert into book (title) values ('Anderland:  Berg van zwart glas'); /*9*/
insert into book (title)values ('Anderland:  Zee van zilveren licht'); /*10*/
insert into book (title) values ('Neuromancer'); /*11*/
insert into book (title) values ('Snow crash'); /*12*/
insert into book (title) values ('Rainbows’s end'); /*13*/
insert into book (title) values ('Hard-boiled wonderland en het einde van de wereld'); /*14*/
insert into book (title)values ('Ready Player one'); /*15*/
insert into book (title) values ('Refactoring'); /*16*/
insert into book (title) values ('Extreme Programming Explained'); /*17*/
insert into book (title) values ('Implementation patterns'); /*18*/
insert into book (title) values ('Clean code'); /*19*/

insert into author (name) values ('Tad Williams'); /*7*/
insert into author (name) values ('William Gibson'); /*8*/
insert into author (name) values ('Neal Stephenson'); /*9*/
insert into author (name) values ('Vernor Vinge'); /*10*/
insert into author (name) values ('Ernest Cline'); /*10*/
insert into author (name) values ('Martin Fowler'); /*11*/
insert into author (name) values ('Kent Beck'); /*12*/
insert into author (name) values ('Robert C Martin'); /*13*/


insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Anderland: De Stad Van Gouden Schaduw',
           select id from AUTHOR where name = 'Tad Williams');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Anderland:  Rivier van blauw vuur',
           select id from AUTHOR where name = 'Tad Williams');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Anderland:  Berg van zwart glas',
           select id from AUTHOR where name = 'Tad Williams');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Anderland:  Zee van zilveren licht',
           select id from AUTHOR where name = 'Tad Williams');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Neuromancer',
           select id from AUTHOR where name = 'William Gibson');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Snow crash',
           select id from AUTHOR where name = 'Neal Stephenson');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Rainbows’s end',
           select id from AUTHOR where name = 'Vernor Vinge');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Hard-boiled wonderland en het einde van de wereld',
           select id from AUTHOR where name = 'Haruki Murakami');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Ready Player one',
           select id from AUTHOR where name = 'Ernest Cline');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Refactoring',
           select id from AUTHOR where name = 'Martin Fowler');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Extreme Programming Explained',
           select id from AUTHOR where name = 'Kent Beck');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Implementation patterns',
           select id from AUTHOR where name = 'Kent Beck');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Clean code',
           select id from AUTHOR where name = 'Robert C Martin');

insert into SERIE(NAME) values ('Oryx and Crake');
insert into SERIE(NAME) values ('Harry Potter');
insert into SERIE(NAME) values ('In de ban van de Ring');

