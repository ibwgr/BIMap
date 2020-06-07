/* Tabellen löschen */
/*
DROP TABLE IF EXISTS bimap.projekt;
DROP TABLE IF EXISTS bimap.bauart;
DROP TABLE IF EXISTS bimap.projektverfasser;
DROP TABLE IF EXISTS bimap.leistung;
DROP TABLE IF EXISTS bimap.ort;
DROP TABLE IF EXISTS bimap.bauherr;
DROP TABLE IF EXISTS bimap.bauartprojekt;
DROP TABLE IF EXISTS bimap.projektverfasserprojekt;
DROP TABLE IF EXISTS bimap.leistungprojekt;
*/


/* Tabellen erzeugen */
CREATE TABLE IF NOT EXISTS projekt
(
	id INT, 
	projektnummer INT,
	projektname VARCHAR(30),
	ortid INT,
	koordx VARCHAR(20),
	koordy VARCHAR(20),
	realisierungsjahr VARCHAR(10),
	bausumme VARCHAR(20),
	bauherrid INT
);

CREATE TABLE IF NOT EXISTS bauart
(
	id INT, 
	bauart VARCHAR(30) 
);


 CREATE TABLE IF NOT EXISTS projektverfasser
(
	id INT, 
	projektverfasser VARCHAR(30) 
);


 CREATE TABLE IF NOT EXISTS leistung
(
	id INT, 
	leistungen VARCHAR(30),
	bauartid VARCHAR(30) 
);


 CREATE TABLE IF NOT EXISTS ort
(
	id INT, 
	ort VARCHAR(30),
	plz VARCHAR(30) 
);


 CREATE TABLE IF NOT EXISTS bauherr
(
	id INT, 
	bauherr VARCHAR(30),
);


 CREATE TABLE IF NOT EXISTS bauartprojekt
(
	bauartid INT, 
	projektid VARCHAR(30),
);


 CREATE TABLE IF NOT EXISTS projektverfasserprojekt
(
	projektverfasserid INT, 
	projektid VARCHAR(30),
);


 CREATE TABLE IF NOT EXISTS leistungprojekt
(
	leistungid INT, 
	projektid VARCHAR(30),
);

