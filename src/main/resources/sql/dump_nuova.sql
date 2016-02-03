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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.especialidad: ~16 rows (aproximadamente)
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
	(14, 'Tes especialidad 2'),
	(15, 'Test Especialidad 4'),
	(16, 'TEST ESPECIALIDAD 4'),
	(17, 'TEST ESPECIALIDAD 5'),
	(18, 'TEST ESPECIALIDAD 6'),
	(19, 'TEST ESPECIALIDAD 7'),
	(20, 'TEST ESPECIALIDAD 8'),
	(21, 'TEST ESPECIALIDAD 9'),
	(22, 'test 55');
/*!40000 ALTER TABLE `especialidad` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.obrasocial
CREATE TABLE IF NOT EXISTS `obrasocial` (
  `obrasocial_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`obrasocial_id`),
  KEY `obrasocialId` (`obrasocial_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.obrasocial: ~15 rows (aproximadamente)
DELETE FROM `obrasocial`;
/*!40000 ALTER TABLE `obrasocial` DISABLE KEYS */;
INSERT INTO `obrasocial` (`obrasocial_id`, `nombre`) VALUES
	(1, 'OSDE'),
	(2, 'SWISS MEDICAL'),
	(3, 'SUBSIDIO DE SALUD'),
	(4, 'PRENSA'),
	(7, 'Test OS 1'),
	(8, 'Test OS 2'),
	(9, 'Test OS 3'),
	(10, 'Test OS 4'),
	(11, 'Test OS 5'),
	(12, 'Test OS 6'),
	(13, 'Test OS 7'),
	(14, 'Test OS 8'),
	(15, 'Test OS 90'),
	(16, 'obra social test 55');
/*!40000 ALTER TABLE `obrasocial` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.observaciones
CREATE TABLE IF NOT EXISTS `observaciones` (
  `observacion_id` int(11) NOT NULL AUTO_INCREMENT,
  `observacion` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `orden_id` int(11) DEFAULT NULL,
  `user_name` varchar(160) COLLATE utf8_bin DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`observacion_id`),
  KEY `observacion_id` (`observacion_id`),
  KEY `FK_observaciones_orden` (`orden_id`),
  CONSTRAINT `FK_observaciones_orden` FOREIGN KEY (`orden_id`) REFERENCES `orden` (`orden_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.observaciones: ~14 rows (aproximadamente)
DELETE FROM `observaciones`;
/*!40000 ALTER TABLE `observaciones` DISABLE KEYS */;
INSERT INTO `observaciones` (`observacion_id`, `observacion`, `orden_id`, `user_name`, `fecha`) VALUES
	(25, 'inicio', 17, 'administrador@nuova.com', '2016-01-13 15:02:25'),
	(26, 'agrego practicas y cambio de estado en observacion', 17, 'administrador@nuova.com', '2016-01-13 15:03:42'),
	(27, 'agrego otra practica con el mismo estado', 17, 'administrador@nuova.com', '2016-01-13 15:06:37'),
	(28, '66666', 17, 'administrador@nuova.com', '2016-01-14 14:37:09'),
	(29, 'Le fata presentar recibo de sueldo', 18, 'administrador@nuova.com', '2016-01-14 14:48:32'),
	(30, 'inicio de practicas', 19, 'administrador@nuova.com', '2016-01-15 18:23:56'),
	(31, 'se encuentra correcto bla bla bla', 19, 'administrador@nuova.com', '2016-01-15 18:25:43'),
	(32, 'se rechazo porq la fecha de la cobertura caduco', 19, 'administrador@nuova.com', '2016-01-15 18:27:44'),
	(33, 'asdas asdas das d', 20, 'administrador@nuova.com', '2016-01-20 11:48:51'),
	(34, 'asdsad asd asdasd asd asd asd asd ', 20, 'administrador@nuova.com', '2016-01-20 11:50:17'),
	(35, 'TEST 1', 22, 'administrador@nuova.com', '2016-01-26 15:10:34'),
	(36, 'ASDASDAS', 22, 'administrador@nuova.com', '2016-01-26 15:12:10'),
	(37, 'ASDASDASD AS DASD ASD ASD A', 22, 'administrador@nuova.com', '2016-01-26 15:12:28'),
	(38, 'asd dasd asd ', 23, 'virginia.gottardi@nuova.com', '2016-01-26 16:40:56'),
	(39, 'asdasdasd', 23, 'virginia.gottardi@nuova.com', '2016-01-26 16:41:14'),
	(40, 'SE GENERA LA ORDEN DEBE TRAER RECIBO DE SUELDO', 24, 'virginia.gottardi@nuova.com', '2016-01-26 19:27:28'),
	(41, 'iajdaiosjdoiasjd oasij doaisjdaoisjdaosidj aosidj asoijd aoisjd aoisj doais jdas doias diajso jasodaso djasj doas doia sdod', 24, 'virginia.gottardi@nuova.com', '2016-01-26 19:54:43'),
	(42, 'asd as dasd as', 25, 'administrador@nuova.com', '2016-01-27 13:19:00'),
	(43, 'asd asda sd asdasdasdsad asdasd asdas dasdas da sdas dasdasdasdasdas dasdada sdas daa da dada sd adas a dasdasdas dasa sd asd asdasd asd adasd asd adasda sad asd asdasas ad adasd a ad asdasdas dasdasda sdasd asdasd asdsadas das das dasdasd asd as as asadad', 26, 'administrador@nuova.com', '2016-01-29 16:51:49'),
	(44, 'testtt', 27, 'administrador@nuova.com', '2016-02-03 15:22:14');
/*!40000 ALTER TABLE `observaciones` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.orden
CREATE TABLE IF NOT EXISTS `orden` (
  `orden_id` int(11) NOT NULL AUTO_INCREMENT,
  `paciente_id` int(11) DEFAULT NULL,
  `fecha` date DEFAULT '0000-00-00',
  `orden_tipo` int(11) DEFAULT NULL,
  `req_orden_medico` tinyint(4) DEFAULT '0',
  `req_credecial` tinyint(4) DEFAULT '0',
  `req_recibo_sueldo` tinyint(4) DEFAULT '0',
  `req_monotributista` tinyint(4) DEFAULT '0',
  `estado` varchar(160) COLLATE utf8_bin DEFAULT '0',
  PRIMARY KEY (`orden_id`),
  KEY `orden_id` (`orden_id`),
  KEY `FK_orden_paciente` (`paciente_id`),
  KEY `FK_orden_orden_tipo` (`orden_tipo`),
  CONSTRAINT `FK_orden_orden_tipo` FOREIGN KEY (`orden_tipo`) REFERENCES `orden_tipo` (`orden_tipo_id`),
  CONSTRAINT `FK_orden_paciente` FOREIGN KEY (`paciente_id`) REFERENCES `paciente` (`paciente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.orden: ~11 rows (aproximadamente)
DELETE FROM `orden`;
/*!40000 ALTER TABLE `orden` DISABLE KEYS */;
INSERT INTO `orden` (`orden_id`, `paciente_id`, `fecha`, `orden_tipo`, `req_orden_medico`, `req_credecial`, `req_recibo_sueldo`, `req_monotributista`, `estado`) VALUES
	(17, 35, '2016-01-13', NULL, 1, 1, 1, 1, 'RECHAZADA'),
	(18, 35, '2016-01-14', NULL, 1, 1, 0, 0, 'EN_OBSERVACION'),
	(19, 35, '2016-01-15', NULL, 1, 1, 1, 1, 'RECHAZADA'),
	(20, 35, '2016-01-20', NULL, 1, 1, 0, 1, 'AUTORIZADA'),
	(21, 163, '2016-01-21', NULL, 1, 1, 1, 1, 'PENDIENTE'),
	(22, 169, '2016-01-26', NULL, 1, 1, 0, 1, 'AUTORIZADA'),
	(23, 172, '2016-01-26', NULL, 1, 1, 1, 0, 'PENDIENTE'),
	(24, 174, '2016-01-26', NULL, 1, 1, 0, 0, 'CERRADA'),
	(25, 175, '2016-01-27', NULL, 0, 0, 0, 0, 'INCOMPLETA'),
	(26, 180, '2016-01-29', NULL, 1, 1, 1, 0, 'AUTORIZADA'),
	(27, 180, '2016-02-03', NULL, 1, 1, 0, 1, 'PENDIENTE');
/*!40000 ALTER TABLE `orden` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.orden_practica
CREATE TABLE IF NOT EXISTS `orden_practica` (
  `ordden_practica_id` int(11) NOT NULL AUTO_INCREMENT,
  `orden_id` int(11) DEFAULT '0',
  `practica_id` int(11) DEFAULT '0',
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`ordden_practica_id`),
  KEY `ordden_practica_id` (`ordden_practica_id`),
  KEY `FK__orden` (`orden_id`),
  KEY `FK__practica` (`practica_id`),
  CONSTRAINT `FK__orden` FOREIGN KEY (`orden_id`) REFERENCES `orden` (`orden_id`),
  CONSTRAINT `FK__practica` FOREIGN KEY (`practica_id`) REFERENCES `practica` (`practica_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.orden_practica: ~16 rows (aproximadamente)
DELETE FROM `orden_practica`;
/*!40000 ALTER TABLE `orden_practica` DISABLE KEYS */;
INSERT INTO `orden_practica` (`ordden_practica_id`, `orden_id`, `practica_id`, `fecha`) VALUES
	(14, 17, 10, '2016-01-14'),
	(15, 17, 9, '2016-01-14'),
	(16, 17, 6, '2016-01-14'),
	(20, 20, 7, '2016-01-20'),
	(21, 20, 3, '2016-01-20'),
	(22, 20, 8, '2016-01-20'),
	(23, 22, 6, '2016-01-26'),
	(24, 22, 9, '2016-01-26'),
	(25, 22, 3, '2016-01-26'),
	(33, 24, 7, '2016-01-26'),
	(34, 24, 5, '2016-01-26'),
	(35, 24, 3, '2016-01-26'),
	(40, 26, 1, '2016-01-29'),
	(41, 26, 4, '2016-01-29'),
	(42, 26, 7, '2016-01-29'),
	(43, 26, 3, '2016-01-29');
/*!40000 ALTER TABLE `orden_practica` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.orden_tipo
CREATE TABLE IF NOT EXISTS `orden_tipo` (
  `orden_tipo_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `monto_1` double(10,2) DEFAULT '0.00',
  `monto_2` double(10,2) DEFAULT '0.00',
  `monto_3` double(10,2) DEFAULT '0.00',
  `codigo` int(11) DEFAULT NULL,
  PRIMARY KEY (`orden_tipo_id`),
  KEY `orden_tipo_id` (`orden_tipo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.orden_tipo: ~3 rows (aproximadamente)
DELETE FROM `orden_tipo`;
/*!40000 ALTER TABLE `orden_tipo` DISABLE KEYS */;
INSERT INTO `orden_tipo` (`orden_tipo_id`, `nombre`, `monto_1`, `monto_2`, `monto_3`, `codigo`) VALUES
	(1, 'CONSULTA', 45.22, 0.00, 0.00, 100),
	(2, 'ODONTOLÓGICA', 20.00, 15.00, 10.00, 101),
	(3, 'PRÁCTICA', 0.00, 0.00, 0.00, 102);
/*!40000 ALTER TABLE `orden_tipo` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.orden_workflow
CREATE TABLE IF NOT EXISTS `orden_workflow` (
  `orden_workflow_id` int(11) NOT NULL AUTO_INCREMENT,
  `orden_id` int(11) DEFAULT NULL,
  `user_name` varchar(164) COLLATE utf8_bin DEFAULT NULL,
  `estado` varchar(164) COLLATE utf8_bin DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`orden_workflow_id`),
  KEY `orden_workflow_id` (`orden_workflow_id`),
  KEY `FK_orden_workflow_orden` (`orden_id`),
  CONSTRAINT `FK_orden_workflow_orden` FOREIGN KEY (`orden_id`) REFERENCES `orden` (`orden_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.orden_workflow: ~20 rows (aproximadamente)
DELETE FROM `orden_workflow`;
/*!40000 ALTER TABLE `orden_workflow` DISABLE KEYS */;
INSERT INTO `orden_workflow` (`orden_workflow_id`, `orden_id`, `user_name`, `estado`, `fecha`) VALUES
	(32, 17, 'administrador@nuova.com', 'PENDIENTE', '2016-01-13 15:02:25'),
	(33, 17, 'administrador@nuova.com', 'EN_OBSERVACION', '2016-01-13 15:03:42'),
	(34, 17, 'administrador@nuova.com', 'RECHAZADA', '2016-01-14 14:37:10'),
	(35, 18, 'administrador@nuova.com', 'INCOMPLETA', '2016-01-14 14:48:32'),
	(36, 19, 'administrador@nuova.com', 'PENDIENTE', '2016-01-15 18:23:56'),
	(37, 19, 'administrador@nuova.com', 'AUTORIZADA', '2016-01-15 18:25:43'),
	(38, 19, 'administrador@nuova.com', 'RECHAZADA', '2016-01-15 18:27:44'),
	(39, 20, 'administrador@nuova.com', 'INCOMPLETA', '2016-01-20 11:48:51'),
	(40, 20, 'administrador@nuova.com', 'PENDIENTE', '2016-01-20 11:49:46'),
	(41, 20, 'administrador@nuova.com', 'AUTORIZADA', '2016-01-20 11:50:33'),
	(42, 18, 'administrador@nuova.com', 'RECHAZADA', '2016-01-21 13:38:51'),
	(43, 18, 'administrador@nuova.com', 'EN_OBSERVACION', '2016-01-21 13:39:04'),
	(44, 21, 'administrador@nuova.com', 'PENDIENTE', '2016-01-21 18:55:18'),
	(45, 22, 'administrador@nuova.com', 'INCOMPLETA', '2016-01-26 15:10:34'),
	(46, 22, 'administrador@nuova.com', 'AUTORIZADA', '2016-01-26 15:14:04'),
	(47, 23, 'virginia.gottardi@nuova.com', 'PENDIENTE', '2016-01-26 16:40:56'),
	(48, 24, 'virginia.gottardi@nuova.com', 'INCOMPLETA', '2016-01-26 19:27:28'),
	(49, 24, 'virginia.gottardi@nuova.com', 'PENDIENTE', '2016-01-26 19:35:27'),
	(50, 24, 'virginia.gottardi@nuova.com', 'EN_OBSERVACION', '2016-01-26 19:53:15'),
	(51, 24, 'virginia.gottardi@nuova.com', 'AUTORIZADA', '2016-01-26 19:55:09'),
	(52, 24, 'virginia.gottardi@nuova.com', 'CERRADA', '2016-01-26 19:55:32'),
	(53, 25, 'administrador@nuova.com', 'INCOMPLETA', '2016-01-27 13:19:00'),
	(54, 26, 'administrador@nuova.com', 'PENDIENTE', '2016-01-29 16:49:48'),
	(55, 26, 'administrador@nuova.com', 'AUTORIZADA', '2016-01-29 16:52:05'),
	(56, 27, 'administrador@nuova.com', 'PENDIENTE', '2016-02-03 15:22:14');
/*!40000 ALTER TABLE `orden_workflow` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.paciente
CREATE TABLE IF NOT EXISTS `paciente` (
  `paciente_id` int(11) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(256) COLLATE utf8_bin DEFAULT '0',
  `nombre` varchar(256) COLLATE utf8_bin DEFAULT '0',
  `fecha_nacimiento` date DEFAULT '0000-00-00',
  `domicilio` varchar(512) COLLATE utf8_bin DEFAULT '0',
  `telefono` varchar(56) COLLATE utf8_bin DEFAULT '0',
  `mail` varchar(128) COLLATE utf8_bin DEFAULT '0',
  `coseguro` tinyint(4) DEFAULT '0',
  `adherente_id` int(11) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL,
  `provincia` varchar(156) COLLATE utf8_bin DEFAULT NULL,
  `parentesco` tinyint(4) DEFAULT NULL,
  `zona_afiliacion` varchar(156) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`paciente_id`),
  KEY `pacienteId` (`paciente_id`),
  KEY `FK_paciente_paciente` (`adherente_id`),
  CONSTRAINT `FK_paciente_paciente` FOREIGN KEY (`adherente_id`) REFERENCES `paciente` (`paciente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.paciente: ~142 rows (aproximadamente)
DELETE FROM `paciente`;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` (`paciente_id`, `apellido`, `nombre`, `fecha_nacimiento`, `domicilio`, `telefono`, `mail`, `coseguro`, `adherente_id`, `dni`, `provincia`, `parentesco`, `zona_afiliacion`) VALUES
	(35, 'ARJONA', 'MIGUEL ANGEL', '2015-01-01', 'Descripcion Domicilio', '4930332', 'arjonamiguel@gmail.com', 0, NULL, 29878065, 'Tucumán', 1, NULL),
	(37, 'asdasd', 'asda', '2015-01-01', 'Descripcion Domicilio', '4930332', 'arjonamiguel@gmail.com', 0, 35, 654654, 'Tucumán', 0, NULL),
	(38, 'asdasd', 'asda', '2015-01-01', 'Descripcion Domicilio', '4930332', 'arjonamiguel@gmail.com', 0, 35, 654654, 'Tucumán', 0, NULL),
	(39, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(40, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(41, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(42, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(43, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(44, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(45, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(46, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(47, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(48, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(49, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(50, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(51, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(52, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(53, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(54, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(55, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(56, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(57, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(58, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(59, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(60, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(61, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(62, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(63, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(64, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(65, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(66, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(67, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(68, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(69, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(70, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(71, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(72, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(73, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(74, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(75, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(76, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(77, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(78, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(79, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(80, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(81, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(82, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(83, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(84, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(85, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(86, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(87, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(88, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(89, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(90, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(91, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(92, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(93, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(94, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(95, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(96, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(97, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(98, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(99, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(100, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(101, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(102, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(103, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(104, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(105, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(106, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(107, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(108, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(109, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(110, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(111, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(112, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(113, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(114, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(115, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(116, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(117, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(118, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(119, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(120, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(121, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(122, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(123, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(124, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(125, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(126, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(127, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(128, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(129, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(130, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(131, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(132, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(133, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(134, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(135, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(136, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(137, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(138, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(139, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(140, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(141, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(142, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(143, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(144, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(145, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(146, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(147, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(148, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(149, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(150, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(151, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(152, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(153, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(154, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(155, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(156, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(157, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(158, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(159, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(160, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(161, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(162, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(163, 'as', 'as', '2016-01-14', 'asdasda', '5555', 'sdasd', 0, NULL, 44, 'San Juan', 0, NULL),
	(165, 'asdad', 'asda', '2016-01-26', 'asda', '22223333', 'asdasdad@asdad.com', 0, NULL, 5456465, 'Río Negro', 1, NULL),
	(166, 'test', 'test', '2016-01-13', 'asdsada', '33333', 'asdasdad@asdad.com', 0, NULL, 29878065, 'Neuquén', 1, NULL),
	(169, 'ARJONA 5', 'MIGUEL 5', '2016-01-14', 'ASDASDAS', '34444', 'asdasdad@asdad.com', 0, NULL, 54545454, 'Neuquén', 1, NULL),
	(170, 'add', 'add', '2016-01-21', 'ASDASDAS', '34444', 'asdasdad@asdad.com', 0, 169, 444444444, 'Neuquén', 0, NULL),
	(171, 'gggg', 'gggggg', '2016-01-22', 'ASDASDAS', '344445555', 'asdasdad@asdad.com', 0, 169, 29878022, 'Neuquén', 0, NULL),
	(172, 'sdasdasdad', 'asdasd', '2016-01-06', 'ASDASDAS', '34444', 'asdasdad@asdad.com', 0, 169, 5555555, 'Neuquén', 0, NULL),
	(173, 'test', 'test', '2016-01-12', 'ASDASDAS', '34444', 'asdasdad@asdad.com', 0, 169, 334444444, 'Neuquén', 0, NULL),
	(174, 'VALDEZ', 'JUNIOR', '2014-02-11', 'ASDASDAS', '34444', 'asdasdad@asdad.com', 0, 169, 29878889, 'Neuquén', 3, NULL),
	(175, 'PEREZ', 'JUAN PEDRO', '1982-01-27', 'SD SDASDADADASD ASDADASDAD', '3815966987', '454545454@asda.com', 0, NULL, 29878065, 'Salta', 1, NULL),
	(176, 'test 555', 'test nombre 555', '2016-01-05', 'asd asd asd as', '546546546', 'asdasdad@asdad.com', 1, NULL, NULL, 'Catamarca', 1, NULL),
	(177, 'asdasd', 'asda', '2013-10-30', 'asdas asd asd', '546546545646', 'asdasdad@asdad.com', 1, NULL, 29878055, 'Buenos Aires', 1, NULL),
	(178, 'ARJONA 100', 'MIGUEL 100', '2015-11-29', 'ASDAS AS DA', '546545646', 'asdasdad@asdad.com', 0, NULL, 29878065, 'Buenos Aires', 0, NULL),
	(179, 'ARJONA 100', 'MIGUEL JR', '2016-12-30', 'ASDAS AS DA', '546545646', 'asdasdad@asdad.com', 0, 178, 33878065, 'Buenos Aires', 3, NULL),
	(180, 'ARJONA 100', 'Jr uuuu', '2016-12-30', 'ASDAS AS DA', '546545646', 'asdasdad@asdad.com', 0, 178, 29878088, 'Catamarca', 6, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.paciente_obrasocial: ~17 rows (aproximadamente)
DELETE FROM `paciente_obrasocial`;
/*!40000 ALTER TABLE `paciente_obrasocial` DISABLE KEYS */;
INSERT INTO `paciente_obrasocial` (`paciente_obrasocial_id`, `nro_credencial`, `provisorio`, `obrasocial_id`, `paciente_id`, `fecha`) VALUES
	(67, '65465456', 1, 4, 35, '2016-01-08'),
	(70, '455', 0, 4, 37, '2016-01-08'),
	(71, '4555', 0, 4, 38, '2016-01-08'),
	(72, '444444444444444', 1, 4, 39, '2016-01-16'),
	(74, '2132131231231313', 1, 13, 165, '2016-01-26'),
	(75, '333333333', 1, 12, 166, '2016-01-26'),
	(81, '2342424', 0, 12, 169, '2016-01-26'),
	(83, '444444444', 1, 12, 170, '2016-01-26'),
	(85, '666666666667', 1, 12, 171, '2016-01-26'),
	(88, '42323232323', 0, 12, 173, '2016-01-26'),
	(89, '4444444444444', 1, 12, 172, '2016-01-26'),
	(92, '5555-4444-8888-9999', 1, 2, 175, '2016-01-28'),
	(93, '32342432', 1, 11, 176, '2016-01-28'),
	(94, '332 423 4232', 1, 14, 177, '2016-01-28'),
	(98, '5555-4444-8888-9999', 1, 13, 178, '2016-01-29'),
	(104, '5555-4444-8888-9999', 1, 13, 179, '2016-01-29'),
	(105, '5555-4444-8888-9933', 1, 13, 180, '2016-01-29'),
	(106, '432424242342342', 0, 12, 174, '2016-02-03');
/*!40000 ALTER TABLE `paciente_obrasocial` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.practica
CREATE TABLE IF NOT EXISTS `practica` (
  `practica_id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(50) COLLATE utf8_bin DEFAULT '0',
  `nombre` varchar(164) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`practica_id`),
  KEY `practica_id` (`practica_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.practica: ~10 rows (aproximadamente)
DELETE FROM `practica`;
/*!40000 ALTER TABLE `practica` DISABLE KEYS */;
INSERT INTO `practica` (`practica_id`, `codigo`, `nombre`) VALUES
	(1, '1001', 'PRACTICA TEST 1'),
	(2, '1002', 'PRACTICA TEST 2'),
	(3, '1003', 'PRACTICA TEST 3'),
	(4, '1004', 'PRACTICA TEST 4'),
	(5, '1005', 'PRACTICA TEST 5'),
	(6, '1006', 'PRACTICA TEST6'),
	(7, '1007', 'PRACTICA TEST 7'),
	(8, '1008', 'PRACTICA TEST 8'),
	(9, '1009', 'PRACTICA TEST 9'),
	(10, '1010', 'PRACTICA TEST  10');
/*!40000 ALTER TABLE `practica` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.prestadores
CREATE TABLE IF NOT EXISTS `prestadores` (
  `prestador_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `domicilio` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `telefono` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `provincia` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`prestador_id`),
  KEY `prestador_id` (`prestador_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.prestadores: ~9 rows (aproximadamente)
DELETE FROM `prestadores`;
/*!40000 ALTER TABLE `prestadores` DISABLE KEYS */;
INSERT INTO `prestadores` (`prestador_id`, `nombre`, `domicilio`, `telefono`, `provincia`) VALUES
	(1, 'MENDEZ COLLADO', ' Muñecas 444, San Miguel de Tucumán, Tucumán', '0381 421-7000', 'Tucumán'),
	(2, 'DIAGNOSTICO GAMA', 'Maipú 749, 4000 San Miguel de Tucumán, Tucumán', '0381 439-5555', 'Tucumán'),
	(4, 'Prestador Test 1', 'asd adad asd ad ad asd ad ad ad ad ad a dad asdsa daad adsa d ad adsa aasdada adsd ', '444444444', 'Tucumán'),
	(6, 'Prestador Test 3', 'asdasd ad asd as d dd ad a ', '46546465', 'Tucumán'),
	(7, 'Prestador Test 4', 'asdas dasd asd sad asdad ada d sad sada ', '6465465', 'Tucumán'),
	(8, 'Prestador Test 5', 'asdsa dasd asd asd ada sda d asd asd asdad ad adad ada d da da ', '0381155867919', 'Tucumán'),
	(9, 'Prestador Test 6', 'asdas das dasd asdsa dadsadsa dasasdasdasdsa dasd as', '4454545454', 'Tucumán'),
	(10, 'Prestador Test 7', 'adsdsdas dsa ad asdadasd asd asd ads a', '654654654654', 'Tucumán'),
	(11, 'Prestador Test 9', 'asdsa ad asd adads asda dad ad ', '6654654', 'Tucumán'),
	(12, 'Prestador Test 8', 'sadasdsadasdad asd asd asd adsa sa asd ad ada a', '546546464', 'Tucumán');
/*!40000 ALTER TABLE `prestadores` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.profesional: ~5 rows (aproximadamente)
DELETE FROM `profesional`;
/*!40000 ALTER TABLE `profesional` DISABLE KEYS */;
INSERT INTO `profesional` (`profesional_id`, `apellido`, `nombre`, `telefono`, `matricula`, `registro_nacional`, `titulo_profesional`, `habilitacion_siprosa`, `fecha_vencimiento_habilitacion`) VALUES
	(1, 'PEREZ', 'ROBERTO CARLOS', '0381155889999', '2451788', '10001000', 'MEDICO CLINICO', 1, '2016-12-01'),
	(3, 'JULIO8', 'BOCA', '213213', '23324', '8787', '876', 1, '2015-12-18'),
	(4, 'PEDRO ', 'EL ESCAMOSO', '87987', '98797', '87987', 'JHJKH', 1, '2015-12-25'),
	(5, 'CAROLINA', 'PEDRABUENA', '09890', '098', '0980', 'DADSAD', 1, '2015-12-25'),
	(10, 'PROFESIONAL T1', 'NOMBRE 1', '546464', '654654654', '5654654566', 'SDASDASDASD', 0, '2016-01-19');
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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.profesional_especialidad: ~13 rows (aproximadamente)
DELETE FROM `profesional_especialidad`;
/*!40000 ALTER TABLE `profesional_especialidad` DISABLE KEYS */;
INSERT INTO `profesional_especialidad` (`id`, `profesional_id`, `especialidad_id`) VALUES
	(25, 1, 2),
	(26, 1, 6),
	(29, 4, 9),
	(30, 5, 1),
	(33, 5, 2),
	(34, 5, 6),
	(35, 4, 6),
	(36, 3, 2),
	(62, 10, 1),
	(63, 10, 2),
	(64, 10, 19),
	(65, 10, 6),
	(66, 10, 22);
/*!40000 ALTER TABLE `profesional_especialidad` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
