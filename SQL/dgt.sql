CREATE DATABASE  IF NOT EXISTS `dgt` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dgt`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dgt
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agente`
--

DROP TABLE IF EXISTS `agente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `placa` varchar(45) NOT NULL,
  `id_departamento` int(11) DEFAULT NULL,
  `password` varchar(50) NOT NULL DEFAULT '12345678',
  PRIMARY KEY (`id`),
  KEY `fk_agente_departamento_idx` (`id_departamento`),
  CONSTRAINT `fk_agente_departamento` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agente`
--

LOCK TABLES `agente` WRITE;
/*!40000 ALTER TABLE `agente` DISABLE KEYS */;
INSERT INTO `agente` VALUES (1,'Majonei','1234',39,'12345678'),(2,'Jonny Walker','4321',36,'12345678'),(3,'Monk','7890',38,'12345678'),(4,'Takel Berry','0987',37,'12345678'),(5,'Tontini','5678',37,'12345678');
/*!40000 ALTER TABLE `agente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coche`
--

DROP TABLE IF EXISTS `coche`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coche` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(10) NOT NULL,
  `modelo` varchar(45) NOT NULL DEFAULT 'cuatro latas',
  `km` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `maticula_UNIQUE` (`matricula`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coche`
--

LOCK TABLES `coche` WRITE;
/*!40000 ALTER TABLE `coche` DISABLE KEYS */;
INSERT INTO `coche` VALUES (1,'3548MKZ','toyota yaris',500),(3,'9605EFH','Fiat Multipla',800),(4,'5674MBD','GRT',1800),(6,'BI0020AZ','flagoneta',47500);
/*!40000 ALTER TABLE `coche` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `departamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (1,'IT'),(2,'DEV'),(31,'VENTAS'),(33,'Ingeniería'),(34,'Producción'),(35,'Mercadeo'),(36,'Alcoholemia'),(37,'Oficina'),(38,'Fealdad'),(39,'Velocidad');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multa`
--

DROP TABLE IF EXISTS `multa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `multa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_coche` int(11) NOT NULL,
  `id_agente` int(11) NOT NULL,
  `importe` float NOT NULL DEFAULT '50',
  `concepto` varchar(255) NOT NULL,
  `fecha_alta` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_baja` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_multas_coches_idx` (`id_coche`),
  KEY `fk_multas_agentes_idx` (`id_agente`),
  CONSTRAINT `fk_multas_agentes` FOREIGN KEY (`id_agente`) REFERENCES `agente` (`id`),
  CONSTRAINT `fk_multas_coches` FOREIGN KEY (`id_coche`) REFERENCES `coche` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multa`
--

LOCK TABLES `multa` WRITE;
/*!40000 ALTER TABLE `multa` DISABLE KEYS */;
INSERT INTO `multa` VALUES (2,6,1,500,'exceso velocidad 240km/h','2019-01-07 10:38:46','2019-01-07 10:38:46',NULL),(5,1,1,1400,'por ir a200k/h en zona de 30km/h','2019-01-07 12:40:21','2019-01-07 12:40:21',NULL),(7,1,1,500,'Pasar semáforo en rojo','2019-01-09 13:25:34','2019-01-14 12:49:19','2019-01-14 12:49:19'),(9,6,1,1000,'Aparcar donde no debe','2019-01-09 13:29:00','2019-01-09 13:29:00',NULL),(17,1,1,500,'Ha excedido el límite de velocidad, 20km/h','2019-01-10 12:12:51','2019-01-10 12:12:51',NULL),(22,6,1,50,'Iba 20km/h por encima en una zona de 120','2019-01-14 12:51:03','2019-01-14 12:59:34','2019-01-14 12:59:34'),(24,6,1,200,'Se ha saltado un semaforo en rojo','2019-01-14 13:09:16','2019-01-14 13:09:16',NULL),(28,1,1,222,'Se ha saltado un Stop\r\n','2019-01-15 10:55:27','2019-01-15 10:58:25','2019-01-15 10:58:25'),(29,1,1,2000,'Stop y semaforo en rojo','2019-01-15 11:36:51','2019-01-15 11:36:57','2019-01-15 11:36:57'),(30,1,1,250,'Por aparcar en zona de carga-descarga','2019-01-16 09:21:31','2019-01-16 09:23:24','2019-01-16 09:23:24'),(31,4,2,100,'Se ha saltado un semaforo en rojo','2019-01-16 12:07:30','2019-01-16 12:07:30',NULL),(32,1,2,100,'Se ha saltado un semaforo en rojo','2019-01-16 12:14:42','2019-01-16 12:14:42',NULL),(33,3,2,50,'Ha excedido el límite de velocidad 18Km/h por encima del límite','2019-01-16 12:14:42','2019-01-16 12:14:42',NULL),(34,4,3,100,'Se ha saltado un semaforo en rojo','2019-01-16 12:16:30','2019-01-16 12:16:30',NULL),(35,3,3,150,'Ha excedido el límite de velocidad 25Km/h por encima del límite','2019-01-16 12:16:30','2019-01-16 12:16:30',NULL),(36,3,3,25,'No ha cedido el paso a los peatones en ningun paso de cebra','2019-01-16 12:16:30','2019-01-16 12:16:30',NULL),(37,4,4,74.95,'Se ha saltado un STOP','2019-01-16 12:33:21','2019-01-16 12:33:21',NULL),(38,4,4,500,'Ha doblado la tasa de alcoholemia permitida','2019-01-16 12:33:21','2019-01-16 12:33:21',NULL),(39,3,4,75,'El coche estaba circulando sin tener la ITV y/o un permiso especial','2019-01-16 12:33:21','2019-01-16 12:33:21',NULL),(40,4,5,74.95,'Se ha saltado un STOP','2019-01-16 12:41:07','2019-01-16 12:41:07',NULL),(41,6,5,200,'Ha superado la tasa de alcoholemia permitida por 0.2','2019-01-16 12:41:07','2019-01-16 12:41:07',NULL),(42,3,5,150,'El coche estaba circulando sin tener seguro','2019-01-16 12:41:07','2019-01-16 12:41:07',NULL),(43,1,1,50,'No ha marcado la trayectoria que iba a tomar con el intermitente','2019-01-16 13:06:37','2019-01-16 13:07:28','2019-01-16 13:07:28');
/*!40000 ALTER TABLE `multa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'dgt'
--
/*!50003 DROP PROCEDURE IF EXISTS `agente_getById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `agente_getById`(IN p_idAgente long)
BEGIN
	SELECT id, nombre, placa, id_departamento FROM agente WHERE id=p_idAgente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `agente_login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `agente_login`(IN p_numplaca VARCHAR(45), IN p_password VARCHAR(50))
BEGIN
	SELECT id, nombre, placa, id_departamento, `password` FROM agente WHERE placa = p_numplaca AND `password` = p_password;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `coche_getById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `coche_getById`(IN p_idCoche long)
BEGIN
	SELECT c.id, c.matricula, c.modelo, c.km FROM coche AS c WHERE c.id =p_idCoche;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `coche_getByMatricula` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `coche_getByMatricula`(IN p_matriculaCoche VARCHAR(10))
BEGIN
	SELECT c.id, c.matricula, c.modelo, c.km FROM coche AS c WHERE c.matricula =p_matriculaCoche;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `multa_getAllByIdAgente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `multa_getAllByIdAgente`(IN p_idAgente long)
BEGIN
	SELECT m.id AS id_multa,
		importe, concepto, fecha_alta, 
		id_agente,id_coche, c.matricula,
		c.modelo, c.km 
	FROM multa AS m 
    INNER JOIN coche AS c 
    ON m.id_coche= c.id 
    WHERE id_agente=p_idAgente 
    AND fecha_baja IS NULL 
    ORDER BY fecha_alta DESC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `multa_getAllByIdAgenteBaja` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `multa_getAllByIdAgenteBaja`(IN p_idAgente long)
BEGIN
	SELECT m.id AS id_multa, 
		importe, concepto, 
        fecha_alta, fecha_baja, 
        id_agente, id_coche, 
        c.matricula, c.modelo, c.km
	FROM multa AS m 
    INNER JOIN coche AS c 
    ON m.id_coche= c.id 
    WHERE id_agente=p_idAgente 
    AND m.fecha_baja IS NOT NULL 
	ORDER BY fecha_alta DESC ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `multa_getById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `multa_getById`(IN p_idMulta long)
BEGIN
	SELECT m.id AS id_multa,
		importe, concepto, 
        fecha_alta, fecha_baja, 
        id_coche, c.matricula, 
        c.modelo, c.km
	FROM multa AS m 
    INNER JOIN coche AS c 
    ON m.id_coche = c.id 
    WHERE m.id=p_idMulta 
    ORDER BY fecha_alta DESC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `multa_insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `multa_insert`(IN p_importeMulta float, 
								 IN p_conceptoMulta VARCHAR(255), 
                                 IN p_idCoche long, 
                                 IN p_idAgente long, 
                                 OUT o_idMulta long)
BEGIN
	INSERT INTO multa (importe,concepto,id_coche,id_agente) 
		   VALUES(p_importeMulta, p_conceptoMulta, p_idCoche, p_idAgente);
    SET o_idMulta = last_insert_id();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `multa_updateFechaBaja` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `multa_updateFechaBaja`(IN p_idMulta long)
BEGIN
	UPDATE multa SET fecha_baja= CURRENT_TIMESTAMP(), fecha_modificacion= CURRENT_TIMESTAMP() WHERE id = p_idMulta;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-16 13:08:42
