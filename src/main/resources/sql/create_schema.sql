CREATE TABLE person (
	id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	name VARCHAR(30),
	age INT
);

CREATE TABLE car (
	id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	producer VARCHAR(30),
	model VARCHAR(30),
	manufacture_year INT,
	origin VARCHAR(30)
);
