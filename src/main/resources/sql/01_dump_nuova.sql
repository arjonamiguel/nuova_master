-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.11 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para nuova
CREATE DATABASE IF NOT EXISTS `nuova` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `nuova`;


-- Volcando estructura para tabla nuova.caja
CREATE TABLE IF NOT EXISTS `caja` (
  `caja_id` int(11) NOT NULL AUTO_INCREMENT,
  `concepto` int(11) DEFAULT NULL,
  `ingreso` double(10,0) DEFAULT '0',
  `egreso` double(10,0) DEFAULT '0',
  `fecha` timestamp NULL DEFAULT NULL,
  `numero_referencia` int(11) DEFAULT NULL,
  PRIMARY KEY (`caja_id`),
  KEY `caja_id` (`caja_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.caja_cierre
CREATE TABLE IF NOT EXISTS `caja_cierre` (
  `caja_cierre_id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_cierre` date DEFAULT NULL,
  `monto` double DEFAULT NULL,
  PRIMARY KEY (`caja_cierre_id`),
  KEY `caja_cierre_id` (`caja_cierre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.caja_egresos
CREATE TABLE IF NOT EXISTS `caja_egresos` (
  `caja_egreso_id` int(11) NOT NULL AUTO_INCREMENT,
  `egreso` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `activo` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`caja_egreso_id`),
  KEY `caja_egreso_id` (`caja_egreso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.caja_orden
CREATE TABLE IF NOT EXISTS `caja_orden` (
  `caja_orden_id` int(11) NOT NULL AUTO_INCREMENT,
  `orden_id` int(11) DEFAULT NULL,
  `caja_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`caja_orden_id`),
  KEY `caja_orden_id` (`caja_orden_id`),
  KEY `FK_caja_orden_caja` (`caja_id`),
  KEY `FK_caja_orden_orden` (`orden_id`),
  CONSTRAINT `FK_caja_orden_caja` FOREIGN KEY (`caja_id`) REFERENCES `caja` (`caja_id`),
  CONSTRAINT `FK_caja_orden_orden` FOREIGN KEY (`orden_id`) REFERENCES `orden` (`orden_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.calendario
CREATE TABLE IF NOT EXISTS `calendario` (
  `calendario_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource` char(50) COLLATE utf8_bin DEFAULT NULL,
  `start` timestamp NULL DEFAULT NULL,
  `end` timestamp NULL DEFAULT NULL,
  `title` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`calendario_id`),
  KEY `calendario_id` (`calendario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.especialidad
CREATE TABLE IF NOT EXISTS `especialidad` (
  `especialidad_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(256) COLLATE utf8_bin DEFAULT '0',
  PRIMARY KEY (`especialidad_id`),
  KEY `id` (`especialidad_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.localidades
CREATE TABLE IF NOT EXISTS `localidades` (
  `localidad_id` int(11) NOT NULL AUTO_INCREMENT,
  `provincia_id` int(11) DEFAULT NULL,
  `nombre` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`localidad_id`),
  KEY `localidad_id` (`localidad_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.obrasocial
CREATE TABLE IF NOT EXISTS `obrasocial` (
  `obrasocial_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `cuit` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `direccion` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `telefono` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `eliminado` int(11) DEFAULT NULL,
  PRIMARY KEY (`obrasocial_id`),
  KEY `obrasocialId` (`obrasocial_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.orden
CREATE TABLE IF NOT EXISTS `orden` (
  `orden_id` int(11) NOT NULL AUTO_INCREMENT,
  `paciente_id` int(11) DEFAULT NULL,
  `fecha` date DEFAULT '0000-00-00',
  `orden_tipo` int(11) DEFAULT NULL,
  `eliminado` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.orden_document
CREATE TABLE IF NOT EXISTS `orden_document` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(256) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `fileName` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `fileType` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `content` longblob,
  `orden_id` int(11) DEFAULT NULL,
  `size` bigint(20) DEFAULT '0',
  PRIMARY KEY (`document_id`),
  KEY `document_id` (`document_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.orden_practica
CREATE TABLE IF NOT EXISTS `orden_practica` (
  `ordden_practica_id` int(11) NOT NULL AUTO_INCREMENT,
  `orden_id` int(11) DEFAULT '0',
  `nomenclador_id` int(11) DEFAULT '0',
  `fecha` date DEFAULT NULL,
  `estado` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`ordden_practica_id`),
  KEY `ordden_practica_id` (`ordden_practica_id`),
  KEY `FK__orden` (`orden_id`),
  KEY `FK_orden_practica_nomenclador` (`nomenclador_id`),
  CONSTRAINT `FK__orden` FOREIGN KEY (`orden_id`) REFERENCES `orden` (`orden_id`),
  CONSTRAINT `FK_orden_practica_nomenclador` FOREIGN KEY (`nomenclador_id`) REFERENCES `nomenclador` (`nomenclador_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.orden_profesional
CREATE TABLE IF NOT EXISTS `orden_profesional` (
  `orden_profesional_id` int(11) NOT NULL AUTO_INCREMENT,
  `orden_id` int(11) DEFAULT NULL,
  `profesional_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`orden_profesional_id`),
  KEY `orden_profesional_id` (`orden_profesional_id`),
  KEY `FK__orden_profe` (`orden_id`),
  KEY `FK__profesional_orden` (`profesional_id`),
  CONSTRAINT `FK__orden_profe` FOREIGN KEY (`orden_id`) REFERENCES `orden` (`orden_id`),
  CONSTRAINT `FK__profesional_orden` FOREIGN KEY (`profesional_id`) REFERENCES `profesional` (`profesional_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


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
  `zona_afiliacion` varchar(156) COLLATE utf8_bin DEFAULT '-1',
  `obrasocial_id` int(11) DEFAULT NULL,
  `nro_credencial` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `eliminado` tinyint(4) DEFAULT '0',
  `localidad_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`paciente_id`),
  KEY `pacienteId` (`paciente_id`),
  KEY `FK_paciente_paciente` (`adherente_id`),
  KEY `eliminado` (`eliminado`),
  CONSTRAINT `FK_paciente_paciente` FOREIGN KEY (`adherente_id`) REFERENCES `paciente` (`paciente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.prestadores
CREATE TABLE IF NOT EXISTS `prestadores` (
  `prestador_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `domicilio` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `telefono` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `provincia` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`prestador_id`),
  KEY `prestador_id` (`prestador_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


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
  `nro_registro` int(11) DEFAULT NULL,
  `valido_hasta` date DEFAULT NULL,
  `fecha_emision_matricula` date DEFAULT NULL,
  `nro_libro` int(11) DEFAULT NULL,
  `nro_folio` int(11) DEFAULT NULL,
  `nro_poliza` int(11) DEFAULT NULL,
  `vigencia_desde` date DEFAULT NULL,
  `eliminado` tinyint(4) DEFAULT '0',
  `vigencia_hasta` date DEFAULT NULL,
  `tipo_matricula` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`profesional_id`),
  KEY `id` (`profesional_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.users_role_map
CREATE TABLE IF NOT EXISTS `users_role_map` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `role_id` (`role_id`),
  KEY `FK_users_role_map_user_deatils` (`id`),
  CONSTRAINT `FK_users_role_map_user_deatils` FOREIGN KEY (`id`) REFERENCES `user_details` (`id`),
  CONSTRAINT `users_role_map_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `user_roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.user_details
CREATE TABLE IF NOT EXISTS `user_details` (
  `username` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `address` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.


-- Volcando estructura para tabla nuova.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `role_id` int(11) NOT NULL,
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`role_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
