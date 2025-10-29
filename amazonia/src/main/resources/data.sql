insert or ignore into users (username, password, enabled)
values ('javier@email.net', 'javier', true);

insert or ignore into users (username, password, enabled)
values ('pepe@email.net', 'pepe', true);

insert or ignore into users (username, password, enabled)
values ('juan@email.net', 'juan', true);

insert or ignore into authorities (username, authority)
values ('javier@email.net', 'ROLE_ADMINISTRADOR');

insert or ignore into authorities (username, authority)
values ('javier@email.net', 'ROLE_USUARIO');

insert or ignore into authorities (username, authority)
values ('pepe@email.net', 'ROLE_USUARIO');

insert or ignore into authorities (username, authority)
values ('juan@email.net', 'ROLE_USUARIO');

