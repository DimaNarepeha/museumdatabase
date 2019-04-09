--RIGHT SIDE
INSERT INTO guide_position(position_name)
VALUES("chief"),("helper"),("intern");

INSERT INTO guide(id_position, firstname, lastname)
values(1,"Taras","Tarasenko"),(2,"Petro","Petrenko"),(2,"Ivan","Ivanenko"),
(3,"Pavlo","Pavlenko"),(3,"Ostap","Ostapenko");

INSERT INTO excursions(excursion_name)
VALUES("Ancient Greece"),("Ancient Rome"),("Ancient Egypt"),("Mesopotamia"),("Middle Ages");


INSERT INTO schedules(id_excursion, id_guide, time_start, time_end)
VALUES(1,2,'2019-08-01 09:00:00','2019-08-01 12:00:00'),
(2,1,'2019-08-01 11:00:00','2019-08-01 12:00:00'),
(3,4,'2019-08-01 10:00:00','2019-08-01 11:00:00'),
(4,3,'2019-08-01 11:00:00','2019-08-01 12:00:00'),
(1,4,'2019-08-01 14:00:00','2019-08-01 15:00:00'),
(2,3,'2019-08-01 17:00:00','2019-08-01 18:30:00'),
(3,2,'2019-08-01 13:00:00','2019-08-01 13:30:00'),
(4,1,'2019-08-01 14:00:00','2019-08-01 14:30:00');




--LEFT SIDE

INSERT INTO technique(technique_name)
VALUES("professional"),
("learning"),
("new technique");

INSERT INTO material(material_name)
VALUES("wood"),
("stone"),
("paper");

INSERT INTO hall(hall_name)
VALUES("big hall"),
("middle hall"),
("new hall");

INSERT INTO author(FIRSTNAME,LASTNAME)
VALUES("Tom","Eagleeye"),
("Ralph","Rainbow"),
("Jack","Smith");

INSERT INTO exhibit(id_material,id_technique,id_hall,id_author,exhibit_name)
VALUES(1,3,1,2,"Eagle"),
(3,1,2,3,"Rain"),
(1,2,1,1,"Batman"),
(3,2,2,3,"Pacman"),
(1,3,1,1,"Statue of liberty");

SELECT * FROM technique;
SELECT * FROM material;
SELECT * FROM hall;
SELECT * FROM author;
SELECT * FROM exhibit;