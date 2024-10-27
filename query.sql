create database Pharmacy;
use Pharmacy;

create table Admin(
	id int auto_increment primary key,
    username varchar(100) not null,
    password varchar(50) not null
);

create table medicine(
	id int auto_increment primary key,
    medicine_id varchar(100) not null,
    productName varchar(100) not null,
    category varchar(100) not null,
    quantity int not null,
    price varchar(100) not null,
    status varchar(100) not null
);