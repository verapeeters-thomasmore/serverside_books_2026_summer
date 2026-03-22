insert into BOOK (TITLE, DESCRIPTION)
values ('Oryx and Crake',
        'MaddAddam is a serie of 3 dystopian science-fiction novels that deals with extreme genetic engineering.'); /*1*/

insert into BOOK (TITLE, DESCRIPTION)
values ('The year of the flood',
        'MaddAddam is a serie of 3 dystopian science-fiction novels that deals with extreme genetic engineering.');/*2*/

insert into BOOK (TITLE, DESCRIPTION)
values ('MaddAddam',
        'MaddAddam is a serie of 3 dystopian science-fiction novels that deals with extreme genetic engineering.');/*3*/

insert into BOOK (TITLE, DESCRIPTION)
values ('1Q84',
        'Set in 1984 in Tokyo, the story concerns an assassin who stumbles upon an alternate world she refers to as 1Q84. There, she becomes embroiled in a conspiracy involving an abusive religious cult.');/*4*/

insert into BOOK (TITLE, DESCRIPTION)
values ('De opwindvogelkronieken',
        'Novel about Toru, a bored young man living a basic life in Tokyo. When Toru’s daily routines are interrupted by increasingly odd and chaotic events, he must undergo a metaphysical journey that tests the limits of free will and corporeality. ');/*5*/

insert into BOOK (TITLE)
values ('Design Patterns');/*6*/

insert into author (NAME, DESCRIPTION, COUNTRY)
values ('Margaret Atwood',
        'Margaret Atwood was born in 1939 in Ottawa and grew up in northern Ontario, Quebec, and Toronto. Throughout her writing career, Margaret Atwood has received numerous awards and honourary degrees.',
        'Canada'); /*1*/

insert into author (NAME, DESCRIPTION, COUNTRY)
values ('Haruki Murakami',
        'Haruki Murakami  is a popular contemporary Japanese writer and translator. His work has been described as ''easily accessible, yet profoundly complex''.',
        'Japan'); /*2*/

insert into author (NAME, DESCRIPTION, COUNTRY)
values ('Erich Gamma', 'One of the ''Gang of four''.', 'Switzerland'); /*3*/

insert into author (NAME, DESCRIPTION, COUNTRY)
values ('Richard Helm', 'One of the ''Gang of four''.', 'Australia'); /*4*/

insert into author (NAME, DESCRIPTION, COUNTRY)
values ('Ralph Johnson', 'One of the ''Gang of four''.', 'USA'); /*5*/

insert into author (NAME, DESCRIPTION, COUNTRY)
values ('John Vlissides', 'One of the ''Gang of four''.', 'USA'); /*6*/

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Oryx and Crake',
           select id from AUTHOR where name = 'Margaret Atwood');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'The year of the flood',
           select id from AUTHOR where name = 'Margaret Atwood');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'MaddAddam',
           select id from AUTHOR where name = 'Margaret Atwood');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= '1Q84',
           select id from AUTHOR where name = 'Haruki Murakami');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'De opwindvogelkronieken',
           select id from AUTHOR where name = 'Haruki Murakami');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Design Patterns',
           select id from AUTHOR where name = 'Erich Gamma');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Design Patterns',
           select id from AUTHOR where name = 'Richard Helm');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Design Patterns',
           select id from AUTHOR where name = 'Ralph Johnson');

insert into BOOK_AUTHORS (BOOKS_ID, AUTHORS_ID)
values (select id from BOOK where title= 'Design Patterns',
           select id from AUTHOR where name = 'John Vlissides');

insert into GENRE(NAME)
values ('fantasy');

insert into GENRE(NAME)
values ('non-fiction');

insert into GENRE(NAME)
values ('programming');

INSERT INTO BOOKSUSER (USERNAME, PASSWORD, ROLE)
VALUES ('admin', '$2a$10$9MIX8kYPkuB7uE/H5nHF8.KG6.YdjBA/voOnjSZnZDxLXL/2BIerS', 'ADMIN'); -- admin

INSERT INTO BOOKSUSER (USERNAME, PASSWORD, ROLE)
VALUES ('marie', '$2a$10$9TeBFudS7HsgCa4sSvP//O627sMq.KiTFrOr8IzrVlYw5c8aoKzNm', 'USER'); -- password

INSERT INTO BOOKSUSER (USERNAME, PASSWORD, ROLE)
VALUES ('vera', '$2y$12$KF3spKP4kgf59.6zYkmjyeYaW2.4ZxV16Grpw1FPsFnzYq68kswJ6', 'USER'); -- vera

insert into SERIE(NAME)
values ('Harry Potter');
insert into SERIE(NAME)
values ('Anderland');


INSERT INTO LIBRARY (ID, LIBRARY_NAME, LOCATION, ESTABLISHED_YEAR, MANAGER_NAME)
VALUES (1, 'Bib Mechelen', 'Mechelen', 1920, 'Vera Peeters');
INSERT INTO LIBRARY (ID, LIBRARY_NAME, LOCATION, ESTABLISHED_YEAR, MANAGER_NAME)
VALUES (2, 'Permeke Bibliotheek', 'Antwerpen', 2005, 'Luc Janssens');
INSERT INTO LIBRARY (ID, LIBRARY_NAME, LOCATION, ESTABLISHED_YEAR, MANAGER_NAME)
VALUES (3, 'De Krook', 'Gent', 2017, 'Annelies De Smet');
INSERT INTO LIBRARY (ID, LIBRARY_NAME, LOCATION, ESTABLISHED_YEAR, MANAGER_NAME)
VALUES (4, 'Bib Leuven', 'Leuven', 1860, 'Pieter Willems');
INSERT INTO LIBRARY (ID, LIBRARY_NAME, LOCATION, ESTABLISHED_YEAR, MANAGER_NAME)
VALUES (5, 'Muntpunt', 'Brussel', 2011, 'Sarah Mertens');
INSERT INTO LIBRARY (ID, LIBRARY_NAME, LOCATION, ESTABLISHED_YEAR, MANAGER_NAME)
VALUES (6, 'Openbare Bibliotheek Brugge', 'Brugge', 1798, 'Jan Van Damme');
INSERT INTO LIBRARY (ID, LIBRARY_NAME, LOCATION, ESTABLISHED_YEAR, MANAGER_NAME)
VALUES (7, 'Bib Hasselt', 'Hasselt', 1946, 'Ellen Claes');
INSERT INTO LIBRARY (ID, LIBRARY_NAME, LOCATION, ESTABLISHED_YEAR, MANAGER_NAME)
VALUES (8, 'ARhus', 'Roeselare', 2014, 'Tom Verstraete');
INSERT INTO LIBRARY (ID, LIBRARY_NAME, LOCATION, ESTABLISHED_YEAR, MANAGER_NAME)
VALUES (9, 'Bibliotheek Kortrijk', 'Kortrijk', 1900, 'Marie Dubois');
INSERT INTO LIBRARY (ID, LIBRARY_NAME, LOCATION, ESTABLISHED_YEAR, MANAGER_NAME)
VALUES (10, 'Bib Turnhout', 'Turnhout', 1955, 'Koen Maes');

