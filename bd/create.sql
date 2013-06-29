SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `brtax` ;
CREATE SCHEMA IF NOT EXISTS `brtax` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `brtax` ;

-- -----------------------------------------------------
-- Table `brtax`.`PRODUCT`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `brtax`.`PRODUCT` (
  `id_product` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(500) NULL ,
  `product_name` VARCHAR(100) NULL ,
  `brand` VARCHAR(50) NULL ,
  `gtin` INT NOT NULL ,
  PRIMARY KEY (`id_product`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brtax`.`TAX_IPI`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `brtax`.`TAX_IPI` (
  `id_tax_ipi` INT NOT NULL ,
  `aliquot` DECIMAL(10,2) NULL ,
  `decree` VARCHAR(255) NULL ,
  PRIMARY KEY (`id_tax_ipi`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brtax`.`NCM_GROUP`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `brtax`.`NCM_GROUP` (
  `ncm_group_code` INT NOT NULL ,
  `description` VARCHAR(100) NULL ,
  PRIMARY KEY (`ncm_group_code`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brtax`.`NCM_SUB`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `brtax`.`NCM_SUB` (
  `ncm_sub_code` INT NOT NULL ,
  `description` VARCHAR(100) NULL ,
  `ncm_group_code` INT NOT NULL ,
  PRIMARY KEY (`ncm_sub_code`) ,
  INDEX `fk_NCM_SUB_NCM_GROUP_idx` (`ncm_group_code` ASC) ,
  CONSTRAINT `fk_NCM_SUB_NCM_GROUP`
    FOREIGN KEY (`ncm_group_code` )
    REFERENCES `brtax`.`NCM_GROUP` (`ncm_group_code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brtax`.`TAX_COFINS`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `brtax`.`TAX_COFINS` (
  `id_tax_cofins` INT NOT NULL ,
  `aliquot` DECIMAL(10,2) NULL ,
  `decree` VARCHAR(255) NULL ,
  PRIMARY KEY (`id_tax_cofins`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brtax`.`TAX_PIS`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `brtax`.`TAX_PIS` (
  `id_tax_pis` INT NOT NULL ,
  `aliquot` DECIMAL(10,2) NULL ,
  `decree` VARCHAR(255) NULL ,
  PRIMARY KEY (`id_tax_pis`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brtax`.`NCM`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `brtax`.`NCM` (
  `ncm_code` INT NOT NULL ,
  `description` VARCHAR(100) NULL ,
  `ncm_sub_code` INT NOT NULL ,
  `id_tax_ipi` INT NOT NULL ,
  `id_tax_cofins` INT NOT NULL ,
  `id_tax_pis` INT NOT NULL ,
  PRIMARY KEY (`ncm_code`) ,
  INDEX `fk_NCM_NCM_SUB1_idx` (`ncm_sub_code` ASC) ,
  INDEX `fk_NCM_TAX_IPI1_idx` (`id_tax_ipi` ASC) ,
  INDEX `fk_NCM_TAX_COFINS1_idx` (`id_tax_cofins` ASC) ,
  INDEX `fk_NCM_TAX_PIS1_idx` (`id_tax_pis` ASC) ,
  CONSTRAINT `fk_NCM_NCM_SUB1`
    FOREIGN KEY (`ncm_sub_code` )
    REFERENCES `brtax`.`NCM_SUB` (`ncm_sub_code` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NCM_TAX_IPI1`
    FOREIGN KEY (`id_tax_ipi` )
    REFERENCES `brtax`.`TAX_IPI` (`id_tax_ipi` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NCM_TAX_COFINS1`
    FOREIGN KEY (`id_tax_cofins` )
    REFERENCES `brtax`.`TAX_COFINS` (`id_tax_cofins` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NCM_TAX_PIS1`
    FOREIGN KEY (`id_tax_pis` )
    REFERENCES `brtax`.`TAX_PIS` (`id_tax_pis` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `brtax` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
