delete from award_books;

delete from award;
alter table award alter column id restart with 1;

delete from book;
alter table book alter column id restart with 1;