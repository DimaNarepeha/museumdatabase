DROP DATABASE IF EXISTS museum;
CREATE DATABASE museum;
USE museum;

CREATE TABLE material(
	id_material INT PRIMARY KEY AUTO_INCREMENT,
    material_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE technique(
	id_technique INT PRIMARY KEY AUTO_INCREMENT,
    technique_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE hall(
	id_hall INT PRIMARY KEY AUTO_INCREMENT,
	hall_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE author(
	id_author INT PRIMARY KEY AUTO_INCREMENT,
	FIRSTNAME VARCHAR(100) NOT NULL,
	LASTNAME VARCHAR(100) NOT NULL,
    CONSTRAINT UNIQUE(FIRSTNAME,LASTNAME)
    );

CREATE TABLE exhibit(
id_exhibit INT PRIMARY KEY AUTO_INCREMENT,
id_material INT,
id_technique INT,
id_hall INT ,
exhibit_name VARCHAR(100) NOT NULL,
 FOREIGN KEY (id_material) REFERENCES material(id_material),
 FOREIGN KEY (id_technique) REFERENCES technique(id_technique),
 FOREIGN KEY (id_hall) REFERENCES hall(id_hall)
 );

CREATE TABLE author_exhibit(
	id_exhibit INT,
	id_author INT,
	PRIMARY KEY (id_exhibit,id_author),
	FOREIGN KEY (id_exhibit) REFERENCES exhibit(id_exhibit),
	FOREIGN KEY (id_author) REFERENCES author(id_author));

CREATE TABLE excursions(
	id_excursion INT PRIMARY KEY AUTO_INCREMENT,
	excursion_name varchar(100) NOT NULL UNIQUE
);

CREATE TABLE guide_position(
   id_guide_position INT PRIMARY KEY AUTO_INCREMENT,
   position_name varchar(100) NOT NULL UNIQUE
);

create table guide(
	id_guide  INT PRIMARY KEY AUTO_INCREMENT,
    id_position int,
	firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
	FOREIGN KEY (id_position) REFERENCES guide_position(id_guide_position)
);



CREATE TABLE schedules(
	id_schedule int primary key AUTO_INCREMENT,
	id_excursion int,
    id_guide int,
    time_start DATETIME NOT NULL,
    time_end DATETIME NOT NULL,
    FOREIGN KEY (id_excursion) REFERENCES excursions(id_excursion),
	FOREIGN KEY (id_guide) REFERENCES guide(id_guide)
); 

CREATE TABLE exhibit_guide(
	id_guide INT,
	id_exhibit INT,
	PRIMARY KEY (id_guide,id_exhibit),
	FOREIGN KEY (id_exhibit) REFERENCES exhibit(id_exhibit),
	FOREIGN KEY (id_guide) REFERENCES guide(id_guide)
);


