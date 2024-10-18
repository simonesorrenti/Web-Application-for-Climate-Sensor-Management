-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sensorlogicsystem
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `detection`
--

DROP TABLE IF EXISTS `detection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detection` (
  `id` int NOT NULL AUTO_INCREMENT,
  `value` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  `sensor` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sensor_idx` (`sensor`),
  CONSTRAINT `sensor` FOREIGN KEY (`sensor`) REFERENCES `sensor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detection`
--

LOCK TABLES `detection` WRITE;
/*!40000 ALTER TABLE `detection` DISABLE KEYS */;
INSERT INTO `detection` VALUES (1,'27 °C','2020-08-27 00:00:00',1),(3,'24 °C','2020-08-31 13:00:00',1),(4,'20 °C','2020-08-30 13:00:00',1),(5,'20 °C','2020-08-29 13:00:00',1),(13,'22 °C','2020-08-21 00:00:00',1),(14,'22 °C','2020-08-21 00:00:00',1),(15,'22 °C','2020-08-21 00:00:00',1),(16,'22 °C','2020-08-21 00:00:00',1),(17,'22 °C','2020-08-21 00:00:00',1),(18,'22 °C','2020-08-21 00:00:00',1),(19,'22 °C','2020-08-21 00:00:00',1),(20,'22 °C','2020-08-21 00:00:00',1),(21,'22 °C','2020-08-21 00:00:00',1),(22,'22 °C','2020-08-21 00:00:00',1),(23,'22 °C','2020-08-21 00:00:00',1),(24,'22 °C','2020-08-21 00:00:00',1),(25,'22 °C','2020-08-21 00:00:00',1),(26,'22 °C','2020-08-21 00:00:00',1),(27,'22 °C','2020-08-21 00:00:00',1),(28,'22 °C','2020-08-21 00:00:00',1),(29,'25 °C','2020-09-01 00:00:00',3),(30,'25 °C','2020-09-01 00:00:00',3),(31,'25 °C','2020-09-01 00:00:00',3),(32,'25 °C','2020-09-01 00:00:00',3),(33,'25 °C','2020-09-01 00:00:00',3),(34,'25 °C','2020-09-01 00:00:00',3),(35,'25 °C','2020-09-01 00:00:00',3),(36,'25 °C','2020-09-01 00:00:00',3),(37,'25 °C','2020-09-01 00:00:00',3),(38,'25 °C','2020-09-01 00:00:00',3),(39,'25 °C','2020-09-01 00:00:00',3),(40,'25 °C','2020-09-01 00:00:00',3),(41,'25 °C','2020-09-01 00:00:00',3),(42,'25 °C','2020-09-01 00:00:00',3),(43,'25 °C','2020-09-01 00:00:00',3),(44,'25 °C','2020-09-01 00:00:00',3),(45,'25 °C','2020-09-01 00:00:00',3),(46,'25 °C','2020-09-01 00:00:00',3),(47,'25 °C','2020-09-01 00:00:00',3),(48,'25 °C','2020-09-01 00:00:00',3),(49,'25 °C','2020-09-01 00:00:00',3),(50,'25 °C','2020-09-01 00:00:00',3),(51,'25 °C','2020-09-01 00:00:00',3),(52,'25 °C','2020-09-01 00:00:00',3);
/*!40000 ALTER TABLE `detection` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-08  9:22:04
