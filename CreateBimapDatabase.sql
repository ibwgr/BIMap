-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               10.4.11-MariaDB - mariadb.org binary distribution
-- Server Betriebssystem:        Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Exportiere Datenbank Struktur für bimap
CREATE DATABASE IF NOT EXISTS `bimap` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bimap`;

-- Exportiere Struktur von Tabelle bimap.bauart
CREATE TABLE IF NOT EXISTS `bauart` (
  `idbauart` int(10) NOT NULL,
  `bauart` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idbauart`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle bimap.bauart: ~11 rows (ungefähr)
DELETE FROM `bauart`;
/*!40000 ALTER TABLE `bauart` DISABLE KEYS */;
INSERT INTO `bauart` (`idbauart`, `bauart`) VALUES
	(1, 'Einfamilienhaus'),
	(2, 'Mehrfamilienhaus'),
	(3, 'Öffentlicher Bau'),
	(4, 'Gewerbebau'),
	(5, 'Strassenbau'),
	(6, 'Gleisbau'),
	(7, 'Kommunaler Tiefbau'),
	(8, 'Touristische Anlagen'),
	(9, 'Kunstbauten');
/*!40000 ALTER TABLE `bauart` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle bimap.bauartprojekt
CREATE TABLE IF NOT EXISTS `bauartprojekt` (
  `bauartid` int(11) DEFAULT NULL,
  `projektid` int(11) DEFAULT NULL,
  KEY `bauartid` (`bauartid`),
  KEY `projektid` (`projektid`),
  CONSTRAINT `bauartprojekt_ibfk_1` FOREIGN KEY (`bauartid`) REFERENCES `bauart` (`idbauart`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bauartprojekt_ibfk_2` FOREIGN KEY (`projektid`) REFERENCES `projekt` (`idprojekt`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle bimap.bauartprojekt: ~16 rows (ungefähr)
DELETE FROM `bauartprojekt`;
/*!40000 ALTER TABLE `bauartprojekt` DISABLE KEYS */;
INSERT INTO `bauartprojekt` (`bauartid`, `projektid`) VALUES
	(3, 1),
	(4, 2),
	(2, 3),
	(1, 4),
	(2, 5),
	(6, 6),
	(7, 6),
	(5, 7),
	(6, 7),
	(5, 8),
	(9, 8),
	(8, 9),
	(5, 10),
	(6, 10),
	(7, 10),
	(9, 10);
/*!40000 ALTER TABLE `bauartprojekt` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle bimap.bauherr
CREATE TABLE IF NOT EXISTS `bauherr` (
  `idbauherr` int(10) NOT NULL,
  `bauherr` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idbauherr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle bimap.bauherr: ~10 rows (ungefähr)
DELETE FROM `bauherr`;
/*!40000 ALTER TABLE `bauherr` DISABLE KEYS */;
INSERT INTO `bauherr` (`idbauherr`, `bauherr`) VALUES
	(1, 'Gemeinde Triesenberg'),
	(2, 'Privat'),
	(3, 'BEVOLA Immobilien Anstalt'),
	(6, 'Rhätische Bahn AG'),
	(7, 'Tiefbauamt Graubünden'),
	(8, 'ASTRA'),
	(9, 'Weisse Arena Bergbahnen AG'),
	(10, 'Tiefbauamt AR');
/*!40000 ALTER TABLE `bauherr` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle bimap.leistung
CREATE TABLE IF NOT EXISTS `leistung` (
  `idleistung` int(10) NOT NULL,
  `leistungen` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idleistung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle bimap.leistung: ~10 rows (ungefähr)
DELETE FROM `leistung`;
/*!40000 ALTER TABLE `leistung` DISABLE KEYS */;
INSERT INTO `leistung` (`idleistung`, `leistungen`) VALUES
	(1, 'Projektierung'),
	(2, 'Ausführungsplanung'),
	(3, 'Bauleitung'),
	(4, 'Studie'),
	(5, 'Auflageprojekt'),
	(6, 'Detail- und Bauprojekt'),
	(7, 'Submissionsprojekt'),
	(8, 'Ausführungsprojekt'),
	(9, 'Inbetriebnahme, Abschluss'),
	(10, 'Technische Baubegleitung');
/*!40000 ALTER TABLE `leistung` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle bimap.leistungprojekt
CREATE TABLE IF NOT EXISTS `leistungprojekt` (
  `leistungid` int(11) DEFAULT NULL,
  `projektid` int(11) DEFAULT NULL,
  KEY `leistungid` (`leistungid`),
  KEY `projektid` (`projektid`),
  CONSTRAINT `leistungprojekt_ibfk_1` FOREIGN KEY (`leistungid`) REFERENCES `leistung` (`idleistung`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `leistungprojekt_ibfk_2` FOREIGN KEY (`projektid`) REFERENCES `projekt` (`idprojekt`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle bimap.leistungprojekt: ~38 rows (ungefähr)
DELETE FROM `leistungprojekt`;
/*!40000 ALTER TABLE `leistungprojekt` DISABLE KEYS */;
INSERT INTO `leistungprojekt` (`leistungid`, `projektid`) VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(1, 2),
	(2, 2),
	(3, 2),
	(1, 3),
	(2, 3),
	(3, 3),
	(1, 4),
	(2, 4),
	(3, 4),
	(1, 5),
	(2, 5),
	(3, 5),
	(5, 6),
	(6, 6),
	(8, 6),
	(9, 6),
	(5, 7),
	(6, 7),
	(7, 7),
	(8, 7),
	(9, 7),
	(10, 7),
	(6, 8),
	(7, 8),
	(8, 8),
	(9, 8),
	(10, 8),
	(6, 9),
	(7, 9),
	(8, 9),
	(9, 9),
	(10, 9),
	(5, 10),
	(6, 10),
	(7, 10);
/*!40000 ALTER TABLE `leistungprojekt` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle bimap.ort
CREATE TABLE IF NOT EXISTS `ort` (
  `idort` int(10) NOT NULL,
  `ort` varchar(30) DEFAULT NULL,
  `plz` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle bimap.ort: ~10 rows (ungefähr)
DELETE FROM `ort`;
/*!40000 ALTER TABLE `ort` DISABLE KEYS */;
INSERT INTO `ort` (`idort`, `ort`, `plz`) VALUES
	(1, 'Triesenberg', '9497'),
	(2, 'Balzers', '9494'),
	(3, 'Malbun', '9497'),
	(4, 'Gamprin', '9487'),
	(5, 'Schaan', '9494'),
	(6, 'Arosa', '7050'),
	(7, 'Ardez', '7546'),
	(8, 'Chur', '7000'),
	(9, 'Laax', '7031'),
	(10, 'Herisau', '9100');
/*!40000 ALTER TABLE `ort` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle bimap.projekt
CREATE TABLE IF NOT EXISTS `projekt` (
  `idprojekt` int(10) NOT NULL,
  `projektnummer` int(10) DEFAULT NULL,
  `projektname` varchar(50) DEFAULT NULL,
  `ortid` int(11) DEFAULT NULL,
  `koordx` varchar(20) DEFAULT NULL,
  `koordy` varchar(20) DEFAULT NULL,
  `realisierungsjahr` varchar(10) DEFAULT NULL,
  `bausumme` varchar(20) DEFAULT NULL,
  `bauherrid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idprojekt`),
  KEY `FK_projekt_ort` (`ortid`),
  KEY `FK_bauherr` (`bauherrid`),
  CONSTRAINT `FK_bauherr` FOREIGN KEY (`bauherrid`) REFERENCES `bauherr` (`idbauherr`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_projekt_ort` FOREIGN KEY (`ortid`) REFERENCES `ort` (`idort`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle bimap.projekt: ~10 rows (ungefähr)
DELETE FROM `projekt`;
/*!40000 ALTER TABLE `projekt` DISABLE KEYS */;
INSERT INTO `projekt` (`idprojekt`, `projektnummer`, `projektname`, `ortid`, `koordx`, `koordy`, `realisierungsjahr`, `bausumme`, `bauherrid`) VALUES
	(1, 502, 'Kindergarten Taescherloch', 1, '2759543', '1220275', '2009', '2860000', 1),
	(2, 919, 'Gewerbehalle Balzers', 2, '2757459', '1216049 ', '2011', '3500000', 2),
	(3, 943, 'MFH Stubi', 3, '2764691', '1218983', '2016', '7940000', 3),
	(4, 1002, 'EFH Hasler', 4, '2756850', '1232394', '2012', '915000', 2),
	(5, 1128, 'MFH Lampert', 5, '2757674', '1225878', '2014', '5700000', 2),
	(6, 2047, 'Stationsumbau Arosa', 6, '2771084', '1183922', '2014', '24400000', 6),
	(7, 1069, 'Strassenkorrektion Giarsun - Ardez', 7, '2808318', '1183671', '2018', '5700000', 7),
	(8, 1083, 'N13 EP17 Chur Nord - AS Zizers/Untervaz', 8, '2760953', '1197348', '2018', '35000000', 8),
	(9, 6012, 'Speichersee Nagens', 9, '2737162', '1191112', '2002', '3600000', 9),
	(10, 1093, 'Kreuzung Bahnhofstrasse', 10, '2738645', '1250344', '2020', '16500000', 10);
/*!40000 ALTER TABLE `projekt` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle bimap.projektverfasser
CREATE TABLE IF NOT EXISTS `projektverfasser` (
  `idprojektverfasser` int(10) NOT NULL,
  `projektverfasser` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idprojektverfasser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle bimap.projektverfasser: ~6 rows (ungefähr)
DELETE FROM `projektverfasser`;
/*!40000 ALTER TABLE `projektverfasser` DISABLE KEYS */;
INSERT INTO `projektverfasser` (`idprojektverfasser`, `projektverfasser`) VALUES
	(1, 'ARCHITEKTUR PITBAU'),
	(2, 'Lampert Architektur'),
	(3, 'Xylo AG'),
	(4, 'FHP Bauingenieure AG'),
	(5, 'Wüst Rellstab Schmid AG'),
	(6, 'Sieber Cassina + Handke AG');
/*!40000 ALTER TABLE `projektverfasser` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle bimap.projektverfasserprojekt
CREATE TABLE IF NOT EXISTS `projektverfasserprojekt` (
  `projektverfasserid` int(11) DEFAULT NULL,
  `projektid` int(11) DEFAULT NULL,
  KEY `projektverfasserid` (`projektverfasserid`),
  KEY `projektid` (`projektid`),
  CONSTRAINT `projektverfasserprojekt_ibfk_1` FOREIGN KEY (`projektverfasserid`) REFERENCES `projektverfasser` (`idprojektverfasser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `projektverfasserprojekt_ibfk_2` FOREIGN KEY (`projektid`) REFERENCES `projekt` (`idprojekt`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle bimap.projektverfasserprojekt: ~15 rows (ungefähr)
DELETE FROM `projektverfasserprojekt`;
/*!40000 ALTER TABLE `projektverfasserprojekt` DISABLE KEYS */;
INSERT INTO `projektverfasserprojekt` (`projektverfasserid`, `projektid`) VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 5),
	(4, 6),
	(4, 7),
	(4, 8),
	(5, 8),
	(4, 9),
	(4, 10),
	(5, 10),
	(6, 10);
/*!40000 ALTER TABLE `projektverfasserprojekt` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
