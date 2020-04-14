create table user_group
(
    user_id bigint not null,
    group_id bigint not null,
    primary key (group_id, user_id),
    key group_id (group_id),
    constraint user_group_ibfk_1
    foreign key (user_id) references usr (id),
    constraint user_group_ibfk_2
    foreign key (group_id) references learning_group (group_id)
) engine = InnoDB default charset = utf8;