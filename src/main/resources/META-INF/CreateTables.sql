
/* Tabellen löschen */

DROP TABLE IF EXISTS bauart CASCADE;
DROP TABLE IF EXISTS projektverfasser CASCADE;
DROP TABLE IF EXISTS leistung CASCADE;
DROP TABLE IF EXISTS ort CASCADE;
DROP TABLE IF EXISTS bauherr CASCADE;
DROP TABLE IF EXISTS bauartprojekt CASCADE;
DROP TABLE IF EXISTS projektverfasserprojekt CASCADE;
DROP TABLE IF EXISTS leistungprojekt CASCADE;
DROP TABLE IF EXISTS projekt CASCADE;

CREATE TABLE IF NOT EXISTS bauart
(
    idbauart INTEGER PRIMARY KEY,
    bauart   VARCHAR(255)

);

 CREATE TABLE IF NOT EXISTS projektverfasser
(
	idprojektverfasser INTEGER PRIMARY KEY ,
	projektverfasser VARCHAR(255)
);


 CREATE TABLE IF NOT EXISTS leistung
(
	idleistung INTEGER PRIMARY KEY ,
	leistungen VARCHAR(255)
);


 CREATE TABLE IF NOT EXISTS ort
(
	idort INTEGER PRIMARY KEY ,
	ort VARCHAR(255),
	plz VARCHAR(255)
);


 CREATE TABLE IF NOT EXISTS bauherr
(
	idbauherr INTEGER PRIMARY KEY ,
	bauherr VARCHAR(255)
);



CREATE TABLE IF NOT EXISTS projekt
(
    idprojekt         INTEGER PRIMARY KEY,
    projektnummer     INTEGER,
    projektname       VARCHAR(255),
    ortid             INTEGER,
    koordx            VARCHAR(255),
    koordy            VARCHAR(255),
    realisierungsjahr VARCHAR(255),
    bausumme          VARCHAR(255),
    bauherrid         INTEGER,


	FOREIGN KEY (ortid) REFERENCES ort(idort),
	FOREIGN KEY (bauherrid) REFERENCES bauherr(idbauherr)

);


 CREATE TABLE IF NOT EXISTS bauartprojekt
(
	bauartid INT, 
	projektid INT,

	FOREIGN KEY (bauartid) REFERENCES bauart(idbauart),
	FOREIGN KEY (projektid) REFERENCES projekt(idprojekt)

/*
	KEY `FK_bauart` (`bauartid`),
	CONSTRAINT `FK_bauart` FOREIGN KEY (`bauartid`) REFERENCES `bauart` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

	KEY `FK_projekt_bauart` (`projektid`),
	CONSTRAINT `FK_projekt_bauart` FOREIGN KEY (`projektid`) REFERENCES `projekt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
	*/
);


 CREATE TABLE IF NOT EXISTS projektverfasserprojekt
(
	projektverfasserid INT, 
	projektid INT,

	FOREIGN KEY (projektverfasserid) REFERENCES projektverfasser(idprojektverfasser),
	FOREIGN KEY (projektid) REFERENCES projekt(idprojekt)

/*
	KEY `FK_brojektverfasser` (`projektverfasserid`),
	CONSTRAINT `FK_brojektverfasser` FOREIGN KEY (`projektverfasserid`) REFERENCES `projektverfasser` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

	KEY `FK_projekt_projektverfasser` (`projektid`),
	CONSTRAINT `FK_projekt_projektverfasser` FOREIGN KEY (`projektid`) REFERENCES `projekt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
*/
);


 CREATE TABLE IF NOT EXISTS leistungprojekt
(
	leistungid INT, 
	projektid INT,

	FOREIGN KEY (leistungid) REFERENCES leistung(idleistung),
	FOREIGN KEY (projektid) REFERENCES projekt(idprojekt)

/*
	KEY `FK_leistung` (`leistungid`),
	CONSTRAINT `FK_leistung` FOREIGN KEY (`leistungid`) REFERENCES `leistung` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

	KEY `FK_projekt_Leistung` (`projektid`),
	CONSTRAINT `FK_projekt_Leistung` FOREIGN KEY (`projektid`) REFERENCES `projekt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
	*/
);

