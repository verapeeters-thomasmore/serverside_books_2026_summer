insert into serie (NAME)
values ('Programming in C'); /*1*/

insert into book (TITLE, SERIE_ID, NUMBER_IN_SERIE)
values ('Programming in C the basics',
        select id from SERIE where name='Programming in C', 1); /*1*/

insert into book (TITLE, SERIE_ID, NUMBER_IN_SERIE)
values ('Programming in C advanced',
        select id from SERIE where name='Programming in C', 2); /*1*/

