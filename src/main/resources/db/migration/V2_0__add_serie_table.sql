create table serie
(
    id   integer      not null GENERATED ALWAYS AS IDENTITY,
    name varchar(255) not null,
    primary key (id)
);
