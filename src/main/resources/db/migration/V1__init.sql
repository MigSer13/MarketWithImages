create table users
(
    id         bigserial primary key,
    username   varchar(30) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

create table categories
(
    id         bigserial primary key,
    title      varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories (title)
values ('Food');

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    url         varchar(255),
    price       numeric(8, 2) not null,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into products (title, price, category_id, url)
values ('Milk', 95, 1, 'https://clipart4school.com/wp-content/uploads/2018/03/free-milk-clipart-color-preview.jpg'),
       ('Bread', 28, 1, 'https://ic.pics.livejournal.com/irina_co/66663678/7981661/7981661_original.jpg'),
       ('Cheese', 420, 1, 'https://catherineasquithgallery.com/uploads/posts/2021-03/1614578272_25-p-sir-na-belom-fone-36.png'),
       ('Cheese2', 420, 1, 'https://catherineasquithgallery.com/uploads/posts/2021-03/1614578272_25-p-sir-na-belom-fone-36.png'),
       ('Cheese3', 420, 1, 'https://catherineasquithgallery.com/uploads/posts/2021-03/1614578272_25-p-sir-na-belom-fone-36.png'),
       ('Cheese4', 420, 1, 'https://catherineasquithgallery.com/uploads/posts/2021-03/1614578272_25-p-sir-na-belom-fone-36.png'),
       ('Cheese5', 420, 1, 'https://catherineasquithgallery.com/uploads/posts/2021-03/1614578272_25-p-sir-na-belom-fone-36.png'),
       ('Cheese6', 420, 1, 'https://catherineasquithgallery.com/uploads/posts/2021-03/1614578272_25-p-sir-na-belom-fone-36.png'),
       ('Cheese7', 420, 1, 'https://catherineasquithgallery.com/uploads/posts/2021-03/1614578272_25-p-sir-na-belom-fone-36.png'),
       ('Cheese8', 420, 1, 'https://catherineasquithgallery.com/uploads/posts/2021-03/1614578272_25-p-sir-na-belom-fone-36.png');

create table orders
(
    id         bigserial primary key,
    price      numeric(8, 2) not null,
    user_id    bigint references users (id),
    address    varchar(255),
    phone      varchar(32),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    price             numeric(8, 2) not null,
    price_per_product numeric(8, 2) not null,
    product_id        bigint references products (id),
    order_id          bigint references products (id),
    quantity          int,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);