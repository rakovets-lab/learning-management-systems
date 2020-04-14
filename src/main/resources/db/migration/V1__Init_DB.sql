create sequence hibernate_sequence start with 1 increment by 1;

create table hw
(
    hw_id    bigint        not null,
    filename varchar(255),
    solution varchar(2048) not null,
    title    varchar(255),
    user_id  bigint,
    primary key (hw_id)
) engine = InnoDB;

create table user_role
(
    user_id bigint not null,
    roles   varchar(255)
) engine = InnoDB;

create table usr
(
    user_id         bigint       not null auto_increment,
    active          bit          not null,
    username        varchar(255) not null,
    email           varchar(255),
    activation_code varchar(255),
    password        varchar(255) not null,
    primary key (user_id)
) engine = InnoDB;

create table learning_group
(
    group_id          bigint not null auto_increment,
    group_name        varchar(255) default null,
    group_leader_name bigint not null,
    primary key (group_id)
) engine = InnoDB
  default charset = utf8;

create table user_group
(
    user_id  bigint not null,
    group_id bigint not null,
    primary key (group_id, user_id)
) engine = InnoDB
  default charset = utf8;

create table solution
(
    id       bigint(20) not null,
    filename varchar(255) collate utf8mb4_unicode_ci default null,
    user_id  bigint(20)                              default null
) engine = InnoDB
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci;

alter table user_group
    add constraint user_group_id_user_fk
        foreign key (group_id) references learning_group (group_id);

alter table user_group
    add constraint user_id_group_user_fk
        foreign key (user_id) references usr (user_id);

alter table hw
    add constraint hw_user_fk
        foreign key (user_id) references usr (user_id);

alter table user_role
    add constraint user_role_user_fk
        foreign key (user_id) references usr (user_id);

alter table solution
    add primary key (id),
    add key FK9pwmf9mk2ssylkxfjyow4j6o4 (user_id);

alter table solution
    modify id bigint(20) not null auto_increment;

alter table solution
    add constraint FK9pwmf9mk2ssylkxfjyow4j6o4 foreign key (user_id) references usr (user_id);