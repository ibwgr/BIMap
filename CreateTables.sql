use bimaptest;

/* Tabellen löschen */

DROP TABLE IF EXISTS bimaptest.projekt;
DROP TABLE IF EXISTS bimaptest.bauart;
DROP TABLE IF EXISTS bimaptest.projektverfasser;
DROP TABLE IF EXISTS bimaptest.leistung;
DROP TABLE IF EXISTS bimaptest.ort;
DROP TABLE IF EXISTS bimaptest.bauherr;
DROP TABLE IF EXISTS bimaptest.bauartprojekt;
DROP TABLE IF EXISTS bimaptest.projektverfasserprojekt;
DROP TABLE IF EXISTS bimaptest.leistungprojekt;



/* Tabellen erzeugen */


CREATE TABLE IF NOT EXISTS bauart
(
	idbauart INT(10) NOT NULL, 
	bauart VARCHAR(30),
	PRIMARY KEY (`idbauart`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


 CREATE TABLE IF NOT EXISTS projektverfasser
(
	idprojektverfasser INT(10) NOT NULL, 
	projektverfasser VARCHAR(30),
	PRIMARY KEY (`idprojektverfasser`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


 CREATE TABLE IF NOT EXISTS leistung
(
	idleistung INT(10) NOT NULL, 
	leistungen VARCHAR(30),
	PRIMARY KEY (`idleistung`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


 CREATE TABLE IF NOT EXISTS ort
(
	idort INT(10) NOT NULL, 
	ort VARCHAR(30),
	plz VARCHAR(30),
	PRIMARY KEY (`idort`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci; 


 CREATE TABLE IF NOT EXISTS bauherr
(
	idbauherr INT(10) NOT NULL, 
	bauherr VARCHAR(30),
	PRIMARY KEY (`idbauherr`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci; 



CREATE TABLE IF NOT EXISTS projekt
(
	idprojekt INT(10) NOT NULL, 
	projektnummer INT(10),
	projektname VARCHAR(30),
	ortid INT,
	koordx VARCHAR(20),
	koordy VARCHAR(20),
	realisierungsjahr VARCHAR(10),
	bausumme VARCHAR(20),
	bauherrid INT,
	PRIMARY KEY (`idprojekt`),

	KEY `FK_projekt_ort` (`ortid`),
	CONSTRAINT `FK_projekt_ort` FOREIGN KEY (`ortid`) REFERENCES `ort` (`idort`) ON DELETE CASCADE ON UPDATE CASCADE,

	KEY `FK_bauherr` (`bauherrid`),
	CONSTRAINT `FK_bauherr` FOREIGN KEY (`bauherrid`) REFERENCES `bauherr` (`idbauherr`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci; 




 CREATE TABLE IF NOT EXISTS bauartprojekt
(
	bauartid INT, 
	projektid INT,

	FOREIGN KEY (bauartid) REFERENCES bauart(idbauart) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (projektid) REFERENCES projekt(idprojekt) ON DELETE CASCADE ON UPDATE CASCADE

/*
	KEY `FK_bauart` (`bauartid`),
	CONSTRAINT `FK_bauart` FOREIGN KEY (`bauartid`) REFERENCES `bauart` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

	KEY `FK_projekt_bauart` (`projektid`),
	CONSTRAINT `FK_projekt_bauart` FOREIGN KEY (`projektid`) REFERENCES `projekt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
	*/
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci; 


 CREATE TABLE IF NOT EXISTS projektverfasserprojekt
(
	projektverfasserid INT, 
	projektid INT,

	FOREIGN KEY (projektverfasserid) REFERENCES projektverfasser(idprojektverfasser) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (projektid) REFERENCES projekt(idprojekt) ON DELETE CASCADE ON UPDATE CASCADE

/*
	KEY `FK_brojektverfasser` (`projektverfasserid`),
	CONSTRAINT `FK_brojektverfasser` FOREIGN KEY (`projektverfasserid`) REFERENCES `projektverfasser` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

	KEY `FK_projekt_projektverfasser` (`projektid`),
	CONSTRAINT `FK_projekt_projektverfasser` FOREIGN KEY (`projektid`) REFERENCES `projekt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
*/
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci; 


 CREATE TABLE IF NOT EXISTS leistungprojekt
(
	leistungid INT, 
	projektid INT,

	FOREIGN KEY (leistungid) REFERENCES leistung(idleistung) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (projektid) REFERENCES projekt(idprojekt) ON DELETE CASCADE ON UPDATE CASCADE

/*
	KEY `FK_leistung` (`leistungid`),
	CONSTRAINT `FK_leistung` FOREIGN KEY (`leistungid`) REFERENCES `leistung` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

	KEY `FK_projekt_Leistung` (`projektid`),
	CONSTRAINT `FK_projekt_Leistung` FOREIGN KEY (`projektid`) REFERENCES `projekt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
	*/
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci; 

