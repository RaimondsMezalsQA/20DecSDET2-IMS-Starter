drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
    );
    
    CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `iid` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) DEFAULT NULL,
    `price` DEC(5,2) DEFAULT (0),
    PRIMARY KEY (`iid`)  
	);
	
	CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `oid` INT(11) NOT NULL AUTO_INCREMENT,
    `f_cid` INT(11) DEFAULT (0),
    PRIMARY KEY (`oid`),
    FOREIGN KEY (`f_cid`) REFERENCES customers (id)
	);
	
	CREATE TABLE IF NOT EXISTS `ims`.`orderline` (
    `f_oid` INT(11),
    `f_iid` INT(11),
    FOREIGN KEY (`f_oid`) REFERENCES orders (oid),
    FOREIGN KEY (`f_iid`) REFERENCES items (iid)
	);