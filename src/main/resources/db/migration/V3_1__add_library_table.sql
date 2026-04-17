create table library
(
    id   integer      not null GENERATED ALWAYS AS IDENTITY,
    LIBRARY_NAME varchar(255) not null,
    LOCATION varchar(255) not null,
    ESTABLISHED_YEAR integer not null,
    MANAGER_NAME varchar(255) not null,
    primary key (id)
);







