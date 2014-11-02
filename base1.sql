SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Name1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Name1` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Name1` ;

-- -----------------------------------------------------
-- Table `Name1`.`UsersGroup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Name1`.`UsersGroup` ;

CREATE TABLE IF NOT EXISTS `Name1`.`UsersGroup` (
  `idUsersGroup` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`idUsersGroup`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Name1`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Name1`.`Users` ;

CREATE TABLE IF NOT EXISTS `Name1`.`Users` (
  `idUsers` INT NOT NULL AUTO_INCREMENT,
  `eMail` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `usersGroup` INT NULL,
  PRIMARY KEY (`idUsers`),
  INDEX `usersgroup_idx` (`usersGroup` ASC),
  CONSTRAINT `usersgroup`
    FOREIGN KEY (`usersGroup`)
    REFERENCES `Name1`.`UsersGroup` (`idUsersGroup`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Name1`.`Article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Name1`.`Article` ;

CREATE TABLE IF NOT EXISTS `Name1`.`Article` (
  `idArticle` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL,
  `datetime` TIMESTAMP NULL,
  `text` TEXT NULL,
  `tag` VARCHAR(600) NULL,
  `idUsers` INT NULL,
  PRIMARY KEY (`idArticle`),
  INDEX `fk_Article_Users1_idx` (`idUsers` ASC),
  CONSTRAINT `fk_Article_Users1`
    FOREIGN KEY (`idUsers`)
    REFERENCES `Name1`.`Users` (`idUsers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Name1`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Name1`.`comment` ;

CREATE TABLE IF NOT EXISTS `Name1`.`comment` (
  `idcomment` INT NOT NULL,
  `date` TIMESTAMP NULL,
  `description` VARCHAR(255) NULL,
  `idUsers` INT NULL,
  `idArticle` INT NULL,
  PRIMARY KEY (`idcomment`),
  INDEX `fk_comment_Users1_idx` (`idUsers` ASC),
  INDEX `fk_comment_Article1_idx` (`idArticle` ASC),
  CONSTRAINT `fk_comment_Users1`
    FOREIGN KEY (`idUsers`)
    REFERENCES `Name1`.`Users` (`idUsers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_Article1`
    FOREIGN KEY (`idArticle`)
    REFERENCES `Name1`.`Article` (`idArticle`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
