-- MySQL Script generated by MySQL Workbench
-- seg 15 mar 2021 19:13:08 -03
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema vocealuga
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema vocealuga
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vocealuga` ;
USE `vocealuga` ;

-- -----------------------------------------------------
-- Table `vocealuga`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vocealuga`.`clientes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `cnh` CHAR(11) NULL,
  `tem_apolice` TINYINT(1) NULL,
  `cnh_is_valida` TINYINT(1) NULL,
  `is_lista_negra` TINYINT(1) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vocealuga`.`agencias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vocealuga`.`agencias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cnpj` CHAR(14) NOT NULL,
  `cep` CHAR(8) NOT NULL,
  `logradouro` VARCHAR(45) NOT NULL,
  `numero` VARCHAR(10) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `uf` CHAR(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cnpj_UNIQUE` (`cnpj` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vocealuga`.`funcionarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vocealuga`.`funcionarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cargo` VARCHAR(45) NOT NULL,
  `matricula` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `filiais_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_funcionarios_filiais1_idx` (`filiais_id` ASC),
  CONSTRAINT `fk_funcionarios_filiais1`
    FOREIGN KEY (`filiais_id`)
    REFERENCES `vocealuga`.`agencias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vocealuga`.`grupos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vocealuga`.`grupos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `deleted_at` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vocealuga`.`carros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vocealuga`.`carros` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `modelo` VARCHAR(45) NOT NULL,
  `marca` VARCHAR(45) NOT NULL,
  `ano` CHAR(4) NOT NULL,
  `valor` FLOAT NOT NULL,
  `grupos_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_carros_grupos1_idx` (`grupos_id` ASC),
  CONSTRAINT `fk_carros_grupos1`
    FOREIGN KEY (`grupos_id`)
    REFERENCES `vocealuga`.`grupos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vocealuga`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vocealuga`.`status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vocealuga`.`filiais_carros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vocealuga`.`filiais_carros` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantidade` INT NOT NULL,
  `is_solicitacao` TINYINT(1) NULL,
  `quilometragem` FLOAT NOT NULL,
  `valor_venda` FLOAT NULL,
  `carros_id` INT NOT NULL,
  `filiais_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  INDEX `fk_filiais_has_carros_carros1_idx` (`carros_id` ASC),
  INDEX `fk_filiais_has_carros_filiais1_idx` (`filiais_id` ASC),
  INDEX `fk_filiais_carros_status1_idx` (`status_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_filiais_has_carros_filiais1`
    FOREIGN KEY (`filiais_id`)
    REFERENCES `vocealuga`.`agencias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_filiais_has_carros_carros1`
    FOREIGN KEY (`carros_id`)
    REFERENCES `vocealuga`.`carros` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_filiais_carros_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `vocealuga`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vocealuga`.`locacoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vocealuga`.`locacoes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `devolver_em` DATE NOT NULL,
  `locado_em` DATE NOT NULL,
  `devolvido_em` DATE NULL,
  `valor` DOUBLE NOT NULL,
  `observacoes` TEXT(255) NULL,
  `filiais_carros_id` INT NOT NULL,
  `funcionarios_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_locacoes_filiais_carros1_idx` (`filiais_carros_id` ASC),
  INDEX `fk_locacoes_funcionarios1_idx` (`funcionarios_id` ASC),
  CONSTRAINT `fk_locacoes_filiais_carros1`
    FOREIGN KEY (`filiais_carros_id`)
    REFERENCES `vocealuga`.`filiais_carros` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_locacoes_funcionarios1`
    FOREIGN KEY (`funcionarios_id`)
    REFERENCES `vocealuga`.`funcionarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vocealuga`.`manutencoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vocealuga`.`manutencoes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comecou_em` DATE NOT NULL,
  `terminou_em` DATE NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `filiais_carros_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_manutencoes_filiais_carros1_idx` (`filiais_carros_id` ASC),
  CONSTRAINT `fk_manutencoes_filiais_carros1`
    FOREIGN KEY (`filiais_carros_id`)
    REFERENCES `vocealuga`.`filiais_carros` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vocealuga`.`clientes_locacoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vocealuga`.`clientes_locacoes` (
  `clientes_id` INT NOT NULL,
  `locacoes_id` INT NOT NULL,
  `is_motorista` TINYINT(1) NOT NULL,
  PRIMARY KEY (`clientes_id`, `locacoes_id`),
  INDEX `fk_clientes_has_locacoes_locacoes1_idx` (`locacoes_id` ASC),
  INDEX `fk_clientes_has_locacoes_clientes1_idx` (`clientes_id` ASC),
  CONSTRAINT `fk_clientes_has_locacoes_clientes1`
    FOREIGN KEY (`clientes_id`)
    REFERENCES `vocealuga`.`clientes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clientes_has_locacoes_locacoes1`
    FOREIGN KEY (`locacoes_id`)
    REFERENCES `vocealuga`.`locacoes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
