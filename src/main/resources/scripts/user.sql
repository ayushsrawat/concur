CREATE TABLE USERS (
                       id serial PRIMARY KEY,
                       username VARCHAR(25) unique not null,
                       first_name VARCHAR(20) not null,
                       last_name VARCHAR(20),
                       password_hash text not null,
                       age int check (age>12),
                       phone varchar(15),
                       email text,
                       address text,
                       created_at TIMESTAMP not null default now(),
                       updated_at TIMESTAMP default now()
);