DROP SCHEMA IF EXISTS shapedibles;

Create schema shapedibles;
use shapedibles;

create table utenti (
 Username varchar(18) PRIMARY KEY NOT NULL,
 email varchar(25) NOT NULL,
 pass varchar(5000) NOT NULL,
 nome_cognome varchar(40) NOT NULL, 
 sesso  varchar(25) NOT NULL,
 paese varchar(40) NOT NULL,
 data_nascita date NOT NULL,
 user_admin  tinyint(1) NOT NULL
);

INSERT INTO utenti (Username, email, pass, nome_cognome, sesso, paese, data_nascita, user_admin) 
VALUES ('admin', 'TryAdmin1@gmail.com', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', 'Giovanni Potage', 'uomo',	'Italia', '2024-03-17',	1); 
INSERT INTO utenti (Username, email, pass, nome_cognome, sesso, paese, data_nascita, user_admin)  
VALUES ('user', 'TryUser1@gmail.com', 'b14361404c078ffd549c03db443c3fede2f3e534d73f78f77301ed97d4a436a9fd9db05ee8b325c0ad36438b43fec8510c204fc1c1edb21d0941c00e9e2c1ce2', 'Molly Blindeff', 'donna', 'Italia', '2024-03-17', 0); 
INSERT INTO utenti (Username, email, pass, nome_cognome, sesso, paese, data_nascita, user_admin) 
VALUES ('user2', 'TryUser2@gmail.com', '291116775902b38dd09587ad6235cec503fc14dbf9c09cad761f2e5a5755102eaceb54b95ffd179c22652c3910dbc6ed85ddde7e09eef1ecf3ad219225f509f5', 'Indus Tarbella', 'uomo', 'Italia', '2024-03-17', 0); 
INSERT INTO utenti (Username, email, pass, nome_cognome, sesso, paese, data_nascita, user_admin)
VALUES ('Gabri', 'lol@gmail.com', '30cbb414c16256b85334d8b3e18710f896b25dbb8ab4dd722de7239e5a98d91ed8fd2d51a0465314dcbbc61ffc653765a573f2cb32191713039d796fedc00add', 'Gabriel Tabasco', 'Maschio', 'Italia', '2004-05-01', 1); 

create table indirizzi 
(
 id varchar(13) NOT NULL,
 Utente varchar(18) NOT NULL,
 paese varchar(30) NOT NULL,
 strada varchar(30) NOT NULL,
 città varchar(30) NOT NULL,
 numero int not null,
 codice_postale varchar(6) NOT NULL,
 PRIMARY KEY(Utente, id),
 foreign key (Utente) References Utenti(Username) ON UPDATE cascade on delete cascade
);

INSERT INTO indirizzi (id, Utente, paese, strada, città, numero, codice_postale) 
VALUES ('adAdmin1-1', 'admin', 'Italia', 'via laviano', 'Caserta', 15, '81100'); 
INSERT INTO indirizzi (id, Utente, paese, strada, città, numero, codice_postale) 
VALUES ('adUser1-1', 'user', 'Italia', 'via Garibaldi', 'Salerno', 4, '814121');
INSERT INTO indirizzi (id, Utente, paese, strada, città, numero, codice_postale) 
VALUES ('adUser1-2', 'user', 'Italia', 'via Duomo', 'Milano', 20, '20057');
INSERT INTO indirizzi (id, Utente, paese, strada, città, numero, codice_postale) 
VALUES ('adUser2-1', 'user2', 'Italia', 'via Roma', 'Milano', 10, '20057');


create table info_prodotto
(
  Codice int PRIMARY KEY not null AUTO_INCREMENT, 
  nome varchar(70) not null,
  costo decimal(10,2) not null,
  descrizione varchar(10000) not null, 
  disponibilità int not null,
  tipologia varchar(20) not null
);


INSERT INTO info_prodotto (Nome, costo, descrizione, disponibilità, tipologia) VALUES
('Crema di mandorle proteica', 15.00, 'Deliziosa crema di mandorle ricca di proteine, perfetta per snack sani.', 25, 'Crema Spalmabile'),
('Proteine del siero del latte al cioccolato', 30.00, 'Polvere di proteine del siero del latte al gusto cioccolato, ideale per il post-allenamento.', 30, 'Proteine'),
('Shaker XL', 12.99, 'Shaker XL di alta qualità, perfetto per i tuoi frullati proteici.', 20, 'Shaker'),
('Barrette proteiche al burro di arachidi', 20.00, 'Barrette proteiche al gusto burro di arachidi, perfette per uno snack post-allenamento.', 50, 'Snack'),
('Bevanda proteica al cioccolato', 3.50, 'Deliziosa bevanda proteica al gusto cioccolato, pronta da bere.', 100, 'Bibita'),
('Crema di nocciole proteica', 18.00, 'Cremosa crema di nocciole ricca di proteine, perfetta per uno snack salutare.', 15, 'Crema Spalmabile'),
('Proteine vegane al gusto vaniglia', 28.00, 'Polvere di proteine vegane al gusto vaniglia, ideale per le diete vegane.', 25, 'Proteine'),
('Shaker con scomparti', 14.99, 'Shaker con scomparti separati per polvere e liquidi, perfetto per i tuoi frullati.', 30, 'Shaker'),
('Snack proteici al cioccolato fondente', 22.00, 'Snack croccanti al cioccolato fondente, ricchi di proteine.', 40, 'Snack'),
('Bevanda proteica alla vaniglia', 3.50, 'Bevanda proteica al gusto vaniglia, pronta da bere.', 90, 'Bibita'),
('Burro di anacardi proteico', 16.00, 'Cremoso burro di anacardi arricchito con proteine, ideale per uno spuntino.', 20, 'Crema Spalmabile'),
('Proteine del riso al gusto fragola', 27.00, 'Polvere di proteine del riso al gusto fragola, perfetta per i frullati.', 35, 'Proteine'),
('Shaker termico', 17.99, 'Shaker termico per mantenere i tuoi frullati freschi o caldi più a lungo.', 15, 'Shaker'),
('Barrette proteiche al cioccolato e menta', 21.00, 'Barrette proteiche al gusto cioccolato e menta, perfette per uno snack.', 45, 'Snack'),
('Bevanda proteica al caffè', 4.00, 'Bevanda proteica al gusto caffè, perfetta per un boost mattutino.', 80, 'Bibita'),
('Crema di pistacchi proteica', 20.00, 'Deliziosa crema di pistacchi ricca di proteine, ideale per uno snack.', 10, 'Crema Spalmabile'),
('Proteine del pisello al gusto cioccolato', 26.00, 'Polvere di proteine del pisello al gusto cioccolato, ideale per le diete vegane.', 25, 'Proteine'),
('Shaker portatile', 11.99, 'Shaker portatile compatto e facile da trasportare, perfetto per i tuoi viaggi.', 25, 'Shaker'),
('Snack proteici al cocco', 23.00, 'Snack croccanti al gusto cocco, ricchi di proteine.', 50, 'Snack'),
('Bevanda proteica alla fragola', 3.50, 'Bevanda proteica al gusto fragola, pronta da bere.', 95, 'Bibita');                                                                                   
                                                            
create table prodotti 
(
 Codice int PRIMARY KEY not null AUTO_INCREMENT, 
 info_correnti int not null,
 nome varchar(70) not null,
foreign key (info_correnti) References info_prodotto(codice) On Delete cascade on update cascade
);
INSERT INTO prodotti (info_correnti, nome) VALUES
(1, 'Crema di mandorle proteica'),
(2, 'Proteine del siero del latte al cioccolato'),
(3, 'Shaker XL'),
(4, 'Barrette proteiche al burro di arachidi'),
(5, 'Bevanda proteica al cioccolato'),
(6, 'Crema di nocciole proteica'),
(7, 'Proteine vegane al gusto vaniglia'),
(8, 'Shaker con scomparti'),
(9, 'Snack proteici al cioccolato fondente'),
(10, 'Bevanda proteica alla vaniglia'),
(11, 'Burro di anacardi proteico'),
(12, 'Proteine del riso al gusto fragola'),
(13, 'Shaker termico'),
(14, 'Barrette proteiche al cioccolato e menta'),
(15, 'Bevanda proteica al caffè'),
(16, 'Crema di pistacchi proteica'),
(17, 'Proteine del pisello al gusto cioccolato'),
(18, 'Shaker portatile'),
(19, 'Snack proteici al cocco'),
(20, 'Bevanda proteica alla fragola');



create table Immagine 
(
   num_Immagine int auto_increment PRIMARY KEY,
   img varchar(100) not null,
   Codice_Prodotto int not null,
   foreign key (Codice_Prodotto) References Prodotti(Codice) On Delete cascade on update cascade
);

INSERT INTO Immagine (Codice_prodotto, img) VALUES
(1, 'mandorle_square.jpg'), (1, 'mandorle_wide.jpg'), (1, 'mandorle_transparent.jpg'),
(2, 'cioccolato_square.jpg'), (2, 'cioccolato_wide.jpg'), (2, 'cioccolato_transparent.jpg'),
(3, 'shaker_xl_square.jpg'), (3, 'shaker_xl_wide.jpg'), (3, 'shaker_xl_transparent.jpg'),
(4, 'barrette_arachidi_square.jpg'), (4, 'barrette_arachidi_wide.jpg'), (4, 'barrette_arachidi_transparent.jpg'),
(5, 'bevanda_cioccolato_square.jpg'), (5, 'bevanda_cioccolato_wide.jpg'), (5, 'bevanda_cioccolato_transparent.jpg'),
(6, 'nocciole_square.jpg'), (6, 'nocciole_wide.jpg'), (6, 'nocciole_transparent.jpg'),
(7, 'vaniglia_square.jpg'), (7, 'vaniglia_wide.jpg'), (7, 'vaniglia_transparent.jpg'),
(8, 'shaker_scomparti_square.jpg'), (8, 'shaker_scomparti_wide.jpg'), (8, 'shaker_scomparti_transparent.jpg'),
(9, 'cioccolato_fondente_square.jpg'), (9, 'cioccolato_fondente_wide.jpg'), (9, 'cioccolato_fondente_transparent.jpg'),
(10, 'bevanda_vaniglia_square.jpg'), (10, 'bevanda_vaniglia_wide.jpg'), (10, 'bevanda_vaniglia_transparent.jpg'),
(11, 'anacardi_square.jpg'), (11, 'anacardi_wide.jpg'), (11, 'anacardi_transparent.jpg'),
(12, 'fragola_square.jpg'), (12, 'fragola_wide.jpg'), (12, 'fragola_transparent.jpg'),
(13, 'shaker_termico_square.jpg'), (13, 'shaker_termico_wide.jpg'), (13, 'shaker_termico_transparent.jpg'),
(14, 'cioccolato_menta_square.jpg'), (14, 'cioccolato_menta_wide.jpg'), (14, 'cioccolato_menta_transparent.jpg'),
(15, 'caffe_square.jpg'), (15, 'caffe_wide.jpg'), (15, 'caffe_transparent.jpg'),
(16, 'pistacchi_square.jpg'), (16, 'pistacchi_wide.jpg'), (16, 'pistacchi_transparent.jpg'),
(17, 'pisello_square.jpg'), (17, 'pisello_wide.jpg'), (17, 'pisello_transparent.jpg'),
(18, 'shaker_portatile_square.jpg'), (18, 'shaker_portatile_wide.jpg'), (18, 'shaker_portatile_transparent.jpg'),
(19, 'cocco_square.jpg'), (19, 'cocco_wide.jpg'), (19, 'cocco_transparent.jpg'),
(20, 'bevanda_fragola_square.jpg'), (20, 'bevanda_fragola_wide.jpg'), (20, 'bevanda_fragola_transparent.jpg');

create table tabelleNutrizionali 
(
  Codice_Prodotto int PRIMARY KEY not null,
  energia int not null,
  grassi decimal(10,2) not null,
  grassi_saturi decimal(10,2) not null,
  carboedrati decimal(10,2) not null,
  zucherri decimal(10,2) not null,
  fibre decimal(10,2) not null,
  proteine decimal(10,2) not null,
  sale decimal(10,2) not null,
  foreign key (Codice_Prodotto) References Prodotti(Codice) ON UPDATE cascade on delete cascade
);

INSERT INTO tabelleNutrizionali(Codice_prodotto, energia, grassi, grassi_saturi, carboedrati, zucherri, fibre, proteine, sale) VALUES
(1, 2004, 4.12, 1.45, 1.05, 7.56, 0.01, 5.60, 0.10),
(2, 3674, 4.12, 1.45, 1.05, 7.56, 0.01, 5.60, 0.10),
(3, 1500, 0.50, 0.20, 0.80, 0.30, 0.10, 30.00, 0.05),
(4, 2784, 10.00, 3.50, 22.00, 15.00, 2.50, 20.00, 0.50),
(5, 110, 0.30, 0.10, 20.00, 10.00, 0.50, 20.00, 0.20),
(6, 1800, 16.00, 3.00, 2.00, 1.00, 0.50, 8.00, 0.03),
(7, 120, 0.50, 0.10, 1.00, 0.50, 0.20, 25.00, 0.10),
(8, 0, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00),
(9, 1500, 10.00, 3.50, 15.00, 10.00, 2.50, 20.00, 0.40),
(10, 110, 0.30, 0.10, 20.00, 10.00, 0.50, 20.00, 0.20),
(11, 1800, 14.00, 3.00, 2.00, 1.00, 0.50, 8.00, 0.03),
(12, 120, 0.50, 0.10, 1.00, 0.50, 0.20, 25.00, 0.10),
(13, 0, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00),
(14, 1500, 10.00, 3.50, 15.00, 10.00, 2.50, 20.00, 0.40),
(15, 110, 0.30, 0.10, 20.00, 10.00, 0.50, 20.00, 0.20),
(16, 1800, 14.00, 3.00, 2.00, 1.00, 0.50, 8.00, 0.03),
(17, 120, 0.50, 0.10, 1.00, 0.50, 0.20, 25.00, 0.10),
(18, 0, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00),
(19, 1500, 10.00, 3.50, 15.00, 10.00, 2.50, 20.00, 0.40),
(20, 110, 0.30, 0.10, 20.00, 10.00, 0.50, 20.00, 0.20);
create table ordini 
(
 Utente varchar(18) NOT NULL,
 Codice int not null,
 indirizzo varchar(18) not null,
 stato varchar(10) not  null,
 data_ordine date not null,
 saldo_totale decimal(10,2) not null default 0.0,
 PRIMARY KEY(Codice),
 foreign key (Utente) References Utenti(Username) ON UPDATE cascade on delete cascade,
 foreign key (utente, indirizzo) References Indirizzi(utente, id) ON UPDATE cascade on delete cascade
);

INSERT INTO ordini(Utente, codice, indirizzo, stato, data_ordine, saldo_totale) 
VALUES ('user', 1010, 'adUser1-2', 'spedito', '2024-06-19', 21.90);
INSERT INTO ordini(Utente, codice, indirizzo, stato, data_ordine, saldo_totale) 
VALUES ('user2', 1020, 'adUser2-1', 'consegnato', '2024-04-23', 24.69);
INSERT INTO ordini(Utente, codice, indirizzo, stato, data_ordine, saldo_totale) 
VALUES ('user', 2030, 'adUser1-1', 'pronto', '2024-06-19', 36.69);
INSERT INTO ordini(Utente, codice, indirizzo, stato, data_ordine, saldo_totale) 
VALUES ('user2', 2040, 'adUser2-1', 'spedito', '2024-06-19', 12.00);

create table contiene 
(
 Codice_Ordine int not null,
 Codice_Info int not null,
 Quantità int not null,
 PRIMARY KEY(Codice_Ordine, Codice_Info),
 foreign key (Codice_Ordine) References Ordini(Codice) ON UPDATE cascade on delete cascade,
 foreign key (Codice_Info) References info_prodotto(Codice) ON UPDATE cascade on delete cascade
);   

INSERT INTO contiene ( Codice_Ordine,  Codice_Info, Quantità) 
VALUES (1010, 1, 1);
INSERT INTO contiene ( Codice_Ordine,  Codice_Info, Quantità) 
VALUES (1010, 3, 1);
INSERT INTO contiene ( Codice_Ordine,  Codice_Info, Quantità) 
VALUES (1020, 3, 1);
INSERT INTO contiene ( Codice_Ordine,  Codice_Info, Quantità) 
VALUES (1020, 4, 1);
INSERT INTO contiene ( Codice_Ordine,  Codice_Info, Quantità) 
VALUES (2030, 3, 1);
INSERT INTO contiene ( Codice_Ordine,  Codice_Info, Quantità) 
VALUES (2040, 4, 1);
INSERT INTO contiene ( Codice_Ordine,  Codice_Info, Quantità) 
VALUES (2030, 1, 1);
INSERT INTO contiene ( Codice_Ordine,  Codice_Info, Quantità) 
VALUES (2040, 1, 1);

