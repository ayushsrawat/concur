create table roles
(
    id    serial primary key,
    name  varchar(20) unique not null,
    level int default 3      not null
);

insert into roles (name, level) values ('admin', 1);
insert into roles (name, level) values ('seller', 2);
insert into roles (name, level) values ('customer', 3);

