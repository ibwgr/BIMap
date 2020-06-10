use bimap;

TRUNCATE TABLE ort;
TRUNCATE TABLE bauherr;
TRUNCATE TABLE bauart;
TRUNCATE TABLE projektverfasser;
TRUNCATE TABLE leistung;
TRUNCATE TABLE projekt;
TRUNCATE TABLE bauartprojekt;
TRUNCATE TABLE projektverfasserprojekt;
TRUNCATE TABLE leistungprojekt;



 /* Bauart abfüllen */
INSERT INTO bauart
(idbauart, bauart)
VALUES
('1', 'Einfamilienhaus'),
('2', 'Mehrfamilienhaus'),
('3', 'Öffentlicher Bau'),
('4', 'Gewerbebau'),
('5', 'Strassenbau'),
('6', 'Gleisbau'),
('7', 'Kommunaler Tiefbau'),
('8', 'Touristische Anlagen'),
('9', 'Kunstbauten'),
('10', 'Holzbau'),
('11', 'Konstruktiver Ingenieurbau')
;


 /* Prjektverfasser abfüllen */
INSERT INTO projektverfasser
(idprojektverfasser, projektverfasser)
VALUES
('1', 'ARCHITEKTUR PITBAU'),
('2', 'Lampert Architektur'),
('3', 'Xylo AG'),
('4', 'FHP Bauingenieure AG'),
('5', 'Wüst Rellstab Schmid AG'),
('6', 'Sieber Cassina + Handke AG')
;


 /* Leistung abfüllen */
INSERT INTO leistung
(idleistung, leistungen)
VALUES
('1', 'Projektierung'),
('2', 'Ausführungsplanung'),
('3', 'Bauleitung'),
('4', 'Studie'),
('5', 'Auflageprojekt'),
('6', 'Detail- und Bauprojekt'),
('7', 'Submissionsprojekt'),
('8', 'Ausführungsprojekt'),
('9', 'Inbetriebnahme, Abschluss'),
('10', 'Technische Baubegleitung')
;


 /* Ort abfüllen */
INSERT INTO ort
(idort, ort, plz)
VALUES
('1', 'Triesenberg', '9497'),
('2', 'Balzers', '9494'),
('3', 'Malbun', '9497'),
('4', 'Gamprin', '9487'),
('5', 'Schaan', '9494'),
('6', 'Arosa', '7050'),
('7', 'Ardez', '7546'),
('8', 'Chur', '7000'),
('9', 'Laax', '7031'),
('10', 'Herisau', '9100')
;


 /* Bauherr abfüllen */
INSERT INTO bauherr
(idbauherr, bauherr)
VALUES
('1', 'Gemeinde Triesenberg'),
('2', 'Privat'),
('3', 'BEVOLA Immobilien Anstalt'),
('4', 'Hasler Rainer & Petra'),
('5', 'Clemens Lampert'),
('6', 'Rhätische Bahn AG'),
('7', 'Tiefbauamt Graubünden'),
('8', 'ASTRA'),
('9', 'Weisse Arena Bergbahnen AG'),
('10', 'Tiefbauamt AR')
;



/* Projekt abfüllen */
INSERT INTO projekt
(idprojekt, projektnummer, projektname, ortid, koordx, koordy, realisierungsjahr, bausumme, bauherrid)
VALUES
('1', '0502', 'Kindergarten Taescherloch', '1', '2759543', '1220275', '2009' ,'2860000', '1'),
('2', '0919', 'Gewerbehalle Balzers', '2', '2757459', '1216049 ', '2011' ,'3500000', '2'),
('3', '0943', 'MFH Stubi', '3', '2764691', '1218983', '2016' ,'7940000', '3'),
('4', '1002', 'EFH Hasler', '4', '2756850', '1232394', '2012' ,'915000', '2'),
('5', '1128', 'MFH Lampert', '5', '2757674', '1225878', '2014' ,'5700000', '2'),
('6', '2047', 'Stationsumbau Arosa', '6', '2771084', '1183922', '2014', '24400000', '6'),
('7', '1069', 'Strassenkorrektion Giarsun - Ardez', '7', '2808318', '1183671', '2018','5700000', '7'),
('8', '1083', 'N13 EP17 Chur Nord - AS Zizers/Untervaz', '8', '2760953', '1197348', '2018', '35000000', '8'),
('9', '6012', 'Speichersee Nagens', '9', '2737162', '1191112', '2002', '3600000', '9'),
('10', '1093', 'Kreuzung Bahnhofstrasse', '10', '2738645', '1250344', '2020', '16500000', '10')
;


 /* Baurat_Projekt abfüllen */
INSERT INTO bauartprojekt
(bauartid, projektid)
VALUES
('3', '1'),
('4', '2'),
('2', '3'),
('1', '4'),
('2', '5'),
('6','6'),('7','6'),
('5','7'),('6','7'),
('5','8'),('9','8'),
('8','9'),
('5','10'),('6','10'),('7','10'),('9','10')
;


 /* Projektverfasser_Projekt abfüllen */
INSERT INTO projektverfasserprojekt
(projektverfasserid, projektid)
VALUES
('1', '1'),('2', '1'),('3', '1'),
('1', '2'),
('1', '3'),
('1', '4'),
('1', '5'),
('4','6'),
('4','7'),
('4','8'),('5','8'),
('4','9'),
('4','10'),('5','10'),('6','10')
;


 /* Leistung_Projekt abfüllen */
INSERT INTO leistungprojekt
(leistungid, projektid)
VALUES
('1', '1'),('2', '1'),('3', '1'),
('1', '2'),('2', '2'),('3', '2'),
('1', '3'),('2', '3'),('3', '3'),
('1', '4'),('2', '4'),('3', '4'),
('1', '5'),('2', '5'),('3', '5'),
('5','6'),('6','6'),('8','6'),('9','6'),
('5','7'),('6','7'),('7','7'),('8','7'),('9','7'),('10','7'),
('6','8'),('7','8'),('8','8'),('9','8'),('10','8'),
('6','9'),('7','9'),('8','9'),('9','9'),('10','9'),
('5','10'),('6','10'),('7','10')
;