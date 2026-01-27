INSERT INTO AWARD(AWARD_NAME, COUNTRY, PRIZE_MONEY, GENRE_FOCUS, ORGANIZATION)
values ('Nobelprijs voor Literatuur', 'Zweden', 1000000.0, 'Algemeen', 'Zweedse Academie');

insert into BOOK (TITLE, DESCRIPTION)
values ('Oryx and Crake',
        'MaddAddam is a serie of 3 dystopian science-fiction novels that deals with extreme genetic engineering.'),
       ('Pattern Hatching: Design Patterns Applied', 'Description Random');

INSERT INTO AWARD_BOOKS (AWARD_ID, BOOK_ID, AWARD_YEAR)
values (select id from award where award_name = 'Nobelprijs voor Literatuur',
           select id from book where title = 'Oryx and Crake',
               2004);
