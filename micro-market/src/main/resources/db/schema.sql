create table product
(
	id int auto_increment,
	name varchar(32) not null,
	type varchar(32) not null,
	constraint product_pk
		primary key (id)
);