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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.observaciones: ~7 rows (aproximadamente)
DELETE FROM `observaciones`;
/*!40000 ALTER TABLE `observaciones` DISABLE KEYS */;
INSERT INTO `observaciones` (`observacion_id`, `observacion`, `orden_id`, `user_name`, `fecha`) VALUES
	(1, 'El HTML plano para el componente del panel es mucho más sencillo en comparación con el componente de la barra e navegación. Tenemos que utilizar sólo tres div, con un total de cuatro clases requeridas para agregar el estilo correcto', 14, 'administrador@nuova.com', '2016-01-08 16:52:03'),
	(2, 'Nuevamente, eche un vistazo rápido a las 30 líneas originales de código HTML plano necesarias para el componente de la barra de navegación y observe cómo se construye un menú mucho más rápido (después de crear mixins reutilizables infinitamente) cuando se acorta el código a sólo 12 líneas con Jade en lugar de 30.', 14, 'administrador@nuova.com', '2016-01-11 16:37:49'),
	(3, 'asdsa as dasdasdas dasd asd ad', 14, 'administrador@nuova.com', '2016-01-12 11:02:55'),
	(4, '65465464 654sd6sa4ds5d4 5as65d4as4d a', 14, 'administrador@nuova.com', '2016-01-12 13:20:27'),
	(7, 'asd asd as da', 11, 'administrador@nuova.com', '2016-01-12 13:56:32'),
	(10, 'asdasd', 14, 'administrador@nuova.com', '2016-01-12 14:08:02'),
	(13, 'sad ada sad ', 12, 'administrador@nuova.com', '2016-01-12 14:31:44'),
	(14, 'soy muy pete', 13, 'administrador@nuova.com', '2016-01-12 15:43:47'),
	(15, 'esto es una orden completa puede realizar todo sin problemas asad asd as das dasd ad asd a asdasdsa dasdasd asjdnask jdaskd asjkdn akjsdjkasdas dajldkasjldkajsdlaksjdalksjdlaksj dlaskj dalkjdalkdjalsk jdalsj dalsjdlakjs dlajlasjdlajdlaksjdla sd ', 15, 'administrador@nuova.com', '2016-01-12 16:04:48'),
	(16, 'kskñadk asñdkasñdk asñldk añslk dañkd añsk dñask dña sñd', 15, 'administrador@nuova.com', '2016-01-12 16:06:51'),
	(17, 'asdasdasdasdasd', 15, 'administrador@nuova.com', '2016-01-12 16:07:18');
/*!40000 ALTER TABLE `observaciones` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.orden
CREATE TABLE IF NOT EXISTS `orden` (
  `orden_id` int(11) NOT NULL AUTO_INCREMENT,
  `paciente_id` int(11) DEFAULT NULL,
  `fecha` date DEFAULT '0000-00-00',
  `req_orden_medico` tinyint(4) DEFAULT '0',
  `req_credecial` tinyint(4) DEFAULT '0',
  `req_recibo_sueldo` tinyint(4) DEFAULT '0',
  `req_monotributista` tinyint(4) DEFAULT '0',
  `estado` varchar(160) COLLATE utf8_bin DEFAULT '0',
  PRIMARY KEY (`orden_id`),
  KEY `orden_id` (`orden_id`),
  KEY `FK_orden_paciente` (`paciente_id`),
  CONSTRAINT `FK_orden_paciente` FOREIGN KEY (`paciente_id`) REFERENCES `paciente` (`paciente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.orden: ~3 rows (aproximadamente)
DELETE FROM `orden`;
/*!40000 ALTER TABLE `orden` DISABLE KEYS */;
INSERT INTO `orden` (`orden_id`, `paciente_id`, `fecha`, `req_orden_medico`, `req_credecial`, `req_recibo_sueldo`, `req_monotributista`, `estado`) VALUES
	(11, 35, '2016-01-08', 1, 0, 0, 0, 'AUTORIZADA'),
	(12, 38, '2016-01-08', 0, 0, 0, 0, 'AUTORIZADA'),
	(13, 38, '2016-01-08', 0, 0, 0, 0, 'EN_OBSERVACION'),
	(14, 35, '2016-01-08', 1, 1, 0, 1, 'AUTORIZADA'),
	(15, 35, '2016-01-12', 1, 1, 1, 1, 'AUTORIZADA');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.orden_practica: ~0 rows (aproximadamente)
DELETE FROM `orden_practica`;
/*!40000 ALTER TABLE `orden_practica` DISABLE KEYS */;
INSERT INTO `orden_practica` (`ordden_practica_id`, `orden_id`, `practica_id`, `fecha`) VALUES
	(1, 12, 3, '2016-01-12'),
	(2, 13, 5, '2016-01-12'),
	(3, 15, 6, '2016-01-12'),
	(4, 15, 5, '2016-01-12'),
	(5, 15, 3, '2016-01-12');
/*!40000 ALTER TABLE `orden_practica` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.orden_workflow
CREATE TABLE IF NOT EXISTS `orden_workflow` (
  `orden_workflow_id` int(11) NOT NULL AUTO_INCREMENT,
  `orden_id` int(11) DEFAULT NULL,
  `user_name` varchar(164) COLLATE utf8_bin DEFAULT NULL,
  `estado` varchar(164) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`orden_workflow_id`),
  KEY `orden_workflow_id` (`orden_workflow_id`),
  KEY `FK_orden_workflow_orden` (`orden_id`),
  CONSTRAINT `FK_orden_workflow_orden` FOREIGN KEY (`orden_id`) REFERENCES `orden` (`orden_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.orden_workflow: ~7 rows (aproximadamente)
DELETE FROM `orden_workflow`;
/*!40000 ALTER TABLE `orden_workflow` DISABLE KEYS */;
INSERT INTO `orden_workflow` (`orden_workflow_id`, `orden_id`, `user_name`, `estado`) VALUES
	(7, 11, 'administrador@nuova.com', 'INCOMPLETA'),
	(8, 12, 'administrador@nuova.com', 'INCOMPLETA'),
	(9, 13, 'administrador@nuova.com', 'INCOMPLETA'),
	(10, 14, 'administrador@nuova.com', 'INCOMPLETA'),
	(15, 11, 'administrador@nuova.com', 'AUTORIZADA'),
	(18, 14, 'administrador@nuova.com', 'AUTORIZADA'),
	(21, 12, 'administrador@nuova.com', 'AUTORIZADA'),
	(22, 13, 'administrador@nuova.com', 'EN_OBSERVACION'),
	(23, 15, 'administrador@nuova.com', 'PENDIENTE'),
	(24, 15, 'administrador@nuova.com', 'AUTORIZADA'),
	(25, 15, 'administrador@nuova.com', 'AUTORIZADA'),
	(26, 15, 'administrador@nuova.com', 'AUTORIZADA');
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
  `liberado` tinyint(4) DEFAULT '0',
  `adherente_id` int(11) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL,
  `provincia` varchar(156) COLLATE utf8_bin DEFAULT NULL,
  `titular` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`paciente_id`),
  KEY `pacienteId` (`paciente_id`),
  KEY `FK_paciente_paciente` (`adherente_id`),
  CONSTRAINT `FK_paciente_paciente` FOREIGN KEY (`adherente_id`) REFERENCES `paciente` (`paciente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.paciente: ~2 rows (aproximadamente)
DELETE FROM `paciente`;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` (`paciente_id`, `apellido`, `nombre`, `fecha_nacimiento`, `domicilio`, `telefono`, `mail`, `liberado`, `adherente_id`, `dni`, `provincia`, `titular`) VALUES
	(35, 'ARJONA', 'MIGUEL ANGEL', '2015-01-01', 'Descripcion Domicilio', '4930332', 'arjonamiguel@gmail.com', 0, NULL, 29878065, 'Tucumán', 1),
	(37, 'asdasd', 'asda', '2015-01-01', 'Descripcion Domicilio', '4930332', 'arjonamiguel@gmail.com', 0, 35, 654654, 'Tucumán', 0),
	(38, 'asdasd', 'asda', '2015-01-01', 'Descripcion Domicilio', '4930332', 'arjonamiguel@gmail.com', 0, 35, 654654, 'Tucumán', 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.paciente_obrasocial: ~2 rows (aproximadamente)
DELETE FROM `paciente_obrasocial`;
/*!40000 ALTER TABLE `paciente_obrasocial` DISABLE KEYS */;
INSERT INTO `paciente_obrasocial` (`paciente_obrasocial_id`, `nro_credencial`, `provisorio`, `obrasocial_id`, `paciente_id`, `fecha`) VALUES
	(67, '65465456', 1, 4, 35, '2016-01-08'),
	(70, '455', 0, 4, 37, '2016-01-08'),
	(71, '4555', 0, 4, 38, '2016-01-08');
/*!40000 ALTER TABLE `paciente_obrasocial` ENABLE KEYS */;


-- Volcando estructura para tabla nuova.practica
CREATE TABLE IF NOT EXISTS `practica` (
  `practica_id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(50) COLLATE utf8_bin DEFAULT '0',
  `nombre` varchar(164) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`practica_id`),
  KEY `practica_id` (`practica_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Volcando datos para la tabla nuova.practica: ~8 rows (aproximadamente)
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
