create table member
(
    id            integer      not null GENERATED ALWAYS AS IDENTITY,
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    address       varchar(255) not null,
    city          varchar(255) not null,
    member_number varchar(255) not null,
    primary key (id)
);
