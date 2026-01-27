create table award_books
(
    id         integer not null GENERATED ALWAYS AS IDENTITY,
    book_id    integer not null,
    award_id   integer not null,
    award_year varchar(255),
    primary key (id)
);