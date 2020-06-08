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
	id INT(10) NOT NULL, 
	projektnummer INT,
	projektname VARCHAR(30),
	ortid INT,
	koordx VARCHAR(20),
	koordy VARCHAR(20),
	realisierungsjahr VARCHAR(10),
	bausumme VARCHAR(20),
	bauherrid INT,
	PRIMARY KEY ('id'),

	KEY 'FK_projekt_ort' ('ortid'),
	CONSTRAINT 'FK_projekt_ort' FOREIGN KEY ('ortid') REFERENCES 'ort' ('id') ON DELETE NO UPDATE NO ACTION

	KEY 'FK_bauherr' ('bauherrid'),
	CONSTRAINT 'FK_bauherr' FOREIGN KEY ('bauherrid') REFERENCES 'bauherr' ('id') ON DELETE NO UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS bauart
(
	id INT(10) NOT NULL, 
	bauart VARCHAR(30) 
);


 CREATE TABLE IF NOT EXISTS projektverfasser
(
	id INT(10) NOT NULL, 
	projektverfasser VARCHAR(30) 
);


 CREATE TABLE IF NOT EXISTS leistung
(
	id INT(10) NOT NULL, 
	leistungen VARCHAR(30),
);


 CREATE TABLE IF NOT EXISTS ort
(
	id INT(10) NOT NULL, 
	ort VARCHAR(30),
	plz VARCHAR(30) 
);


 CREATE TABLE IF NOT EXISTS bauherr
(
	id INT(10) NOT NULL, 
	bauherr VARCHAR(30),
);


 CREATE TABLE IF NOT EXISTS bauartprojekt
(
	bauartid INT(10) NOT NULL, 
	projektid VARCHAR(30),

	KEY 'FK_bauart' ('bauartid'),
	CONSTRAINT 'FK_bauart' FOREIGN KEY ('bauartid') REFERENCES 'bauart' ('id') ON DELETE NO UPDATE NO ACTION

	KEY 'FK_projekt' ('projektid'),
	CONSTRAINT 'FK_projekt' FOREIGN KEY ('projektid') REFERENCES 'projekt' ('id') ON DELETE NO UPDATE NO ACTION
);


 CREATE TABLE IF NOT EXISTS projektverfasserprojekt
(
	projektverfasserid INT(10) NOT NULL, 
	projektid VARCHAR(30),

	KEY 'FK_brojektverfasser' ('projektverfasserid'),
	CONSTRAINT 'FK_brojektverfasser' FOREIGN KEY ('projektverfasserid') REFERENCES 'projektverfasser' ('id') ON DELETE NO UPDATE NO ACTION

	KEY 'FK_projekt' ('projektid'),
	CONSTRAINT 'FK_projekt' FOREIGN KEY ('projektid') REFERENCES 'projekt' ('id') ON DELETE NO UPDATE NO ACTION

);


 CREATE TABLE IF NOT EXISTS leistungprojekt
(
	leistungid INT(10) NOT NULL, 
	projektid VARCHAR(30),

	KEY 'FK_leistung' ('leistungid'),
	CONSTRAINT 'FK_leistung' FOREIGN KEY ('leistungid') REFERENCES 'leistung' ('id') ON DELETE NO UPDATE NO ACTION

	KEY 'FK_projekt' ('projektid'),
	CONSTRAINT 'FK_projekt' FOREIGN KEY ('projektid') REFERENCES 'projekt' ('id') ON DELETE NO UPDATE NO ACTION
);

