 /* Projekt abfüllen */
INSERT INTO bimap.projekt
(
id, projektnummer, projektname, ortid, koordx, koordy, realisierungsjahr, bausumme, bauherrid
)
VALUES
(
 '1', '0502', 'Kindergarten Taescherloch', '1', '2''759''543', '1''220''275', '2009' ,'2''860''000', '1'
),
(
 '2', '0919', 'Gewerbehalle Balzers', '2', '2''757''459', '1''216''049 ', '2011' ,'3''500''000', '2'
),
(
 '3', '0943b', 'MFH Stubi', '3', '2''764''691', '1''218''983', '2016' ,'7''940''000', '3'
),
(
 '4', '1002', 'EFH Hasler', '4', '2''756''850', '1''232''394', '2012' ,'915''000', '4'
)
(
 '5', '1128', 'MFH Lampert', '5', '2''757''674', '1''225''878', '2014' ,'5''700''000', '5'
)
;


 /* Bauart abfüllen */
INSERT INTO bimap.bauart
(
id, bauart
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
INSERT INTO bimap.projektverfasser
(
id, projektverfasser
)
VALUES
(
 '1', 'ARCHITEKTUR PITBAU'
)
;


 /* Leistung abfüllen */
INSERT INTO bimap.leistung
(
id, leistungen, bauartid
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
INSERT INTO bimap.ort
(
id, ort, plz
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
INSERT INTO bimap.bauherr
(
id, bauherr
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
)
(
 '5', 'Clemens Lampert'
)
;


 /* Bauerat_Projekt abfüllen */
INSERT INTO bimap.bauartprojekt
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
)
(
 '2', '5'
)
;


 /* Projektverfasser_Projekt abfüllen */
INSERT INTO bimap.projektverfasserprojekt
(
projektverfasserid, projektid
)
VALUES
(
 '1', '1'
),
(
 '1', '2'
),
(
 '1', '3'
),
(
 '1', '4'
)
(
 '1', '5'
)
;


 /* Leistung_Projekt abfüllen */
INSERT INTO bimap.leistungprojekt
(
leistungid, projektid
)
VALUES
(
 '2', '1'
),
(
 '2', '2'
),
(
 '2', '3'
),
(
 '2', '4'
)
(
 '2', '5'
)
;