create table "users"(
    id bigint primary key ,
    name varchar(120) not null,
    surname varchar(120) NOT NULL,
    password varchar(120) not null,
    phone varchar(120),
    email varchar(120),
    roles varchar(120),
    active boolean,
    image_id bigint
);

create table "info"(
    id bigint primary key ,
    title1 varchar(20) not null,
    title2 varchar(20) not null,
    title3 varchar(20) not null,
    teg1 varchar(20) not null,
    teg2 varchar(20) not null,
    teg3 varchar(20) not null
);

create table "image"(
    id bigint primary key,
    bytes bytea
);

create table "home"(
    id bigint primary key,
    text varchar(20),
    start timestamp,
    stop timestamp,
    color varchar(20)
);

create table "user_role"(
    user_id bigint primary key,
    roles varchar(120)
);

CREATE SEQUENCE hibernate_sequence START 1;