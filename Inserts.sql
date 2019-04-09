--RIGHT SIDE
INSERT INTO guide_position(position_name)
VALUES("chief"),
("helper"),
("intern");
INSERT INTO guide()
SELECT * FROM guide_position;



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
(1,2,1,1,"Batman");

SELECT * FROM technique;
SELECT * FROM material;
SELECT * FROM hall;
SELECT * FROM author;
SELECT * FROM exhibit;