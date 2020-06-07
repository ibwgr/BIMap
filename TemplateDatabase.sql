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


-- Exportiere Datenbank Struktur für apiedi
CREATE DATABASE IF NOT EXISTS `apiedi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `apiedi`;

-- Exportiere Struktur von Tabelle apiedi.angebot
CREATE TABLE IF NOT EXISTS `angebot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `behart` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `betrag` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Exportiere Daten aus Tabelle apiedi.angebot: ~3 rows (ungefähr)
DELETE FROM `angebot`;
/*!40000 ALTER TABLE `angebot` DISABLE KEYS */;
INSERT INTO `angebot` (`id`, `behart`, `betrag`) VALUES
	(1, 'behKurz', 50),
	(2, 'behMittel', 85),
	(3, 'behLang', 90);
/*!40000 ALTER TABLE `angebot` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle apiedi.ausgabe
CREATE TABLE IF NOT EXISTS `ausgabe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(50) DEFAULT NULL,
  `bemerkung` varchar(50) DEFAULT NULL,
  `betrag` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Exportiere Daten aus Tabelle apiedi.ausgabe: ~2 rows (ungefähr)
DELETE FROM `ausgabe`;
/*!40000 ALTER TABLE `ausgabe` DISABLE KEYS */;
INSERT INTO `ausgabe` (`id`, `date`, `bemerkung`, `betrag`) VALUES
	(1, '2020-01-01', 'Miete', 500),
	(2, '2020-02-02', 'Telefon', 50);
/*!40000 ALTER TABLE `ausgabe` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle apiedi.behandlung
CREATE TABLE IF NOT EXISTS `behandlung` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `angebotid` int(11) DEFAULT NULL,
  `anzahl` int(11) DEFAULT 0,
  `summe` float DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FK_behandlung_angebot` (`angebotid`),
  CONSTRAINT `FK_behandlung_angebot` FOREIGN KEY (`angebotid`) REFERENCES `angebot` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Exportiere Daten aus Tabelle apiedi.behandlung: ~14 rows (ungefähr)
DELETE FROM `behandlung`;
/*!40000 ALTER TABLE `behandlung` DISABLE KEYS */;
INSERT INTO `behandlung` (`id`, `date`, `angebotid`, `anzahl`, `summe`) VALUES
	(1, '2015-08-08', 1, 4, 0),
	(2, '2015-08-09', 2, 5, 0),
	(3, '2015-08-10', 3, 6, 0),
	(4, '2015-12-12', 1, 1, 50),
	(5, '2015-12-12', 1, 1, 50),
	(6, '2020-01-01', 2, 20, 1700),
	(7, '2020-12-12', 1, 30, 1500),
	(8, '2020-01-05', 3, 1, 90),
	(9, '2020-01-05', 3, 1, 90),
	(10, '2020-01-05', 3, 1, 90),
	(11, '2020-01-05', 3, 1, 90),
	(12, '2000-01-01', 1, 1, 50),
	(13, '2013-01-01', 1, 3, 150),
	(14, '2020-01-03', 1, 3, 150),
	(15, '2020-03-02', 2, 5, 425);
/*!40000 ALTER TABLE `behandlung` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
