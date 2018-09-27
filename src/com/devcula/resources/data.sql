drop table city;
drop table train;
drop table flight;
drop table bus;
drop table passengers;
drop table bookings;
drop table hotel;
drop table card;
drop table cab;
drop table place;

create table city(
	city VARCHAR(30) PRIMARY KEY);

insert into city values('Delhi');
insert into city values('Mumbai');
insert into city values('Mysore');
insert into city values('Bangalore');
insert into city values('Ludhiana');
insert into city values('Dehradun');

create table train(
	source VARCHAR(30) not null,
	destination VARCHAR(30) NOT NULL,
	trainNumber INT(6) PRIMARY KEY ,
	arrival VARCHAR(10) not null,
	departure VARCHAR(10) not null,
	bookingData TEXT NOT NULL,
	trainName VARCHAR(40) NOT NULL);

insert into train values('Delhi','Mumbai',100001,'10:30','22:30','run_java_code','Delhi Mumbai Express');
insert into train values('Mumbai','Delhi',100002,'11:30','23:30','run_java_code','Mumbai Delhi Express');

insert into train values('Delhi','Mumbai',100029,'12:30','00:30','run_java_code','Delhi Mumbai Super Fast');
insert into train values('Mumbai','Delhi',100030,'9:30','21:30','run_java_code','Mumbai Delhi Super Fast');

insert into train values('Delhi','Mysore',100003,'10:30','00:30','run_java_code','Delhi Mysore Express');
insert into train values('Mysore','Delhi',100004,'8:30','22:30','run_java_code','Mysore Delhi Express');

insert into train values('Delhi','Mysore',100031,'5:45','19:45','run_java_code','Delhi Mysore Super Fast');
insert into train values('Mysore','Delhi',100032,'22:00','12:00','run_java_code','Mysore Delhi Super Fast');

insert into train values('Delhi','Bangalore',100005,'13:30','23:30','run_java_code','Delhi Bangalore Express');
insert into train values('Bangalore','Delhi',100006,'7:30','17:30','run_java_code','Bangalore Delhi Express');

insert into train values('Delhi','Bangalore',100033,'5:45','19:45','run_java_code','Delhi Bangalore Super Fast');
insert into train values('Bangalore','Delhi',100034,'21:00','5:00','run_java_code','Bangalore Delhi Super Fast');

insert into train values('Delhi','Ludhiana',100007,'8:30','12:30','run_java_code','Delhi Ludhiana Express');
insert into train values('Ludhiana','Delhi',100008,'16:30','20:30','run_java_code','Ludhiana Delhi Express');

insert into train values('Delhi','Ludhiana',100035,'13:30','17:30','run_java_code','Delhi Ludhiana Super Fast');
insert into train values('Ludhiana','Delhi',100036,'21:00','1:00','run_java_code','Ludhiana Delhi Super Fast');

insert into train values('Delhi','Dehradun',100009,'12:45','17:45','run_java_code','Delhi Dehradun Express');
insert into train values('Dehradun','Delhi',100010,'15:22','20:22','run_java_code','Dehradun Delhi Express');

insert into train values('Delhi','Dehradun',100037,'13:30','18:30','run_java_code','Delhi Dehradun Super Fast');
insert into train values('Dehradun','Delhi',100038,'9:30','14:30','run_java_code','Dehradun Delhi Super Fast');

insert into train values('Mumbai','Mysore',100011,'14:00','22:30','run_java_code','Mumbai Mysore Express');
insert into train values('Mysore','Mumbai',100012,'10:30','18:30','run_java_code','Mysore Mumbai Express');

insert into train values('Mumbai','Mysore',100039,'10:30','22:30','run_java_code','Mumbai Mysore Super Fast');
insert into train values('Mysore','Mumbai',100040,'11:30','23:30','run_java_code','Mysore Mumbai Super Fast');

insert into train values('Mumbai','Bangalore',100013,'9:00','15:30','run_java_code','Mumbai Bangalore Express');
insert into train values('Bangalore','Mumbai',100014,'11:30','17:30','run_java_code','Bangalore Mumbai Express');

insert into train values('Mumbai','Bangalore',100041,'17:00','24:30','run_java_code','Mumbai Bangalore Super Fast');
insert into train values('Bangalore','Mumbai',100042,'15:45','22:30','run_java_code','Bangalore Mumbai Super Fast');

insert into train values('Mumbai','Ludhiana',100015,'19:00','6:00','run_java_code','Mumbai Ludhiana Express');
insert into train values('Ludhiana','Mumbai',100016,'5:30','16:30','run_java_code','Ludhiana Mumbai Express');

insert into train values('Mumbai','Ludhiana',100043,'6:30','17:30','run_java_code','Mumbai Ludhiana Super Fast');
insert into train values('Ludhiana','Mumbai',100044,'15:30','2:30','run_java_code','Ludhiana Mumbai Super Fast');

insert into train values('Mumbai','Dehradun',100017,'6:30','17:30','run_java_code','Mumbai Dehradun Express');
insert into train values('Dehradun','Mumbai',100018,'19:00','6:00','run_java_code','Dehradun Mumbai Express');

insert into train values('Mumbai','Dehradun',100045,'13:30','24:00','run_java_code','Mumbai Dehradun Super Fast');
insert into train values('Dehradun','Mumbai',100046,'5:30','16:30','run_java_code','Dehradun Mumbai Super Fast');

insert into train values('Mysore','Bangalore',100019,'9:30','12:30','run_java_code','Mysore Bangalore Express');
insert into train values('Bangalore','Mysore',100020,'11:30','14:00','run_java_code','Bangalore Mysore Express');

insert into train values('Mysore','Bangalore',100047,'13:30','16:30','run_java_code','Mysore Bangalore Super Fast');
insert into train values('Bangalore','Mysore',100048,'6:30','9:30','run_java_code','Bangalore Mysore Super Fast');

insert into train values('Mysore','Ludhiana',100021,'2:44','17:44','run_java_code','Mysore Ludhiana Express');
insert into train values('Ludhiana','Mysore',100022,'12:00','7:00','run_java_code','Ludhiana Mysore Express');

insert into train values('Mysore','Ludhiana',100049,'4:50','19:50','run_java_code','Mysore Ludhiana Super Fast');
insert into train values('Ludhiana','Mysore',100050,'14:30','9:30','run_java_code','Ludhiana Mysore Super Fast');

insert into train values('Mysore','Dehradun',100023,'11:30','2:30','run_java_code','Mysore Dehradun Express');
insert into train values('Dehradun','Mysore',100024,'5:55','20:55','run_java_code','Dehradun Mysore Express');

insert into train values('Mysore','Dehradun',100051,'5:55','20:55','run_java_code','Mysore Dehradun Super Fast');
insert into train values('Dehradun','Mysore',100052,'11:30','2:30','run_java_code','Dehradun Mysore Super Fast');

insert into train values('Bangalore','Ludhiana',100025,'10:30','00:30','run_java_code','Bangalore Ludhiana Express');
insert into train values('Ludhiana','Bangalore',100026,'8:30','22:30','run_java_code','Ludhiana Bangalore Express');

insert into train values('Bangalore','Ludhiana',100053,'6:00','18:00','run_java_code','Bangalore Ludhiana Super Fast');
insert into train values('Ludhiana','Bangalore',100054,'14:45','2:45','run_java_code','Ludhiana Bangalore Super Fast');

insert into train values('Dehradun','Ludhiana',100027,'13:00','18:30','run_java_code','Dehradun Ludhiana Express');
insert into train values('Ludhiana','Dehradun',100028,'00:45','5:45','run_java_code','Ludhiana Dehradun Express');

insert into train values('Dehradun','Ludhiana',100055,'11:45','16:45','run_java_code','Dehradun Ludhiana Super Fast');
insert into train values('Ludhiana','Dehradun',100056,'6:30','10:30','run_java_code','Ludhiana Dehradun Super Fast');



create table flight(
	source VARCHAR(30) not null,
	destination VARCHAR(30) NOT NULL,
	flightNumber INT(5) PRIMARY KEY ,
	arrival VARCHAR(10) not null,
	departure VARCHAR(10) not null,
	bookingData TEXT NOT NULL,
	flightName VARCHAR(20) NOT NULL);

insert into flight values('Delhi','Mumbai',10001,'10:30','22:30','run_java_code','Luke airways');
insert into flight values('Mumbai','Delhi',10002,'11:30','23:30','run_java_code','Madraid airways');

insert into flight values('Delhi','Mumbai',10029,'10:30','22:30','run_java_code','Air NATIONAL');
insert into flight values('Mumbai','Delhi',10030,'11:30','23:30','run_java_code','Madraid airways');

insert into flight values('Delhi','Mumbai',10057,'10:30','22:30','run_java_code','Luke airways');
insert into flight values('Mumbai','Delhi',10058,'11:30','23:30','run_java_code','Madraid airways');

insert into flight values('Delhi','Mumbai',10059,'10:30','22:30','run_java_code','Air NATIONAL');
insert into flight values('Mumbai','Delhi',10060,'11:30','23:30','run_java_code','Madraid airways');

insert into flight values('Delhi','Mumbai',10061,'10:30','22:30','run_java_code','Luke airways');
insert into flight values('Mumbai','Delhi',10062,'11:30','23:30','run_java_code','Madraid airways');

insert into flight values('Delhi','Mumbai',10063,'10:30','22:30','run_java_code','Air NATIONAL');
insert into flight values('Mumbai','Delhi',10064,'11:30','23:30','run_java_code','Madraid airways');

insert into flight values('Delhi','Mysore',10003,'10:30','22:30','run_java_code','Throne Airways');
insert into flight values('Mysore','Delhi',10004,'11:30','23:30','run_java_code','Air NATIONAL');

insert into flight values('Delhi','Mysore',10031,'10:30','22:30','run_java_code','Luke airways');
insert into flight values('Mysore','Delhi',10032,'11:30','23:30','run_java_code','Throne Airways');

insert into flight values('Delhi','Bangalore',10005,'10:30','22:30','run_java_code','Throne Airways');
insert into flight values('Bangalore','Delhi',10006,'11:30','23:30','run_java_code','Madraid airways');

insert into flight values('Delhi','Bangalore',10033,'10:30','22:30','run_java_code','Sounor Airways');
insert into flight values('Bangalore','Delhi',10034,'11:30','23:30','run_java_code','Air NATIONAL');

insert into flight values('Delhi','Ludhiana',10007,'10:30','22:30','run_java_code','Luke airways');
insert into flight values('Ludhiana','Delhi',10008,'11:30','23:30','run_java_code','Inter Airways');

insert into flight values('Delhi','Ludhiana',10035,'10:30','22:30','run_java_code','Inter Airways');
insert into flight values('Ludhiana','Delhi',10036,'11:30','23:30','run_java_code','Madraid airways');

insert into flight values('Delhi','Dehradun',10009,'10:30','22:30','run_java_code','Inter Airways');
insert into flight values('Dehradun','Delhi',10010,'11:30','23:30','run_java_code','Air NATIONAL');

insert into flight values('Delhi','Dehradun',10037,'10:30','22:30','run_java_code','Luke airways');
insert into flight values('Dehradun','Delhi',10038,'11:30','23:30','run_java_code','Inter Airways');

insert into flight values('Mumbai','Mysore',10011,'10:30','22:30','run_java_code','Inter Airways');
insert into flight values('Mysore','Mumbai',10012,'11:30','23:30','run_java_code','Madraid airways');

insert into flight values('Mumbai','Mysore',10039,'10:30','22:30','run_java_code','Inter Airways');
insert into flight values('Mysore','Mumbai',10040,'11:30','23:30','run_java_code','Air NATIONAL');

insert into flight values('Mumbai','Bangalore',10013,'10:30','22:30','run_java_code','Luke airways');
insert into flight values('Bangalore','Mumbai',10014,'11:30','23:30','run_java_code','Inter Airways');

insert into flight values('Mumbai','Bangalore',10041,'10:30','22:30','run_java_code','Inter Airways');
insert into flight values('Bangalore','Mumbai',10042,'11:30','23:30','run_java_code','Air NATIONAL');

insert into flight values('Mumbai','Ludhiana',10015,'10:30','22:30','run_java_code','Air NATIONAL');
insert into flight values('Ludhiana','Mumbai',10016,'11:30','23:30','run_java_code','Inter Airways');

insert into flight values('Mumbai','Ludhiana',10043,'10:30','22:30','run_java_code','Luke airways');
insert into flight values('Ludhiana','Mumbai',10044,'11:30','23:30','run_java_code','Inter Airways');

insert into flight values('Mumbai','Dehradun',10017,'10:30','22:30','run_java_code','Inter Airways');
insert into flight values('Dehradun','Mumbai',10018,'11:30','23:30','run_java_code','Air NATIONAL');

insert into flight values('Mumbai','Dehradun',10045,'10:30','22:30','run_java_code','Luke airways');
insert into flight values('Dehradun','Mumbai',10046,'11:30','23:30','run_java_code','Inter Airways');

insert into flight values('Mysore','Bangalore',10019,'10:30','22:30','run_java_code','flight Nineteen');
insert into flight values('Bangalore','Mysore',10020,'11:30','23:30','run_java_code','Madraid airways');

insert into flight values('Mysore','Bangalore',10047,'10:30','22:30','run_java_code','Luke airways');
insert into flight values('Bangalore','Mysore',10048,'11:30','23:30','run_java_code','Inter Airways');

insert into flight values('Mysore','Ludhiana',10021,'10:30','22:30','run_java_code','Throne Airways');
insert into flight values('Ludhiana','Mysore',10022,'11:30','23:30','run_java_code','Sounor Airways');

insert into flight values('Mysore','Ludhiana',10049,'10:30','22:30','run_java_code','Sounor Airways');
insert into flight values('Ludhiana','Mysore',10050,'11:30','23:30','run_java_code','Air NATIONAL');

insert into flight values('Mysore','Dehradun',10023,'10:30','22:30','run_java_code','Luke airways');
insert into flight values('Dehradun','Mysore',10024,'11:30','23:30','run_java_code','Sounor Airways');

insert into flight values('Mysore','Dehradun',10051,'10:30','22:30','run_java_code','Sounor Airways');
insert into flight values('Dehradun','Mysore',10052,'11:30','23:30','run_java_code','Madraid airways');

insert into flight values('Bangalore','Ludhiana',10025,'10:30','22:30','run_java_code','Luke airways');
insert into flight values('Ludhiana','Bangalore',10026,'11:30','23:30','run_java_code','Sounor Airways');

insert into flight values('Bangalore','Ludhiana',10053,'10:30','22:30','run_java_code','Throne Airways');
insert into flight values('Ludhiana','Bangalore',10054,'11:30','23:30','run_java_code','Madraid airways');

insert into flight values('Dehradun','Ludhiana',10027,'10:30','22:30','run_java_code','Inter Airways');
insert into flight values('Ludhiana','Dehradun',10028,'11:30','23:30','run_java_code','Air NATIONAL');

insert into flight values('Dehradun','Ludhiana',10055,'10:30','22:30','run_java_code','Luke airways');
insert into flight values('Ludhiana','Dehradun',10056,'11:30','23:30','run_java_code','Throne Airways');


create table bus(
	source VARCHAR(30) not null,
	destination VARCHAR(30) NOT NULL,
	busNumber INT(5) PRIMARY KEY ,
	busType VARCHAR(5) not null,
	arrival VARCHAR(10) not null,
	departure VARCHAR(10) not null,
	bookingData TEXT NOT NULL,
	busName VARCHAR(20) NOT NULL);


insert into bus values('Delhi','Mumbai',1001,'AC','10:30','22:30','run_java_code','Bus 1');
insert into bus values('Mumbai','Delhi',1002,'AC','15:30','18:30','run_java_code','Bus 2');

insert into bus values('Delhi','Mumbai',1031,'NONAC','10:30','22:30','run_java_code','Bus 31');
insert into bus values('Mumbai','Delhi',1032,'NONAC','15:30','18:30','run_java_code','Bus 32');

insert into bus values('Delhi','Mysore',1003,'AC','10:30','22:30','run_java_code','Bus 3');
insert into bus values('Mysore','Delhi',1004,'AC','15:30','18:30','run_java_code','Bus 4');

insert into bus values('Delhi','Mysore',1033,'NONAC','10:30','22:30','run_java_code','Bus 33');
insert into bus values('Mysore','Delhi',1034,'NONAC','15:30','18:30','run_java_code','Bus 34');

insert into bus values('Delhi','Bangalore',1005,'AC','10:30','22:30','run_java_code','Bus 5');
insert into bus values('Bangalore','Delhi',1006,'AC','15:30','18:30','run_java_code','Bus 6');

insert into bus values('Delhi','Bangalore',1035,'NONAC','10:30','22:30','run_java_code','Bus 35');
insert into bus values('Bangalore','Delhi',1036,'NONAC','15:30','18:30','run_java_code','Bus 36');

insert into bus values('Delhi','Ludhiana',1007,'AC','10:30','22:30','run_java_code','Bus 7');
insert into bus values('Ludhiana','Delhi',1008,'AC','15:30','18:30','run_java_code','Bus 8');

insert into bus values('Delhi','Ludhiana',1037,'NONAC','10:30','22:30','run_java_code','Bus 37');
insert into bus values('Ludhiana','Delhi',1038,'NONAC','15:30','18:30','run_java_code','Bus 38');

insert into bus values('Delhi','Dehradun',1009,'AC','10:30','22:30','run_java_code','Bus 9');
insert into bus values('Dehradun','Delhi',1010,'AC','15:30','18:30','run_java_code','Bus 10');

insert into bus values('Delhi','Dehradun',1039,'NONAC','10:30','22:30','run_java_code','Bus 39');
insert into bus values('Dehradun','Delhi',1040,'NONAC','15:30','18:30','run_java_code','Bus 40');

insert into bus values('Mumbai','Mysore',1011,'AC','10:30','22:30','run_java_code','Bus 11');
insert into bus values('Mysore','Mumbai',1012,'AC','15:30','18:30','run_java_code','Bus 12');

insert into bus values('Mumbai','Mysore',1041,'NONAC','10:30','22:30','run_java_code','Bus 41');
insert into bus values('Mysore','Mumbai',1042,'NONAC','15:30','18:30','run_java_code','Bus 42');

insert into bus values('Mumbai','Bangalore',1013,'AC','10:30','22:30','run_java_code','Bus 13');
insert into bus values('Bangalore','Mumbai',1014,'AC','15:30','18:30','run_java_code','Bus 14');

insert into bus values('Mumbai','Bangalore',1043,'NONAC','10:30','22:30','run_java_code','Bus 43');
insert into bus values('Bangalore','Mumbai',1044,'NONAC','15:30','18:30','run_java_code','Bus 44');

insert into bus values('Mumbai','Ludhiana',1015,'AC','10:30','22:30','run_java_code','Bus 15');
insert into bus values('Ludhiana','Mumbai',1016,'AC','15:30','18:30','run_java_code','Bus 16');

insert into bus values('Mumbai','Ludhiana',1045,'NONAC','10:30','22:30','run_java_code','Bus 45');
insert into bus values('Ludhiana','Mumbai',1046,'NONAC','15:30','18:30','run_java_code','Bus 46');

insert into bus values('Mumbai','Dehradun',1017,'AC','10:30','22:30','run_java_code','Bus 17');
insert into bus values('Dehradun','Mumbai',1018,'AC','15:30','18:30','run_java_code','Bus 18');

insert into bus values('Mumbai','Dehradun',1047,'NONAC','10:30','22:30','run_java_code','Bus 47');
insert into bus values('Dehradun','Mumbai',1048,'NONAC','15:30','18:30','run_java_code','Bus 48');

insert into bus values('Mysore','Bangalore',1019,'AC','10:30','22:30','run_java_code','Bus 19');
insert into bus values('Bangalore','Mysore',1020,'AC','15:30','18:30','run_java_code','Bus 20');

insert into bus values('Mysore','Bangalore',1049,'NONAC','10:30','22:30','run_java_code','Bus 49');
insert into bus values('Bangalore','Mysore',1050,'NONAC','15:30','18:30','run_java_code','Bus 50');

insert into bus values('Mysore','Ludhiana',1021,'AC','10:30','22:30','run_java_code','Bus 21');
insert into bus values('Ludhiana','Mysore',1022,'AC','15:30','18:30','run_java_code','Bus 22');

insert into bus values('Mysore','Ludhiana',1051,'NONAC','10:30','22:30','run_java_code','Bus 51');
insert into bus values('Ludhiana','Mysore',1052,'NONAC','15:30','18:30','run_java_code','Bus 52');

insert into bus values('Mysore','Dehradun',1023,'AC','10:30','22:30','run_java_code','Bus 23');
insert into bus values('Dehradun','Mysore',1024,'AC','15:30','18:30','run_java_code','Bus 24');

insert into bus values('Mysore','Dehradun',1053,'NONAC','10:30','22:30','run_java_code','Bus 53');
insert into bus values('Dehradun','Mysore',1054,'NONAC','15:30','18:30','run_java_code','Bus 54');

insert into bus values('Bangalore','Ludhiana',1025,'AC','10:30','22:30','run_java_code','Bus 25');
insert into bus values('Ludhiana','Bangalore',1026,'AC','15:30','18:30','run_java_code','Bus 26');

insert into bus values('Bangalore','Ludhiana',1055,'NONAC','10:30','22:30','run_java_code','Bus 55');
insert into bus values('Ludhiana','Bangalore',1056,'NONAC','15:30','18:30','run_java_code','Bus 56');

insert into bus values('Bangalore','Dehradun',1027,'AC','10:30','22:30','run_java_code','Bus 27');
insert into bus values('Dehradun','Bangalore',1028,'AC','15:30','18:30','run_java_code','Bus 28');

insert into bus values('Bangalore','Dehradun',1057,'NONAC','10:30','22:30','run_java_code','Bus 57');
insert into bus values('Dehradun','Bangalore',1058,'NONAC','15:30','18:30','run_java_code','Bus 58');

insert into bus values('Dehradun','Ludhiana',1029,'AC','10:30','22:30','run_java_code','Bus 29');
insert into bus values('Ludhiana','Dehradun',1030,'AC','15:30','18:30','run_java_code','Bus 30');

insert into bus values('Dehradun','Ludhiana',1059,'NONAC','10:30','22:30','run_java_code','Bus 59');
insert into bus values('Ludhiana','Dehradun',1060,'NONAC','15:30','18:30','run_java_code','Bus 60');



create table bookings(
	bookingId INT PRIMARY KEY,
	userEmailId VARCHAR(40) NOT NULL,
	bookingDate VARCHAR(15) NOT NULL,
	transactionId VARCHAR(15) UNIQUE NOT NULL,
	bookingType VARCHAR(10) NOT NULL,
	noOfPassengers INT NOT NULL,
	journeyDate VARCHAR(15) NOT NULL,
	source VARCHAR(20) NOT NULL,
	destination VARCHAR(20) NOT NULL,
	fare INT NOT NULL,
	entityId INT NOT NULL,
	entityName VARCHAR(30) NOT NULL,
	arrival VARCHAR(40) NOT NULL,
	departure VARCHAR(40) NOT NULL,
	travelClass VARCHAR(15) NOT NULL,
	feedback VARCHAR(300),
	rated CHAR(1) DEFAULT 'N'
);

create table passengers(
	passengerId INT PRIMARY KEY,
	bookingId VARCHAR(10) NOT NULL,
	name VARCHAR(40) NOT NULL,
	age INT NOT NULL,
	gender VARCHAR(6) NOT NULL
);

create table hotel(
	hotelId INT PRIMARY KEY,
	city VARCHAR(20) NOT NULL,
	hotelName VARCHAR(30) NOT NULL,
	bookingData TEXT NOT NULL,
	rating INT DEFAULT 0,
	timesRated INT DEFAULT 0,
	roomsType VARCHAR(10) NOT NULL
);

insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1001,'Mumbai','Hotel Taj','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1002,'Mumbai','Hotel Plaza','run_java_code','standard');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1003,'Mumbai','IBIS','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType,rating,timesRated) values(1004,'Mumbai','Hotel X','run_java_code','deluxe',2.5,2);
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1005,'Delhi','Hotel Taj','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1006,'Delhi','Hotel Plaza','run_java_code','standard');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1007,'Delhi','IBIS','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType,rating,timesRated) values(1008,'Delhi','Hotel X','run_java_code','deluxe',2.5,2);
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1009,'Ludhiana','Hotel Taj','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1010,'Ludhiana','Hotel Plaza','run_java_code','standard');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1011,'Ludhiana','IBIS','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType,rating,timesRated) values(1012,'Ludhiana','Hotel X','run_java_code','deluxe',2.5,2);
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1013,'Mysore','Hotel Taj','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1014,'Mysore','Hotel Plaza','run_java_code','standard');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1015,'Mysore','IBIS','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType,rating,timesRated) values(1016,'Mysore','Hotel X','run_java_code','deluxe',2.5,2);
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1017,'Dehradun','Hotel Taj','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1018,'Dehradun','Hotel Plaza','run_java_code','standard');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1019,'Dehradun','IBIS','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType,rating,timesRated) values(1020,'Dehradun','Hotel X','run_java_code','deluxe',2.5,2);
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1021,'Bangalore','Hotel Taj','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1022,'Bangalore','Hotel Plaza','run_java_code','standard');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1023,'Bangalore','IBIS','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType,rating,timesRated) values(1024,'Bangalore','Hotel X','run_java_code','deluxe',2.5,2);
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1025,'Bangalore','Hotel Taj','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1026,'Bangalore','Hotel Plaza','run_java_code','standard');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1027,'Bangalore','IBIS','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType,rating,timesRated) values(1028,'Bangalore','Hotel X','run_java_code','deluxe',2.5,2);
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1029,'Mumbai','Hotel Taj','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1030,'Mumbai','Hotel Plaza','run_java_code','standard');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType) values(1031,'Mumbai','IBIS','run_java_code','deluxe');
insert into hotel(hotelId,city,hotelName,bookingData,roomsType,rating,timesRated) values(1032,'Mumbai','Hotel X','run_java_code','deluxe',2.5,2);


create table card(
    cardHolderName VARCHAR(30),
	cardNumber varchar(16) PRIMARY KEY,
	cvv INT NOT NULL,
	cardexpirydate VARCHAR(15) NOT NULL,
	cardtype varchar(6) not null,
	amount BIGINT NOT NULL
);


insert into card values('Supreet Singh Soni', '1234567891234567', '007','2022-10-31','Credit',1000000 );
insert into card values('Abhishek Prasad', '123456789987654', '123','2022-10-31','Credit',5000000000 );
insert into card values('Tamanpreet Singh', '123456789456789', '456','2022-10-31','Debit',5264000 );
insert into card values('Shriya Gupta', '123456789741852', '789','2022-10-31','Credit',415441 );
insert into card values('Shrutika Chawla', '123456789852963', '123','2022-10-31','Debit',878787 );
insert into card values('Sukhprit Kaur', '123456789741963', '456','2022-10-31','Credit',4545454545 );








	create table user_table(
		emailId VARCHAR(30) PRIMARY KEY,
		firstName VARCHAR(15) NOT NULL,
		lastName VARCHAR(15) ,
		contactNo INT(10) not null,
		dateOfBirth date not null,
		gender varchar(6) not null,
		password varchar(80) not null,
		question varchar(100) NOT NULL,
		answer varchar(50) NOT NULL);





		create table place(
		city varchar(30),
		place varchar(30),
		primary key(city,place)
		);

		insert into place values('Mysore','Railway Colony');
		insert into place values('Mysore','Infosys');
		insert into place values('Mysore','Vijay Nagar');
		insert into place values('Mysore','Bus Stand');
		insert into place values('Mysore','Chamundi Hills');
		insert into place values('Mysore','Gokulam');
		insert into place values('Bangalore','Yeshwantpur');
		insert into place values('Bangalore','Koramangala');
		insert into place values('Bangalore','Indira Nagar');
		insert into place values('Bangalore','Lalbagh');
		insert into place values('Bangalore','Majestic');
		insert into place values('Bangalore','Wilson Garden');
		insert into place values('Dehradun','Bhandari Bagh');
		insert into place values('Dehradun','Race Course');
		insert into place values('Dehradun','Bahuguna Colony');
		insert into place values('Dehradun','GTB Nagar');
		insert into place values('Dehradun','Govind Nagar');
		insert into place values('Dehradun','Chander Nagar');
		insert into place values('Ludhiana','Model Town');
		insert into place values('Ludhiana','BRS Nagar');
		insert into place values('Ludhiana','Sarabha Nagar');
		insert into place values('Ludhiana','Dugri');
		insert into place values('Ludhiana','Greenfield');
		insert into place values('Ludhiana','Civil Lines');
		insert into place values('Delhi','Defence Colony');
		insert into place values('Delhi','Lajpat Nagar');
		insert into place values('Delhi','New Friends Colony');
		insert into place values('Delhi','Nehru Place');
		insert into place values('Delhi','Tilak Nagar');
		insert into place values('Delhi','Connaught Place');
		insert into place values('Mumbai','Thane');
		insert into place values('Mumbai','Goregaon');
		insert into place values('Mumbai','Chembur');
		insert into place values('Mumbai','Juhu');
		insert into place values('Mumbai','Worli');
		insert into place values('Mumbai','Dadar');


		create table cab(
			cabNumber varchar(30) PRIMARY KEY,
			cabname VARCHAR(30) NOT NULL,
			carType VARCHAR(30) NOT NULL,
			city varchar(30) not null,
			bookingData TEXT NOT NULL,
			driverName VARCHAR(40) NOT NULL
		);

		insert into cab values('pb10aa0001','swift','hatchback','Ludhiana','run_java_code','Shahrukh Khan');
		insert into cab values('pb10aa0002','sunny','sedan','Ludhiana','run_java_code','Salman Khan');
		insert into cab values('pb10aa0003','scorpio','suv','Ludhiana','run_java_code','Saif Ali Khan');
		insert into cab values('ka09aa0001','swift','hatchback','Mysore','run_java_code','Randeep Hooda');
		insert into cab values('ka09aa0002','sunny','sedan','Mysore','run_java_code','Varun Dhawan');
		insert into cab values('ka09aa0003','scorpio','suv','Mysore','run_java_code','Sidharth Malhotra');
		insert into cab values('dl01aa0001','swift','hatchback','Delhi','run_java_code','Rani Mukherjee');
		insert into cab values('dl01aa0002','sunny','sedan','Delhi','run_java_code','Vidya Balan');
		insert into cab values('dl01aa0003','scorpio','suv','Delhi','run_java_code','Alia Bhatt');
		insert into cab values('ka05aa0001','swift','hatchback','Bangalore','run_java_code','Kareena Kapoor');
		insert into cab values('ka05aa0002','sunny','sedan','Bangalore','run_java_code','Supreet Singh');
		insert into cab values('ka5aa0003','scorpio','suv','Bangalore','run_java_code','Shriya Gupta');
		insert into cab values('uk07aa0001','swift','hatchback','Dehradun','run_java_code','Abhishek Prasad');
		insert into cab values('uk07aa0002','sunny','sedan','Dehradun','run_java_code','Shrutika Chawla');
		insert into cab values('uk007aa0003','scorpio','suv','Dehradun','run_java_code','Sukhprit Kaun');
		insert into cab values('mh01aa0001','swift','hatchback','Mumbai','run_java_code','TamanPreet Singh');
		insert into cab values('mh01aa0002','sunny','sedan','Mumbai','run_java_code','Sankalp Mehra');
		insert into cab values('mh01aa0003','scorpio','suv','Mubai','run_java_code','Shashank Sah');










commit;

select * from user_table;
select * from city;
select * from train;
select * from train;
select * from flight;
select * from bookings;
select * from passengers;
select * from bus;
select * from hotel;
