-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema clinicdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema clinicdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `clinicdb` DEFAULT CHARACTER SET utf8 ;
USE `clinicdb` ;

-- -----------------------------------------------------
-- Table `clinicdb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicdb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `clinicdb`.`details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicdb`.`details` (
  `user_id` INT NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `surname` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `phoneNumber` VARCHAR(45) NULL DEFAULT NULL,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  INDEX `fk_details_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_details_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `clinicdb`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `clinicdb`.`pets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicdb`.`pets` (
  `pet_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `type_of_pet` VARCHAR(45) NOT NULL,
  `owner` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`pet_id`, `user_id`),
  INDEX `fk_pets_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_pets_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `clinicdb`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `clinicdb`.`medical_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinicdb`.`medical_card` (
  `id_record` INT NOT NULL AUTO_INCREMENT,
  `date_appointment` DATE NOT NULL,
  `doctor` VARCHAR(45) NOT NULL,
  `patient` VARCHAR(45) NOT NULL,
  `accompanying_the_patient` VARCHAR(45) NOT NULL,
  `visit_comment_` VARCHAR(45) NOT NULL,
  `pets_pet_id` INT NOT NULL,
  `pets_user_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id_record`, `pets_pet_id`, `pets_user_id`, `user_id`),
  INDEX `fk_medical_card_pets1_idx` (`pets_pet_id` ASC, `pets_user_id` ASC) VISIBLE,
  INDEX `fk_medical_card_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_medical_card_pets1`
    FOREIGN KEY (`pets_pet_id` , `pets_user_id`)
    REFERENCES `clinicdb`.`pets` (`pet_id` , `user_id`),
  CONSTRAINT `fk_medical_card_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `clinicdb`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
