-- --------------------------------------------------------
-- Host:                         138.219.42.11
-- Versión del servidor:         5.5.47-0ubuntu0.14.04.1 - (Ubuntu)
-- SO del servidor:              debian-linux-gnu
-- HeidiSQL Versión:             9.2.0.4981
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para nuova
CREATE DATABASE IF NOT EXISTS `nuova` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `nuova`;


-- Volcando estructura para tabla nuova.nomenclador
CREATE TABLE IF NOT EXISTS `nomenclador` (
  `nomenclador_id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0',
  `nombre` varchar(164) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tipo` varchar(164) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nom_esp` varchar(164) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nom_ayu` varchar(164) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nom_ane` varchar(164) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nom_gts` varchar(164) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nom_cntayu` varchar(164) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nom_clave` varchar(164) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nom_precio` varchar(164) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nom_grupo` varchar(164) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `estado` varchar(164) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`nomenclador_id`),
  KEY `practica_id` (`nomenclador_id`),
  KEY `codigo` (`codigo`),
  KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
