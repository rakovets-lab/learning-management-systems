create table learning_group
(
    group_id        bigint not null auto_increment,
    group_name varchar(255) default null,
    group_leader_name bigint not null,
    primary key (group_id)
) engine = InnoDB default charset = utf8;