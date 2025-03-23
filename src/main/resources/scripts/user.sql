CREATE TABLE USERS (
                       id serial PRIMARY KEY,
                       username VARCHAR(25) unique not null,
                       first_name VARCHAR(20) not null,
                       last_name VARCHAR(20),
                       password_hash text not null,
                       date_of_birth DATE not null,
                       phone varchar(15),
                       email text,
                       address text,
                       created_at TIMESTAMP not null default now(),
                       updated_at TIMESTAMP default now()
);

--
-- alter table users drop constraint users_age_check;
-- alter table users add constraint users_age_check CHECK ((age > 12));
--
-- alter table users alter column created_at set not null;
--
-- alter table users add column date_of_birth DATE not null default CURRENT_DATE;
-- alter table users alter column date_of_birth drop default;
-- alter table users drop column age;