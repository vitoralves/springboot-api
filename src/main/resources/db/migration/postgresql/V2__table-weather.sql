create table weather (
id serial,
max_temp double precision,
min_temp double precision,
cliente integer,
primary key(id),
foreign key(cliente) references cliente(id) on delete cascade);
