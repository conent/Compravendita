SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `DBProgetto` DEFAULT CHARACTER SET latin1 ;
USE `DBProgetto` ;

-- -----------------------------------------------------
-- Table `DBProgetto`.`users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DBProgetto`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `role` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBProgetto`.`products`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DBProgetto`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `quantity` INT NULL ,
  `category` VARCHAR(45) NULL ,
  `UM` INT NULL ,
  `price` DOUBLE NULL ,
  `date_timestamp` DATETIME NULL ,
  `url_image` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBProgetto`.`sell`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DBProgetto`.`sell` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `timestamp` DATETIME NULL ,
  `id_product` INT NOT NULL ,
  `id_user` INT NOT NULL ,
  `quantity` INT NULL ,
  `total_price` DOUBLE NULL ,
  `url_recepit` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_sell_products_idx` (`id_product` ASC) ,
  INDEX `fk_sell_users1_idx` (`id_user` ASC) ,
  CONSTRAINT `fk_sell_products`
    FOREIGN KEY (`id_product` )
    REFERENCES `DBProgetto`.`products` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sell_users1`
    FOREIGN KEY (`id_user` )
    REFERENCES `DBProgetto`.`users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `DBProgetto`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `DBProgetto`;
INSERT INTO `DBProgetto`.`users` (`id`, `username`, `password`, `role`) VALUES (1, 'mario', 'mario', 'seller');
INSERT INTO `DBProgetto`.`users` (`id`, `username`, `password`, `role`) VALUES (2, 'marco', 'marco', 'seller');
INSERT INTO `DBProgetto`.`users` (`id`, `username`, `password`, `role`) VALUES (3, 'matteo', 'matteo', 'seller');
INSERT INTO `DBProgetto`.`users` (`id`, `username`, `password`, `role`) VALUES (4, 'monica', 'monica', 'sellet');
INSERT INTO `DBProgetto`.`users` (`id`, `username`, `password`, `role`) VALUES (5, 'francesco', 'francesco', 'buyer');
INSERT INTO `DBProgetto`.`users` (`id`, `username`, `password`, `role`) VALUES (6, 'federico', 'federico', 'buyer');
INSERT INTO `DBProgetto`.`users` (`id`, `username`, `password`, `role`) VALUES (7, 'filippo', 'filippo', 'buyer');
INSERT INTO `DBProgetto`.`users` (`id`, `username`, `password`, `role`) VALUES (8, 'fulvio', 'fulvio', 'buyer');

COMMIT;

-- -----------------------------------------------------
-- Data for table `DBProgetto`.`products`
-- -----------------------------------------------------
START TRANSACTION;
USE `DBProgetto`;
INSERT INTO `DBProgetto`.`products` (`id`, `name`, `quantity`, `category`, `UM`, `price`, `date_timestamp`, `url_image`) VALUES (1, 'mela fuji', 20, 'mele', 1, 1.50, NULL, NULL);
INSERT INTO `DBProgetto`.`products` (`id`, `name`, `quantity`, `category`, `UM`, `price`, `date_timestamp`, `url_image`) VALUES (2, 'mela golden', 20, 'mele', 1, 1.30, NULL, NULL);
INSERT INTO `DBProgetto`.`products` (`id`, `name`, `quantity`, `category`, `UM`, `price`, `date_timestamp`, `url_image`) VALUES (3, 'mela red delicious', 40, 'mele', 1, 1.00, NULL, NULL);
INSERT INTO `DBProgetto`.`products` (`id`, `name`, `quantity`, `category`, `UM`, `price`, `date_timestamp`, `url_image`) VALUES (4, 'mela gala', 30, 'mele', 1, 1.10, NULL, NULL);
INSERT INTO `DBProgetto`.`products` (`id`, `name`, `quantity`, `category`, `UM`, `price`, `date_timestamp`, `url_image`) VALUES (5, 'mela morgenduft', 20, 'mele', 1, 1.50, NULL, NULL);
INSERT INTO `DBProgetto`.`products` (`id`, `name`, `quantity`, `category`, `UM`, `price`, `date_timestamp`, `url_image`) VALUES (6, 'mortadella', 10, 'affettati', 1, 10.05, NULL, NULL);
INSERT INTO `DBProgetto`.`products` (`id`, `name`, `quantity`, `category`, `UM`, `price`, `date_timestamp`, `url_image`) VALUES (7, 'speck', 10, 'affettati', 1, 15.90, NULL, NULL);
INSERT INTO `DBProgetto`.`products` (`id`, `name`, `quantity`, `category`, `UM`, `price`, `date_timestamp`, `url_image`) VALUES (8, 'pancetta', 10, 'affettati', 1, 15.10, NULL, NULL);
INSERT INTO `DBProgetto`.`products` (`id`, `name`, `quantity`, `category`, `UM`, `price`, `date_timestamp`, `url_image`) VALUES (9, 'crudo', 10, 'affettati', 1, 19.60, NULL, NULL);
INSERT INTO `DBProgetto`.`products` (`id`, `name`, `quantity`, `category`, `UM`, `price`, `date_timestamp`, `url_image`) VALUES (10, 'bresaola', 10, 'affettati', 1, 39.90, NULL, NULL);

COMMIT;
