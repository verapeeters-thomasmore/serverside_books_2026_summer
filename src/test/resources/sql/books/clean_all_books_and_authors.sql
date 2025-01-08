delete from  book_authors;

delete from  book;
alter table book alter column id restart with 1;

delete from  author;
alter table author alter column id restart with 1;

