create table `user`(
    id varchar(36) primary key,
    email varchar(50) not null unique,
    document_id varchar(50) not null unique,
    full_name varchar(50) not null,
    password varchar(255) not null,
    created_date datetime not null
);

create table role(
    name varchar(50) primary key
);

create table user_roles(
    user_id varchar(36) not null,
    role_id varchar(36) not null,
    primary key (user_id, role_id),
    foreign key (user_id) references `user` (id),
    foreign key (role_id) references role (name)
);

create table wallet(
    id varchar(36) primary key,
    balance decimal(10, 2) not null,
    user_id varchar(36) not null,
    constraint fk_user foreign key (user_id) references `user` (id)
);

create table `transaction`(
    id varchar(36) not null,
    amount decimal(10, 2) not null,
    user_id varchar(36) not null,
    sender_wallet_id varchar(36) not null,
    receiver_wallet_id varchar(36) not null,
    created_date datetime not null,
    constraint fk_sender_wallet foreign key (sender_wallet_id) references wallet (id),
    constraint fk_receiver_wallet foreign key (receiver_wallet_id) references wallet (id)
);