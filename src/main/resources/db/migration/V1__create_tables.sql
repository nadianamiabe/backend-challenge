create table application_user(
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

create table application_user_roles(
    application_user_id varchar(36) not null,
    roles_name varchar(50) not null,
    primary key (application_user_id, roles_name),
    foreign key (application_user_id) references application_user (id),
    foreign key (roles_name) references role (name)
);

create table wallet(
    id varchar(36) primary key,
    balance decimal(10, 2) not null,
    application_user_id varchar(36) not null,
    constraint fk_user foreign key (application_user_id) references application_user (id)
);

create table `transaction`(
    id varchar(36) not null,
    amount decimal(10, 2) not null,
--     application_user_id varchar(36) not null,
    status varchar(20) not null,
    sender_wallet_id varchar(36) not null,
    receiver_wallet_id varchar(36) not null,
    created_date datetime not null,
    constraint fk_sender_wallet foreign key (sender_wallet_id) references wallet (id),
    constraint fk_receiver_wallet foreign key (receiver_wallet_id) references wallet (id)
);