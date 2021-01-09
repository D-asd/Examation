 create database telts
 
 
 create table question (
 	title varchar(255) ,
 	answer varchar(40),
 	options varchar(255),
 	score int(11),
 	level int (11)
);

create table user(
  id int(11), 
  name varchar(20),
  phone varchar(20),
  password varchar(20),
  email varchar(40)
);
