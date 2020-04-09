create table limitation
(
	id int auto_increment,
	type varchar(100) not null,
	min_age int not null,
	require_no_criminal bool not null,
	require_no_illness bool not null,
	constraint limitation_pk
		primary key (id)
);

create unique index limitation_type_uindex
	on limitation (type);

create table users
(
	id varchar(32) not null,
	date_of_birth date not null,
	mental_illness bool not null,
	criminal bool not null,
	constraint users_pk
		primary key (id)
);
