create sequence hibernate_sequence start with 1 increment by 1;

create table hw
(
    id       bigint not null,
    filename varchar(255),
    solution varchar(2048) not null,
    title    varchar(255),
    user_id  bigint,
    primary key (id)
) engine = InnoDB;

create table user_role
(
    user_id bigint not null,
    roles   varchar(255)
) engine = InnoDB;

create table usr
(
    id       bigint not null,
    active   bit    not null,
    username varchar(255) not null,
    email varchar(255),
    password varchar(255) not null,
    primary key (id)
) engine = InnoDB;

alter table hw
    add constraint hw_user_fk
        foreign key (user_id) references usr (id);

alter table user_role
    add constraint user_role_user_fk
        foreign key (user_id) references usr (id);