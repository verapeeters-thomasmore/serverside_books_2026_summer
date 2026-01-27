create table award
(
    id   integer      not null GENERATED ALWAYS AS IDENTITY,
    award_name varchar(255) not null,
    country varchar(255),
    prize_money double,
    genre_focus varchar(255),
    organization varchar(255),
    primary key (id)
);
