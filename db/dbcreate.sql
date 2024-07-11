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
  tipologia varchar(10) not null
);


INSERT INTO info_prodotto (Nome, costo, descrizione, disponibilità, tipologia) 
VALUES ('Burro di arachidi proteico', 12.00, 'il nostro cremisissimo burro di arachidi proteico, con pezzi di arachide interi per un extra crunch', 20, 'cibo'); 
INSERT INTO info_prodotto (Nome, costo, descrizione, disponibilità, tipologia) 
VALUES ('100% Proteine solubile, al gusto brownie', 27.50, '1000g di proteine pure al 100% per le tue sessioni di workout più intense. Ora al sapore di Brownie al cioccolato', 15, 'proteine'); 
INSERT INTO info_prodotto (Nome, costo, descrizione, disponibilità, tipologia) 
VALUES ('Shaker S', 9.99, 'shaker di plastica di alta qualita nero trasparente con tappo collegato e materiale termico isolante', 10, 'altro'); 
INSERT INTO info_prodotto (Nome, costo, descrizione, disponibilità, tipologia) 
VALUES ('Pancake proteico', 14.70, 'preparato con ricetta per preaparere i tuoi pankake proteici per una dolce e salutare colazione', 45, 'cibo'); 

create table prodotti 
(
 Codice int PRIMARY KEY not null AUTO_INCREMENT, 
 info_correnti int not null,
 nome varchar(70) not null,
foreign key (info_correnti) References info_prodotto(codice) On Delete cascade on update cascade
);
INSERT INTO prodotti (info_correnti, nome) 
VALUES (1,'Burro di arachidi proteico'); 
INSERT INTO prodotti (info_correnti, nome) 
VALUES (2,'100% Proteine solubile, al gusto brownie'); 
INSERT INTO prodotti (info_correnti, nome) 
VALUES (3,'Shaker S'); 
INSERT INTO prodotti (info_correnti, nome) 
VALUES (4,'Pancake proteico'); 

create table imagine 
(
   num_Imagine int auto_increment PRIMARY KEY,
   img varchar(100) not null,
   Codice_Prodotto int not null,
   foreign key (Codice_Prodotto) References Prodotti(Codice) On Delete cascade on update cascade
);

create table coupons
(
 Codice varchar(13) PRIMARY KEY not null, 
 percentuale_sconto int not null,
 saldo_minimo decimal(10,2) not null
);

INSERT INTO coupons (Codice, percentuale_sconto, saldo_minimo) 
VALUES ('001201002', 20, 20.40);
INSERT INTO coupons (Codice, percentuale_sconto, saldo_minimo) 
VALUES ('001201102', 50, 50.00);
INSERT INTO coupons (Codice, percentuale_sconto, saldo_minimo) 
VALUES ('001201202', 10, 25.00);
INSERT INTO coupons (Codice, percentuale_sconto, saldo_minimo) 
VALUES ('001201302', 30, 32.00);

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

INSERT INTO tabelleNutrizionali(Codice_prodotto, energia, grassi, grassi_saturi, carboedrati, zucherri, fibre, proteine, sale) 
VALUES (1, 2004, 4.12, 1.45, 1.05, 7.56, 0.01, 5.60, 0.10);
INSERT INTO tabelleNutrizionali(Codice_prodotto, energia, grassi, grassi_saturi, carboedrati, zucherri, fibre, proteine, sale) 
VALUES (2, 3674, 4.12, 1.45, 1.05, 7.56, 0.01, 5.60, 0.10);
INSERT INTO tabelleNutrizionali(Codice_prodotto, energia, grassi, grassi_saturi, carboedrati, zucherri, fibre, proteine, sale) 
VALUES (4, 2784, 4.12, 1.45, 1.05, 7.56, 0.01, 5.60, 0.10);

create table ordini 
(
 Utente varchar(18) NOT NULL,
 Codice int not null,
 indirizzo varchar(18) not null,
 stato varchar(10) not  null,
 data_ordine date not null,
 saldo_totale decimal(10,2) not null default 0.0,
 PRIMARY KEY(Utente, Codice),
 foreign key (Utente) References Utenti(Username) ON UPDATE cascade on delete cascade,
 foreign key (utente, indirizzo) References Indirizzi(utente, id) ON UPDATE cascade on delete cascade
);

INSERT INTO ordini(Utente, codice, indirizzo, stato, data_ordine, saldo_totale) 
VALUES ('user', 1, 'adUser1-2', 'spedito', '2024-06-19', 21.90);
INSERT INTO ordini(Utente, codice, indirizzo, stato, data_ordine, saldo_totale) 
VALUES ('user2', 1, 'adUser2-1', 'consegnato', '2024-04-23', 24.69);
INSERT INTO ordini(Utente, codice, indirizzo, stato, data_ordine, saldo_totale) 
VALUES ('user', 2, 'adUser1-1', 'pronto', '2024-06-19', 36.69);
INSERT INTO ordini(Utente, codice, indirizzo, stato, data_ordine, saldo_totale) 
VALUES ('user2', 2, 'adUser2-1', 'spedito', '2024-06-19', 12.00);

create table contiene 
(
 Utente varchar(18) NOT NULL,
 Codice_Ordine int not null,
 Codice_Prodotto int not null,
 PRIMARY KEY(Codice_Ordine, Codice_Prodotto, Utente),
 foreign key (Utente, Codice_Ordine) References Ordini(Utente, Codice) ON UPDATE cascade on delete cascade,
 foreign key (Codice_Prodotto) References Prodotti(Codice) ON UPDATE cascade on delete cascade
);   

INSERT INTO contiene (Utente, Codice_Ordine, Codice_Prodotto) 
VALUES ('user', 1, 1);
INSERT INTO contiene (Utente, Codice_Ordine, Codice_Prodotto) 
VALUES ('user', 1, 3);
INSERT INTO contiene (Utente, Codice_Ordine, Codice_Prodotto) 
VALUES ('user2', 1, 3);
INSERT INTO contiene (Utente, Codice_Ordine, Codice_Prodotto) 
VALUES ('user2', 1, 4);
INSERT INTO contiene (Utente, Codice_Ordine, Codice_Prodotto) 
VALUES ('user', 2, 3);
INSERT INTO contiene (Utente, Codice_Ordine, Codice_Prodotto) 
VALUES ('user', 2, 4);
INSERT INTO contiene (Utente, Codice_Ordine, Codice_Prodotto) 
VALUES ('user', 2, 1);
INSERT INTO contiene (Utente, Codice_Ordine, Codice_Prodotto) 
VALUES ('user2', 1, 1);

