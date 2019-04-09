INSERT INTO guide_position(position_name)
VALUES("chief"),
("helper"),
("intern");
INSERT INTO guide()
SELECT * FROM guide_position;