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
(
idbauart, bauart
)
VALUES
(
 '1', 'Einfamilienhaus'
),
(
 '2', 'Mehrfamilienhaus'
),
(
 '3', 'Öffentlicher Bau'
),
(
 '4', 'Gewerbebau'
)
;


 /* Prjektverfasser abfüllen */
INSERT INTO projektverfasser
(
idprojektverfasser, projektverfasser
)
VALUES
(
 '1', 'ARCHITEKTUR PITBAU'
),
(
 '2', 'Lampert Architektur'
),
(
 '3', 'Xylo AG'
)
;


 /* Leistung abfüllen */
INSERT INTO leistung
(
idleistung, leistungen
)
VALUES
(
 '1', 'Projektierung'
),
(
 '2', 'Ausführungsplanung'
),
(
 '3', 'Bauleitung'
)
;


 /* Ort abfüllen */
INSERT INTO ort
(
idort, ort, plz
)
VALUES
(
 '1', 'Triesenberg', '9497'
),
(
 '2', 'Balzers', '9494'
 ),
(
 '3', 'Malbun', '9497'
),
(
 '4', 'Gamprin', '9487'
),
(
 '5', 'Schaan', '9494'
)
;


 /* Bauherr abfüllen */
INSERT INTO bauherr
(
idbauherr, bauherr
)
VALUES
(
 '1', 'Gemeinde Triesenberg'
),
(
 '2', 'Privat'
),
(
 '3', 'BEVOLA Immobilien Anstalt'
),
(
 '4', 'Hasler Rainer & Petra'
),
(
 '5', 'Clemens Lampert'
)
;



/* Projekt abfüllen */
INSERT INTO projekt
(
idprojekt, projektnummer, projektname, ortid, koordx, koordy, realisierungsjahr, bausumme, bauherrid
)
VALUES
(
 '1', '0502', 'Kindergarten Taescherloch', '1', '2759543', '1220275', '2009' ,'2860000', '1'
),
(
 '2', '0919', 'Gewerbehalle Balzers', '2', '2757459', '1216049 ', '2011' ,'3500000', '2'
),
(
 '3', '0943', 'MFH Stubi', '3', '2764691', '1218983', '2016' ,'7940000', '3'
),
(
 '4', '1002', 'EFH Hasler', '4', '2756850', '1232394', '2012' ,'915000', '2'
),
(
 '5', '1128', 'MFH Lampert', '5', '2757674', '1225878', '2014' ,'5700000', '2'
)
;


 /* Bauerat_Projekt abfüllen */
INSERT INTO bauartprojekt
(
bauartid, projektid
)
VALUES
(
 '3', '1'
),
(
 '4', '2'
),
(
 '2', '3'
),
(
 '1', '4'
),
(
 '2', '5'
)
;


 /* Projektverfasser_Projekt abfüllen */
INSERT INTO projektverfasserprojekt
(
projektverfasserid, projektid
)
VALUES
(
 '1', '1'
),
(
 '2', '1'
),
(
 '3', '1'
),

(
 '1', '2'
),
(
 '1', '3'
),
(
 '1', '4'
),
(
 '1', '5'
)
;


 /* Leistung_Projekt abfüllen */
INSERT INTO leistungprojekt
(
leistungid, projektid
)
VALUES
(
 '1', '1'
),
(
 '2', '1'
),
(
 '3', '1'
),


(
 '1', '2'
),
(
 '2', '2'
),
(
 '3', '2'
),


(
 '1', '3'
),
(
 '2', '3'
),
(
 '3', '3'
),


(
 '1', '4'
),
(
 '2', '4'
),
(
 '3', '4'
),


(
 '1', '5'
),
(
 '2', '5'
),
(
 '3', '5'
)
;