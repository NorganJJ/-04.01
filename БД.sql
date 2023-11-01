CREATE TABLE Users
(
    Id SERIAL PRIMARY KEY,
    Email CHARACTER VARYING(30),
    Age INTEGER default(0),
    Password CHARACTER VARYING(255),
	Surname CHARACTER VARYING(30) default('Не указано'),
	Second_Name CHARACTER VARYING(30) default('Не указано'),
	Name CHARACTER VARYING(30) default('Не указано'),
	Active boolean
);

INSERT INTO Users VALUES (1, 'cucu@mail.ru', 36, 'Password', 'Иванов', 'Иван', 'Иванович')

CREATE TABLE Aviary
(
    Id SERIAL PRIMARY KEY,
    Name CHARACTER VARYING(30),
    Code CHARACTER VARYING(10),
	Description CHARACTER VARYING(200)
);

INSERT INTO Aviary VALUES (1, 'Вольер №136', 136, 'Вольер - бомба')

CREATE TABLE Animal
(
    Id SERIAL PRIMARY KEY References Aviary (ID),
    Name CHARACTER VARYING(30),
    Age INTEGER,
    Color CHARACTER VARYING(30)
);

INSERT INTO Animal VALUES (1, 'Лев', 10, 'Оранжевый')

CREATE TABLE Category
(
    Id SERIAL PRIMARY KEY,
    Name CHARACTER VARYING(30),
    Reason CHARACTER VARYING(30),
    Status CHARACTER VARYING(30)
);

INSERT INTO Category VALUES (1, 'Важное', 'Перекрыть зоопарк', 'Немедленно')

drop table users;

CREATE TABLE Ticket
(
    Id SERIAL PRIMARY KEY,
    Name CHARACTER VARYING(30),
    Time CHARACTER VARYING(30),
    Cost INTEGER,
	User_ID INTEGER,
	foreign key (User_ID) References Users (ID)
	
);

CREATE TABLE user_roles
(
    user_id SERIAL PRIMARY KEY,
    role CHARACTER VARYING(30)	
);


INSERT INTO Ticket VALUES (1, 'Билет на посещение', '5 часов', 800, 1)

CREATE TABLE Event
(
    Id SERIAL PRIMARY KEY,
    Name CHARACTER VARYING(30),
    Category_ID INTEGER,
    Coach_ID INTEGER,
	foreign key (Category_ID) References Category (ID),
	foreign key (Coach_ID) References Coaches (ID)
);
INSERT INTO Event VALUES (1, 'Шоу Львов', 2, 1)


CREATE TABLE Coaches
(
    Id SERIAL PRIMARY KEY,
    Email CHARACTER VARYING(30),
    Age INTEGER,
	Surname CHARACTER VARYING(30),
	Second_Name CHARACTER VARYING(30),
	Name CHARACTER VARYING(30)
);

INSERT INTO Coaches VALUES (2, 'tutu@mail.ru', 18, 'Иванов', 'Андрей', 'Иванович')

select Name + ' ' + Surname + ' ' + Second_Name from Users

select * from Users

CREATE TABLE EventMark
(
    Id SERIAL PRIMARY KEY,
    Event_ID INTEGER,
    User_ID INTEGER,
	foreign key (Event_ID) References Event (ID),
	foreign key (User_ID) References Users (ID)
);

INSERT INTO EventMark VALUES (1, 1, 1)

CREATE TABLE AnTraining
(
	Id SERIAL PRIMARY KEY,
    KOLVO INTEGER,
	MONTH CHARACTER VARYING(20),
    COACH_ID INTEGER,
	ANIMAL_ID INTEGER,
	foreign key (Animal_ID) References Animal (ID),
	foreign key (Coach_ID) References Coaches (ID)
);

INSERT INTO AnTraining VALUES (1, 1, 'Сентябрь', 1, 1)

select * from Animal

Delete from Animal where Id = 2

drop database zovopark;


select email, password, active from users where email = 'mrbecauser@mail.ru'

select u.email, ur.role from users u inner join user_roles ur on u.id = ur.user_id where u.email='mrbecauser@mail.ru'


insert into eventmark Values(1,7)

select * from event