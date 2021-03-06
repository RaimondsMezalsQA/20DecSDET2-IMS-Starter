	DROP TABLE `orderline`;
	DROP TABLE `orders`;
	DROP TABLE `customers`;
	DROP TABLE `items`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
    );
    
    CREATE TABLE IF NOT EXISTS `items` (
    `iid` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) DEFAULT NULL,
    `price` DEC(5,2),
    PRIMARY KEY (`iid`)  
	);
	
	CREATE TABLE IF NOT EXISTS `orders` (
    `oid` INT(11) NOT NULL AUTO_INCREMENT,
    `f_cid` INT(11),
    PRIMARY KEY (`oid`),
    FOREIGN KEY (`f_cid`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
	);
	
	CREATE TABLE IF NOT EXISTS `orderline` (
    `f_oid` INT(11),
    `f_iid` INT(11),
    FOREIGN KEY (`f_oid`) REFERENCES `orders` (`oid`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`f_iid`) REFERENCES `items` (`iid`) ON DELETE CASCADE ON UPDATE CASCADE
	);