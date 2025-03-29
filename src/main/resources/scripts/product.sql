create table product_category
(
    id          serial primary key,
    category_name        varchar(50) not null unique,
    description text        not null,
    created_at  TIMESTAMP   not null default now(),
    updated_at  TIMESTAMP            default now()
);

create table products
(
    id          serial primary key,
    product_name        varchar(100)            not null unique,
    description text,
    price       decimal(10, 2)          not null check (price >= 0),
    thumbnail   text,
    created_at  TIMESTAMP default now() not null,
    updated_at  TIMESTAMP default now()
);

create table product_category_mapping
(
    product_id int,
    category_id int,
    primary key (product_id, category_id),
    foreign key (product_id) references products(id) on delete cascade,
    foreign key (category_id) references product_category(id) on delete cascade
);

create table product_attributes
(
    id              serial primary key,
    product_id      int          not null,
    attribute_name  varchar(50)  not null,
    attribute_value VARCHAR(255) not null,
    attribute_type  VARCHAR(20)  NOT NULL CHECK (
        attribute_type IN ('string', 'number', 'boolean', 'date', 'json')
        ),
    created_at      TIMESTAMP    not null default now(),
    updated_at      TIMESTAMP             default now(),
    foreign key (product_id) references products (id) on delete cascade
);

-- following queries are not meant to execute
-- alter table product_category rename name to category_name;
-- alter table products rename name to product_name;
