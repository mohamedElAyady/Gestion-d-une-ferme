/*CREATE DATABASE mazraaty;*/
CREATE TABLE `mazraaty`.`vache` (`ID` INT(25) NOT NULL AUTO_INCREMENT ,
 `sexe` ENUM('male','female') NOT NULL , `type` VARCHAR(50) NOT NULL ,
  `date_naiss` DATE NOT NULL , `statut` VARCHAR(50) NOT NULL ,
PRIMARY KEY (`ID`)) ENGINE = InnoDB;


CREATE TABLE `mazraaty`.`production` (`ID` INT NOT NULL AUTO_INCREMENT ,
 `ID_vache` INT NOT NULL , `litres` INT NOT NULL , `date_enrg` DATE NOT NULL ,
`prix_litre` FLOAT NOT NULL , `total` FLOAT NOT NULL , PRIMARY KEY (`ID`)) ENGINE = InnoDB;
/*add foreign key*/
ALTER TABLE `production` ADD CONSTRAINT `fk_vache_id` FOREIGN KEY (`ID_vache`)
REFERENCES `vache`(`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;


CREATE TABLE `mazraaty`.`alimentation` (`ID` INT NOT NULL AUTO_INCREMENT ,
 `ID_vache` INT NOT NULL , `produit` VARCHAR(40) NOT NULL , `quantité` FLOAT NOT NULL
 , `date_enrg` DATE NOT NULL ,
 `remarque` TEXT NULL DEFAULT NULL , PRIMARY KEY (`ID`)) ENGINE = InnoDB;
/*add foreign key*/
ALTER TABLE `alimentation` ADD CONSTRAINT `fk_vache_alim` FOREIGN KEY (`ID_vache`)
REFERENCES `vaches`(`ID_vache`)
 ON DELETE CASCADE ON UPDATE CASCADE;


CREATE TABLE `mazraaty`.`stock` (`ID` INT NOT NULL , `produit` VARCHAR(50) NOT NULL
, `quantite` FLOAT NOT NULL , PRIMARY KEY (`ID`)) ENGINE = InnoDB;
ALTER TABLE `stock` CHANGE `ID` `ID` INT(11) NOT NULL AUTO_INCREMENT;


CREATE TABLE `mazraaty`.`stock_disp`
(`produit` VARCHAR(40) NOT NULL , `quantite` FLOAT NOT NULL ) ENGINE = InnoDB;

CREATE TABLE `mazraaty`.`vente_lait` (`ID` INT NOT NULL ,
 `name_client` VARCHAR(40) NOT NULL , `litres` INT NOT NULL , `prix_litre` FLOAT NOT NULL ,
  `date_enrg` DATE NOT NULL ,
 `total` FLOAT NOT NULL , PRIMARY KEY (`ID`)) ENGINE = InnoDB;