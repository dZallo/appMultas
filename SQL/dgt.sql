CREATE DATABASE  IF NOT EXISTS `dgt` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dgt`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: dgt
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
INSERT INTO `agente` VALUES (1,'Majonei','1234',39),(2,'Jonny Walker','4321',36),(3,'Monk','7890',38),(4,'Takel Berry','0987',37),(5,'Tontini','5678',37);
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
  `fecha_modificacion` datetime DEFAULT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_multas_coches_idx` (`id_coche`),
  KEY `fk_multas_agentes_idx` (`id_agente`),
  CONSTRAINT `fk_multas_agentes` FOREIGN KEY (`id_agente`) REFERENCES `agente` (`id`),
  CONSTRAINT `fk_multas_coches` FOREIGN KEY (`id_coche`) REFERENCES `coche` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multa`
--

LOCK TABLES `multa` WRITE;
/*!40000 ALTER TABLE `multa` DISABLE KEYS */;
INSERT INTO `multa` VALUES (1,3,3,200,'por feo','2019-01-07 10:36:52',NULL,NULL),(2,6,1,500,'exceso velocidad 240km/h','2019-01-07 10:38:46',NULL,NULL),(3,1,2,700,'por empinar codo 8.0','2018-12-31 22:40:52',NULL,NULL),(4,1,2,700,'por empinar codo 8.0','2019-01-07 10:41:55',NULL,NULL),(5,1,1,1400,'por ir a200k/h en zona de 30km/h','2019-01-07 12:40:21',NULL,NULL),(6,3,3,200,'por no cambiar el coche (feo)','2019-01-07 12:40:40',NULL,NULL),(7,1,1,500,'Pasar semáforo en rojo','2019-01-09 13:25:34',NULL,'2019-01-14 12:49:19'),(8,6,1,90,'Hacerme una peineta','2019-01-09 13:27:19',NULL,NULL),(9,6,1,1000,'Aparcar donde no debe','2019-01-09 13:29:00',NULL,NULL),(10,1,1,3000,'Muuu malo','2019-01-09 13:47:28',NULL,NULL),(11,1,1,1,'Objeto no identificado: sdafdsfsafd','2019-01-09 13:48:03',NULL,NULL),(12,1,1,2,'Me ha rayado el buga','2019-01-09 13:49:56',NULL,NULL),(13,1,1,3,'Me a hecho un calvo','2019-01-09 13:50:13',NULL,NULL),(14,6,1,200,'Me ha llamado \"Sr. Anacardo\"','2019-01-09 13:50:42',NULL,NULL),(15,6,1,2,'Me quería dar propina','2019-01-09 13:51:19',NULL,NULL),(16,1,1,300,'Se ha pasado de velocidad','2019-01-09 14:17:42',NULL,NULL),(17,1,1,500,'Ha excedido el límite de velocidad, 20km/h','2019-01-10 12:12:51',NULL,NULL),(18,6,1,2000000,'Manoloooooooooo','2019-01-11 10:30:55',NULL,NULL),(19,6,1,300,'sadsadasdadadsadasda','2019-01-11 10:49:52',NULL,NULL),(20,6,1,3000,'Se ha puesto a quemar ruedaca de la buena','2019-01-11 11:58:22',NULL,'2019-01-11 12:47:00'),(21,1,1,300,'Multon por ir to´cocido','2019-01-11 14:10:07',NULL,'2019-01-11 14:10:20'),(22,6,1,50,'Iba 20km/h por encima en una zona de 120','2019-01-14 12:51:03',NULL,'2019-01-14 12:59:34'),(23,1,1,21,'Sanción nos faltan decimales.Porque es más feo que un ñu','2019-01-14 12:57:11',NULL,'2019-01-14 12:58:40'),(24,6,1,200,'Se ha saltado un semaforo en rojo','2019-01-14 13:09:16',NULL,NULL),(25,1,1,49.99,'Por petardo','2019-01-14 14:09:39',NULL,NULL),(26,6,1,56.66,'Ander se ha saltado un STOP','2019-01-14 14:23:25',NULL,NULL),(27,6,1,300,'Va con las luces de atrás fundidas Va con las luces de atrás fundidas Va con las luces de atrás fundidas Va con las luces de atrás fundidas Va con las luces de atrás fundidas Va con las luces de atrás fundidas Va con las luces de atrás fundidas ','2019-01-15 10:41:15',NULL,'2019-01-15 11:53:23'),(28,1,1,222,'Se ha saltado un Stop\r\n','2019-01-15 10:55:27',NULL,'2019-01-15 10:58:25'),(29,1,1,2000,'Stop y semaforo en rojo','2019-01-15 11:36:51',NULL,'2019-01-15 11:36:57');
/*!40000 ALTER TABLE `multa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'dgt'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-15 13:58:00
