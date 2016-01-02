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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.especialidad: ~8 rows (aproximadamente)
DELETE FROM `especialidad`;
/*!40000 ALTER TABLE `especialidad` DISABLE KEYS */;
INSERT INTO `especialidad` (`especialidad_id`, `nombre`) VALUES
	(1, 'PEDIATRA'),
	(2, 'CLINICO'),
	(3, 'ODONTOLOGO'),
	(5, 'TRAUMATOLOGO'),
	(6, 'GINECOLOGO'),
	(7, 'PSICOLOGO'),
	(9, 'UROLOGO'),
	(12, 'ASDASDASAD AD ADA ');
/*!40000 ALTER TABLE `especialidad` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.obrasocial
CREATE TABLE IF NOT EXISTS `obrasocial` (
  `obrasocial_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`obrasocial_id`),
  KEY `obrasocialId` (`obrasocial_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.obrasocial: ~4 rows (aproximadamente)
DELETE FROM `obrasocial`;
/*!40000 ALTER TABLE `obrasocial` DISABLE KEYS */;
INSERT INTO `obrasocial` (`obrasocial_id`, `nombre`) VALUES
	(1, 'OSDE'),
	(2, 'SWISS MEDICAL'),
	(3, 'SUBSIDIO DE SALUD'),
	(4, 'PRENSA');
/*!40000 ALTER TABLE `obrasocial` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.paciente
CREATE TABLE IF NOT EXISTS `paciente` (
  `paciente_id` int(11) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(256) COLLATE utf8_bin DEFAULT '0',
  `nombre` varchar(256) COLLATE utf8_bin DEFAULT '0',
  `fecha_nacimiento` date DEFAULT '0000-00-00',
  `domicilio` varchar(512) COLLATE utf8_bin DEFAULT '0',
  `telefono` varchar(56) COLLATE utf8_bin DEFAULT '0',
  `mail` varchar(128) COLLATE utf8_bin DEFAULT '0',
  `liberado` tinyint(4) DEFAULT '0',
  `adherente_id` int(11) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL,
  `provincia` varchar(156) COLLATE utf8_bin DEFAULT NULL,
  `titular` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`paciente_id`),
  KEY `pacienteId` (`paciente_id`),
  KEY `FK_paciente_paciente` (`adherente_id`),
  CONSTRAINT `FK_paciente_paciente` FOREIGN KEY (`adherente_id`) REFERENCES `paciente` (`paciente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.paciente: ~9 rows (aproximadamente)
DELETE FROM `paciente`;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` (`paciente_id`, `apellido`, `nombre`, `fecha_nacimiento`, `domicilio`, `telefono`, `mail`, `liberado`, `adherente_id`, `dni`, `provincia`, `titular`) VALUES
	(8, 'VALDEZ', 'GUSTAVO FEDERICO', '1920-01-01', 'Pje. Hola ', '0381155897889', 'gvaldez@gmail.com', 0, NULL, 25666555, 'Córdoba', 1),
	(14, 'Valdez', 'Gustavito Hijo', '2015-01-01', 'Pje. Hola ', '0381155897889', 'gvaldez@gmail.com', 0, 8, 5555555, 'Córdoba', 0),
	(15, 'Valdez', 'Gustavito Hijo 2', '2015-01-01', 'Pje. Hola ', '0381155897889', 'gvaldez@gmail.com', 0, 8, 2322434, 'Córdoba', 0),
	(16, 'Valdez', 'Gustavito Hijo 3', '2015-01-01', 'Pje. Hola ', '0381155897889', 'gvaldez@gmail.com', 0, 8, 23424234, 'Córdoba', 0),
	(17, 'Valdez', 'Gustavito Hijo 4', '2015-01-01', 'Pje. Hola ', '0381155897889', 'gvaldez@gmail.com', 0, 8, 2312131, 'Córdoba', 0),
	(25, 'ARJONA', 'MIGUEL', '1920-01-01', 'SDASDASDASDA', '564646464', 'asdasdad@asdad.com', 0, NULL, 29878065, 'Tucumán', 1),
	(31, 'ARJONA', 'MIGUELITO JR', '2015-01-01', 'SDASDASDASDA', '564646464', 'asdasdad@asdad.com', 0, 25, 23123134, 'Tucumán', 0),
	(32, 'test', 'asdadha', '2015-01-01', 'asdada', '6546546', 'asdasdad@asdad.com', 0, NULL, 9989898, 'Buenos Aires', 0),
	(33, 'qwe', 'wqwewe', '2016-01-02', 'weqweqew', '223333333', '', 0, NULL, NULL, 'NONE', 1);
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.paciente_obrasocial
CREATE TABLE IF NOT EXISTS `paciente_obrasocial` (
  `paciente_obrasocial_id` int(11) NOT NULL AUTO_INCREMENT,
  `nro_credencial` varchar(256) COLLATE utf8_bin DEFAULT '0',
  `provisorio` tinyint(4) DEFAULT '0',
  `obrasocial_id` int(11) DEFAULT '0',
  `paciente_id` int(11) DEFAULT '0',
  `fecha` date DEFAULT '0000-00-00',
  PRIMARY KEY (`paciente_obrasocial_id`),
  KEY `paciente_obrasocial_id` (`paciente_obrasocial_id`),
  KEY `FK_paciente_obrasocial_obrasocial` (`obrasocial_id`),
  KEY `FK_paciente_obrasocial_paciente` (`paciente_id`),
  CONSTRAINT `FK_paciente_obrasocial_obrasocial` FOREIGN KEY (`obrasocial_id`) REFERENCES `obrasocial` (`obrasocial_id`),
  CONSTRAINT `FK_paciente_obrasocial_paciente` FOREIGN KEY (`paciente_id`) REFERENCES `paciente` (`paciente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.paciente_obrasocial: ~8 rows (aproximadamente)
DELETE FROM `paciente_obrasocial`;
/*!40000 ALTER TABLE `paciente_obrasocial` DISABLE KEYS */;
INSERT INTO `paciente_obrasocial` (`paciente_obrasocial_id`, `nro_credencial`, `provisorio`, `obrasocial_id`, `paciente_id`, `fecha`) VALUES
	(22, '888888888888', 1, 1, 14, '2016-01-01'),
	(23, '32424242', 1, 1, 15, '2016-01-01'),
	(24, '6546546456464', 1, 1, 16, '2016-01-01'),
	(25, '6465465464', 1, 1, 17, '2016-01-01'),
	(35, '65465465465465464', 1, 2, 25, '2016-01-02'),
	(41, '6666666666666666', 1, 2, 31, '2016-01-02'),
	(42, '654654564654', 1, 3, 32, '2016-01-02'),
	(46, '6546465464', 1, 1, 8, '2016-01-02');
/*!40000 ALTER TABLE `paciente_obrasocial` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.profesional: ~5 rows (aproximadamente)
DELETE FROM `profesional`;
/*!40000 ALTER TABLE `profesional` DISABLE KEYS */;
INSERT INTO `profesional` (`profesional_id`, `apellido`, `nombre`, `telefono`, `matricula`, `registro_nacional`, `titulo_profesional`, `habilitacion_siprosa`, `fecha_vencimiento_habilitacion`) VALUES
	(1, 'PEREZ', 'ROBERTO CARLOS', '0381155889999', '2451788', '10001000', 'MEDICO CLINICO', 1, '2016-12-01'),
	(2, 'JUAN', 'DE LA CALLE', '2131', '21231', '21321313', 'DASODKASPDOA ', 1, '2015-12-17'),
	(3, 'JULIO8', 'BOCA', '213213', '23324|', '8787', '876', 1, '2015-12-18'),
	(4, 'PEDRO ', 'EL ESCAMOSO', '87987', '98797', '87987', 'JHJKH', 1, '2015-12-25'),
	(5, 'CAROLINA', 'PEDRABUENA', '09890', '098', '0980', 'DADSAD', 1, '2015-12-25');
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.profesional_especialidad: ~6 rows (aproximadamente)
DELETE FROM `profesional_especialidad`;
/*!40000 ALTER TABLE `profesional_especialidad` DISABLE KEYS */;
INSERT INTO `profesional_especialidad` (`id`, `profesional_id`, `especialidad_id`) VALUES
	(25, 1, 2),
	(26, 1, 6),
	(27, 2, 6),
	(28, 3, 3),
	(29, 4, 9),
	(30, 5, 1);
/*!40000 ALTER TABLE `profesional_especialidad` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
