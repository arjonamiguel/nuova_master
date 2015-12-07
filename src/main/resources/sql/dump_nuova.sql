-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.17 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.2.0.4981
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para nuova
CREATE DATABASE IF NOT EXISTS `nuova` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `nuova`;


-- Volcando estructura para tabla nuova.especialidad
CREATE TABLE IF NOT EXISTS `especialidad` (
  `especialidad_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(256) COLLATE utf8_bin DEFAULT '0',
  PRIMARY KEY (`especialidad_id`),
  KEY `id` (`especialidad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.especialidad: ~4 rows (aproximadamente)
DELETE FROM `especialidad`;
/*!40000 ALTER TABLE `especialidad` DISABLE KEYS */;
INSERT INTO `especialidad` (`especialidad_id`, `nombre`) VALUES
	(1, 'PEDIATRA'),
	(2, 'CLINICO'),
	(3, 'ODONTOLOGO'),
	(4, 'TRAUMATOLOGO');
/*!40000 ALTER TABLE `especialidad` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.profesional
CREATE TABLE IF NOT EXISTS `profesional` (
  `profesional_id` int(11) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(256) COLLATE utf8_bin DEFAULT '0',
  `nombre` varchar(256) COLLATE utf8_bin DEFAULT '0',
  `telefono` varchar(56) COLLATE utf8_bin DEFAULT '0',
  `matricula` varchar(256) COLLATE utf8_bin DEFAULT '0',
  `registro_nacional` varchar(256) COLLATE utf8_bin DEFAULT '0',
  `titulo_profesional` varchar(256) COLLATE utf8_bin DEFAULT '0',
  `habilitacion_siprosa` tinyint(4) DEFAULT '0',
  `fecha_vencimiento_habilitacion` date DEFAULT '0000-00-00',
  PRIMARY KEY (`profesional_id`),
  KEY `id` (`profesional_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.profesional: ~3 rows (aproximadamente)
DELETE FROM `profesional`;
/*!40000 ALTER TABLE `profesional` DISABLE KEYS */;
INSERT INTO `profesional` (`profesional_id`, `apellido`, `nombre`, `telefono`, `matricula`, `registro_nacional`, `titulo_profesional`, `habilitacion_siprosa`, `fecha_vencimiento_habilitacion`) VALUES
	(1, 'PEREZ', 'ROBERTO CARLOS', '0381155889999', '2451788', '10001000', 'MEDICO CLINICO', 1, '2016-12-01'),
	(2, 'GOMEZ', 'MIGUEL ALEJANDRO', '0381155869888', '2555554', '10010001', 'MEDICO CLINICO', 1, '2016-12-01'),
	(3, 'LAZARTE', 'ROMINA VERONICA', '03811556658', '2455878', '10012220', 'MEDICO CLINICO', 1, '2016-12-01');
/*!40000 ALTER TABLE `profesional` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.profesional_especialidad
CREATE TABLE IF NOT EXISTS `profesional_especialidad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profesional_id` int(11) DEFAULT '0',
  `especialidad_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_profesionales_especialidades_profesionales` (`profesional_id`),
  KEY `FK_profesionales_especialidades_especialidades` (`especialidad_id`),
  CONSTRAINT `FK_profesionales_especialidades_especialidades` FOREIGN KEY (`especialidad_id`) REFERENCES `especialidad` (`especialidad_id`),
  CONSTRAINT `FK_profesionales_especialidades_profesionales` FOREIGN KEY (`profesional_id`) REFERENCES `profesional` (`profesional_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.profesional_especialidad: ~3 rows (aproximadamente)
DELETE FROM `profesional_especialidad`;
/*!40000 ALTER TABLE `profesional_especialidad` DISABLE KEYS */;
INSERT INTO `profesional_especialidad` (`id`, `profesional_id`, `especialidad_id`) VALUES
	(1, 1, 1),
	(2, 2, 1),
	(3, 3, 2);
/*!40000 ALTER TABLE `profesional_especialidad` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
