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