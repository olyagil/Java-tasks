create table "users"(
id serial ,
login varchar (255) not null ,
password varchar (255) not null ,
constraint pk_user primary key (id),
constraint  idx_login_unique unique (login)
);

create table "country"(
id serial ,
country_name varchar (100) not null ,
constraint pk_country primary key (id),
constraint name_unique unique ("country_name")
);

create table "hotel"(
id serial,
hotel_name varchar (255) not null ,
stars decimal not null ,
website varchar (255) not null,
latitude varchar (50) not null,
longitude varchar (50) not null,
constraint pk_hotel primary key (id),
constraint stars_check check (stars between 0 and  10)
);

create table hotel_features (
hotel_id int4 not null,
features varchar (255) not null,
constraint hotel_features_fk foreign key (hotel_id)
references hotel(id) match simple
on delete cascade on update cascade
);

create table "tour"(
id serial ,
photo varchar (50) not null,
tour_date date not null,
duration numeric not null,
description varchar (255) not null ,
cost numeric  not null ,
tour_type smallint not null ,
hotel_id int not null,
country_id int not null,
constraint tour_type_check check (tour_type between 1 and 10),
constraint tour_pk primary key (id),
constraint tour_hotel_id_fk foreign key (hotel_id)
references hotel(id) match simple
on delete cascade on update cascade ,
constraint tour_country_id_fk foreign key (country_id)
references country (id) match simple
on delete cascade on update cascade
);

create table "review"(
id serial ,
review_date date default current_date ,
review_text varchar (255) not null ,
user_id int not null,
tour_id int not null,
constraint pk_review primary key (id),
constraint review_user_id_fk foreign key (user_id)
references "users"(id) match simple
on delete cascade on update cascade ,
constraint review_tour_id_fk foreign key (tour_id)
references tour(id) match simple
on delete cascade on update cascade
);

create table user_tour(
user_id int not null,
tour_id int not null,
constraint user_tour_user_id_fk foreign key (user_id)
references "users"(id) match simple
on delete cascade on update cascade ,
constraint user_tour_id_fk foreign key (tour_id)
references tour(id) match simple
on delete cascade on update cascade
);
