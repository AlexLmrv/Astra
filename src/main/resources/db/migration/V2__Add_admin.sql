insert into usr(id, username, password, active)
values (1, 'admin', '!Astra0122', true);

insert into user_role(user_id, roles)
values (1, 'USER') , (1, 'ADMIN');
