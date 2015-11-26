# Nuova Aplicacion Web

Instalar

+ Apache Tomcat 7
+ Java JDK 7
+ Apache Maven
+ Git - Git Bash
+ Eclipse (Luna)
+ Mysql 5.6.X (WAMP)
+ Heidi SQL


Descargar el codigo
> git clone https://github.com/arjonamiguel/nuova_master.git

> git pull

MySQL
+ Crear Base de Datos "test"
+ Crear tabla employee

 CREATE TABLE `employee` (
	`ID` INT(11) NOT NULL AUTO_INCREMENT,
	`FIRSTNAME` VARCHAR(50) NULL DEFAULT '0',
	`LASTNAME` VARCHAR(50) NULL DEFAULT '0',
	`TELEPHONE` VARCHAR(50) NULL DEFAULT '0',
	`EMAIL` VARCHAR(50) NULL DEFAULT '0',
	INDEX `Índice 1` (`ID`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
AUTO_INCREMENT=11
;
