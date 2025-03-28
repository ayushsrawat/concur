create table user_roles
(
    id    serial primary key,
    role_name  varchar(20) unique not null,
    role_level int default 3      not null
);

insert into user_roles (role_name, role_level)values ('ADMIN', 1);
insert into user_roles (role_name, role_level)values ('SELLER', 2);
insert into user_roles (role_name, role_level)values ('CUSTOMER', 3);


CREATE TABLE USERS
(
    id            serial PRIMARY KEY,
    username      VARCHAR(25) unique not null,
    first_name    VARCHAR(20)        not null,
    last_name     VARCHAR(20),
    password_hash text               not null,
    date_of_birth DATE               not null,
    phone         varchar(15),
    email         text,
    address       text,
    role_id       int                         default 3,
    created_at    TIMESTAMP          not null default now(),
    updated_at    TIMESTAMP                   default now()
);

alter table users add constraint fk_users_role foreign key (role_id) references user_roles(id);

-- followings are not to be executed
-- alter table users drop constraint users_age_check;
-- alter table users add constraint users_age_check CHECK ((age > 12));
-- alter table users alter column created_at set not null;
-- alter table users add column date_of_birth DATE not null default CURRENT_DATE;
-- alter table users alter column date_of_birth drop default;
-- alter table users drop column age;
-- alter table users add column role_id int default 3 not null;
-- alter table user_roles rename name to role_name;
-- alter table user_roles rename level to role_level;