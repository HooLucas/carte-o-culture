drop database if exists carteoculture;
create database carteoculture;
use carteoculture;

CREATE TABLE livre (
    id_book int NOT NULL AUTO_INCREMENT,
    title  varchar(255) NOT NULL,
    author varchar(255) NOT NULL,
    cover  varchar(255) NOT NULL,
    PRIMARY KEY (id_book)
);

create table chapitre (
    id_chapter int NOT NULL AUTO_INCREMENT,
	id_book int not null,
    nom varchar(255) not null,
	primary key(id_chapter)
);


create table page (
	id_page int NOT NULL AUTO_INCREMENT,
	id_chapter int not null,
	numero int not null,
    texte longtext not null,
	primary key(id_page)
);

create table ville (
	id int NOT NULL AUTO_INCREMENT,
	nom varchar(255),
    latitude FLOAT(25),
    longitude FLOAT(25),
    PRIMARY KEY (id)
);