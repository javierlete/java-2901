delete from authorities;
delete from users;

insert into users (username, password, enabled)
values ('javier@email.net', '$2a$14$nuW2b9w9ba9KqpWn6sTVV.EelyNSzGEWBjV/8.tEDH65Z0Ot4NF7O', true);

insert into users (username, password, enabled)
values ('pepe@email.net', '$2a$14$xZe0MoWGsqWFtdtEi6FY1uAXt0AZGuixboXj1KGV4i0YTXudmyoi2', true);

insert into users (username, password, enabled)
values ('juan@email.net', '$2a$14$LVKlGQJocCOmXv0k0nrnNuoWVNAcT7sNrjKWEUv3MDnMx7l0E.KYG', true);

insert into authorities (username, authority)
values ('javier@email.net', 'ROLE_ADMINISTRADOR');

insert into authorities (username, authority)
values ('javier@email.net', 'ROLE_USUARIO');

insert into authorities (username, authority)
values ('pepe@email.net', 'ROLE_USUARIO');

insert into authorities (username, authority)
values ('juan@email.net', 'ROLE_USUARIO');

