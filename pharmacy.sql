create database Pharmacy;
use Pharmacy;

create table Admin(
	id int auto_increment primary key,
    username varchar(100) not null,
    password varchar(50) not null
);

insert into Admin(username, password) VALUES ('admin', '123456');

CREATE TABLE Customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    phoneNum VARCHAR(20) NOT NULL,
    registrationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- total DOUBLE DEFAULT 0.00,
    loyaltyPoints INT DEFAULT 0
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

drop table Customer;

select * from Admin;
select * from Customer;
select * from medicine;