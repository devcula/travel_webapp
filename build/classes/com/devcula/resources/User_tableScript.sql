drop table user_table cascade constraints;

create table user_table(
	emailId VARCHAR2(30) constraint user_pk PRIMARY KEY,
	firstName VARCHAR2(15) NOT NULL,
	lastName VARCHAR2(15) ,
	contactNo NUMBER(10) not null,
	dateOfBirth date not null,
	gender varchar2(6) not null,
	password varchar2(80) not null,
	question varchar2(100) NOT NULL,
	answer varchar2(50) NOT NULL);
	
Commit;

Select * from user_table;