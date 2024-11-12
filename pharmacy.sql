create database Pharmacy;
use Pharmacy;

create table Admin(
	id int auto_increment primary key,
    username varchar(100) not null,
    password varchar(50) not null
);

insert into Admin(username, password) VALUES ('admin', '123456');

create table Customer (
    id int auto_increment primary key,
    fullName varchar(100) not null,
    phoneNum varchar(20) not null,
    registrationDate timestamp default current_timestamp,
    -- total DOUBLE DEFAULT 0.00,
    loyaltyPoints INT DEFAULT 0
);

create table medicine(
	id int auto_increment primary key,
    medicine_id varchar(100) not null,
    productName varchar(100) not null,
    category varchar(100) not null,
    quantity int not null,
    price int not null,
    status varchar(100) not null
);

select * from Admin;
select * from Customer;
select * from medicine;