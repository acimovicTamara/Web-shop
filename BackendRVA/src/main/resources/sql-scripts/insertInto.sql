--kredit
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES (nextval('kredit_seq'), 'Nenamenski hipotekarni kredit', 'hipKredit1', 'Instrument osiguranja je obavezna hipoteka na nekretnini, a namenu utroska sredstava kredita nije potrebno dokazivati');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES (nextval('kredit_seq'), 'Nenamenski (gotovinski) krediti', 'gotKredit2', 'Odobrena sredstva se isplacuju na vas tekuci racun, a ista mozete koristiti prema vlastitim potrebama (za nabavku kucnih aparata, namestaja, putovanja i slicno.');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES (nextval('kredit_seq'), 'Lombardni kredit', 'lomKredit3', 'Predstavlja nenamenski kratkorocni ili dugorocni kredit ciji je iznos obezbedjen 100% depozitom.');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES (nextval('kredit_seq'), 'Stambeni kredit sa hipotekom', 'stambKredit4', 'Sluze iskljucivo za kupovinu stambenih jedinica, njihovu izgradnju i dovrsavanje, dogradnju, renoviranje i adaptaciju stambenog objekta.');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES (nextval('kredit_seq'), 'Brzi krediti na bankomatima', 'brzKredit5', 'Podizete manje iznose kredita bez kamate iz odobrenog limita kada vi to zelite.');

--tip racuna
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES (nextval('tip_racuna_seq'), 'tekuci', 'tek1', 'Racun koji se koristi za izvrsavanje redovnih platnih transakcija – uplata, prenos i isplata novcanih sredstava.');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES (nextval('tip_racuna_seq'), 'ziro', 'zir2', 'Racun koji se koristi kada se prima honorar ili naknada od povremenog posla');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES (nextval('tip_racuna_seq'), 'stedni', 'ste3', ' Besplatan racun i namenjen je klijentima koji su se opredelili za samo jednu oblast bankarskog poslovanja.');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES (nextval('tip_racuna_seq'), 'namenski', 'nam4', 'Sluzi za priliv novcanih sredstava, po osnovu prodatih HoV ili za uplatu novcanih sredstava prilikom kupovine HoV.');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES (nextval('tip_racuna_seq'), 'devizni', 'dev5', 'Moze se koristiti za prilive kako iz inostranstva.');

--klijent
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Tamara', 'Acimovic', '536521458',1);
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Natasa', 'Andric', '789665412',5);
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Dejana', 'Coskov', '889966335',4);
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Teodora', 'Grubor', '993364461',2);
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Jovana', 'Jovanovic', '010198876',3);

--racun
INSERT INTO "racun"("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
VALUES (nextval('racun_seq'), 'Farmer Hit Basic ', 'farHit1', 'Namenski racun poljoprivrednika',4,1);
INSERT INTO "racun"("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
VALUES (nextval('racun_seq'), 'Start racun', 'start', 'Tekuci racun je posebno kreiran u skladu sa potrebama mladih, starosti od 18 do 27 godina. Bez troskova mesecnog odrzavanja.',1,3);
INSERT INTO "racun"("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
VALUES (nextval('racun_seq'), 'Ekspert racun', 'eksp', 'Namenski racun za mali biznis i preduzetnistvo',4,2);
INSERT INTO "racun"("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
VALUES (nextval('racun_seq'), 'Klasik racun', 'klasik', 'Tekuci racunn namenjen je penzionerima',1,4);
INSERT INTO "racun"("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
VALUES (nextval('racun_seq'), 'Super stednja racun', 'super', 'stedni racun sa kamatnom stopom od 1.50%.',3,5);

