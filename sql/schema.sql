drop database if exists acme_bank;

create database acme_bank;

use acme_bank;

-- create table accounts --
create table accounts (
account_id varchar(10) not null,
name char(64) not null,
balance decimal(8,2) not null,
constraint pk_account_id primary key (account_id)
);

-- insert data.csv into the table accounts -- 
insert into accounts (account_id, name, balance) 
values ('V9L3Jd1BBI', 'fred', '100.00');

insert into accounts (account_id, name, balance) 
values ('fhRq46Y6vB', 'barney', '300.00');

insert into accounts (account_id, name, balance) 
values ('uFSFRqUpJy', 'wilma', '1000.00');

insert into accounts (account_id, name, balance) 
values ('ckTV56axff', 'betty', '1000.00');

insert into accounts (account_id, name, balance) 
values ('Qgcnwbshbh', 'pebbles', '50.00');

insert into accounts (account_id, name, balance) 
values ('if9l185l18', 'bambam', '50.00');
