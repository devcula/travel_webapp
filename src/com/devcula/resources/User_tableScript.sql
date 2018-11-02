drop table user_table;

create table user_table(
	emailId VARCHAR(30) PRIMARY KEY,
	firstName VARCHAR(15) NOT NULL,
	lastName VARCHAR(15) ,
	contactNo BIGINT not null,
	dateOfBirth date not null,
	gender varchar(6) not null,
	password varchar(80) not null,
	question varchar(100) NOT NULL,
	answer varchar(50) NOT NULL);
	
Commit;

Select * from user_table;