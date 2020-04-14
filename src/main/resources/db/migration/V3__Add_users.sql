insert into usr (user_id, username, password, active)
values (2, 'teacher1', '$2y$08$3F4NZRyuhlwH3uQ8GLecp.e0zhdzpe//PjbGdQCOzxPJnFyMSTwlO', true);
insert into user_role (user_id, roles)
values (2, 'TEACHER');

insert into usr (user_id, username, password, active)
values (3, 'teacher2', '$2y$08$3F4NZRyuhlwH3uQ8GLecp.e0zhdzpe//PjbGdQCOzxPJnFyMSTwlO', true);
insert into user_role (user_id, roles)
values (3, 'TEACHER');

insert into usr (user_id, username, password, active)
values (4, 'student1', '$2y$08$3F4NZRyuhlwH3uQ8GLecp.e0zhdzpe//PjbGdQCOzxPJnFyMSTwlO', true);
insert into user_role (user_id, roles)
values (4, 'USER');

insert into usr (user_id, username, password, active)
values (5, 'student2', '$2y$08$3F4NZRyuhlwH3uQ8GLecp.e0zhdzpe//PjbGdQCOzxPJnFyMSTwlO', true);
insert into user_role (user_id, roles)
values (5, 'USER')