create database "info";
create table "users"
(
    id       bigint primary key,
    name     varchar(120) not null,
    surname  varchar(120) NOT NULL,
    password varchar(120) not null,
    phone    varchar(120),
    email    varchar(120),
    roles    varchar(120),
    active   boolean,
    image_id bigint,
    event_id bigint
);

create table "info"
(
    id     bigint primary key,
    title1 varchar(120) not null,
    title2 varchar(120) not null,
    title3 varchar(120) not null,
    teg1   varchar(120) not null,
    teg2   varchar(120) not null,
    teg3   varchar(120) not null
);

create table "image"
(
    id    bigint primary key,
    bytes bytea
);

create table "home"
(
    id      bigint primary key,
    text    varchar(20),
    start   timestamp,
    stop    timestamp,
    color   varchar(20),
    name    varchar(20),
    surname varchar(20),
    user_id bigint
);

create table "user_role"
(
    user_id bigint primary key,
    roles   varchar(120)
);

CREATE SEQUENCE hibernate_sequence START 1;
insert into "home" values (179,'Тематика','2023-04-11 14:00:00','2023-04-11 14:30:00',NULL,'Данила','Чистяков',153);
insert into "home" values (205,'Тематикс','2023-04-11 19:00:00','2023-04-11 19:30:00',NULL,'Илья','Друганов',154);
insert into "home" values (204,'Бронирование','2023-04-11 15:00:00','2023-04-11 15:33:00','green','Данила','Чистяков',153);
insert into "info" values (1,'Бронирование аудиторий – порой долгая и трудозатратная по времени процедура. Необходимо составить служебную записку, найти в нужное время подходящую аудиторию с оптимальной вместимостью и согласовать бронирование со всеми необходимыми инстанциями. Но благодаряму нашему сайты, вы сможете в разы быстрей забронировать аудиторию под внеучебные занятия. Обратите внимание, что помещения бронируются под внеучебные студенческие мероприятия. Для бронирования аудиторий для учебных групповых проектов и учебных мероприятий обращайтесь в администрацию своего факультета!','Проверьте, свободно ли время, на которое вы хотите забронировать аудиторию. Если время свободно, укажите тему посещения. Когда вы получите сообщение о том, что аудитория забронирована, проверьте, отображается ли ваше мероприятие в календаре событий.', 'Среди преимуществ — широкая география сервиса и низкая стоимость комиссии. Мы обещает, что заказы обходятся почти вполовину дешевле, чем у конкурентов.  возможность находить клиентов без оплаты. Кроме того, все занятия проходят на специализированной видеропотформе по внутреннему сформированному расписанию — репетитору не нужно подбирать удобную площадку для проведения занятий.','Бронирование аудиторий с целью проведения внеучебных занятий', 'Как забронировать аудиторию?
', 'Преимущества');
insert into "user_role" values (163,'USER');
insert into "users"(id, active, email, name, password, phone, surname, image_id, event_id) values (153, true, 'danil_chb2001@mail.ru', 'Данила', '$2a$08$iImLMSbbaVv3kSCTDqnS9.dI.Zgow65opWZFxY50tatZM.f4AdQcS', +79502479575, 'Чистяков', 162, NULL);