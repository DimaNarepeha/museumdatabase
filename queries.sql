--1 Інформація про експонат (автор, зал, матеріал, техніка …)
SELECT exhibit_name, hall_name, FIRSTNAME, LASTNAME, material_name, technique_name FROM exhibit
INNER JOIN hall ON hall.id_hall=exhibit.id_hall
INNER JOIN material ON material.id_material=exhibit.id_material
INNER JOIN technique ON technique.id_technique=exhibit.id_technique
INNER JOIN author_exhibit ON author_exhibit.id_exhibit=exhibit.id_exhibit
INNER JOIN author ON author.id_author=author_exhibit.id_author;

--2 Знайти всі роботи та їх розміщення для конкретного автора
SELECT exhibit_name, hall_name, FIRSTNAME, LASTNAME, material_name, technique_name FROM exhibit
INNER JOIN hall ON hall.id_hall=exhibit.id_hall
INNER JOIN material ON material.id_material=exhibit.id_material
INNER JOIN technique ON technique.id_technique=exhibit.id_technique
INNER JOIN author_exhibit ON author_exhibit.id_exhibit=exhibit.id_exhibit
INNER JOIN author ON author.id_author=author_exhibit.id_author
WHERE author.FIRSTNAME='Tom';