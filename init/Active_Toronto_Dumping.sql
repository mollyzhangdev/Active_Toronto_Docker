-- MySQL dump 10.13  Distrib 8.0.29, for macos12 (x86_64)
--
-- Host: localhost    Database: active_v2
-- ------------------------------------------------------
-- Server version	8.0.30

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

-- Creation of Database

UNLOCK TABLES;
CREATE DATABASE IF NOT EXISTS `active`;
USE `active`;
SET TIME_ZONE='-05:00';

-- CREATE USER 'admin'@'%' IDENTIFIED BY 'password';
-- GRANT ALL ON active.* TO 'admin'@'%';
-- FLUSH PRIVILEGES;
--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `availability`;
DROP TABLE IF EXISTS `reference_facility_locationorigin`;
DROP TABLE IF EXISTS `facility_activity`;
DROP TABLE IF EXISTS `activity`;
DROP TABLE IF EXISTS `facility`;
DROP TABLE IF EXISTS `type`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `city`;
DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `language_translation`;
DROP TABLE IF EXISTS `language`;
DROP TABLE IF EXISTS `translation`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
  `ID` char(2) NOT NULL,
  `TITLE` varchar(45) NOT NULL,
  `LAST_UPDATED` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `translation`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `translation` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `LAST_UPDATED` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=202744 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `language_translation`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language_translation` (
  `TRANSLATION_ID` int NOT NULL,
  `LANGUAGE_ID` char(2) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `LAST_UPDATED` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TRANSLATION_ID`,`LANGUAGE_ID`),
  KEY `LANGUAGE_TRANSLATION_ibfk_2` (`LANGUAGE_ID`),
  CONSTRAINT `LANGUAGE_TRANSLATION_ibfk_1` FOREIGN KEY (`TRANSLATION_ID`) REFERENCES `translation` (`ID`),
  CONSTRAINT `LANGUAGE_TRANSLATION_ibfk_2` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `language` (`ID`),
  CONSTRAINT `DESC_unik` UNIQUE (`DESCRIPTION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

ALTER TABLE `language_translation` ADD INDEX `DESCRIPTION_INDEX` (`DESCRIPTION`);

--
-- Table structure for table `city`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(45) NOT NULL,
  `LAST_UPDATED` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `TITLE_TRANSLATION_ID` int NOT NULL,
  `CITY_ID` int NOT NULL,
  `LAST_UPDATED` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `TITLE_TRANSLATION_ID` (`TITLE_TRANSLATION_ID`),
  KEY `CITY_ID` (`CITY_ID`),
  CONSTRAINT `CATEGORY_ibfk_1` FOREIGN KEY (`TITLE_TRANSLATION_ID`) REFERENCES `translation` (`ID`),
  CONSTRAINT `CATEGORY_ibfk_2` FOREIGN KEY (`CITY_ID`) REFERENCES `city` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `type`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `CATEGORY_ID` int NOT NULL,
  `TITLE_TRANSLATION_ID` int NOT NULL,
  `LAST_UPDATED` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `CATEGORY_ID` (`CATEGORY_ID`),
  KEY `TITLE_TRANSLATION_ID` (`TITLE_TRANSLATION_ID`),
  CONSTRAINT `TYPE_ibfk_1` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`ID`),
  CONSTRAINT `TYPE_ibfk_2` FOREIGN KEY (`TITLE_TRANSLATION_ID`) REFERENCES `translation` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2009 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `activity`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `ID` int NOT NULL,
  `TYPE_ID` int NOT NULL,
  `TITLE_TRANSLATION_ID` int NOT NULL,
  `LAST_UPDATED` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `TYPE_ID` (`TYPE_ID`),
  KEY `TITLE_TRANSLATION_ID` (`TITLE_TRANSLATION_ID`),
  CONSTRAINT `ACTIVITY_ibfk_1` FOREIGN KEY (`TYPE_ID`) REFERENCES `type` (`ID`),
  CONSTRAINT `ACTIVITY_ibfk_2` FOREIGN KEY (`TITLE_TRANSLATION_ID`) REFERENCES `translation` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `address`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `STREET_TRANSLATION_ID` int NOT NULL,
  `CITY` varchar(45) NOT NULL,
  `PROVINCE` varchar(45) NOT NULL,
  `POSTAL_CODE` varchar(10) NOT NULL,
  `COUNTRY` varchar(45) NOT NULL,
  `LONGITUDE` double(8,6) NOT NULL,
  `LATITUDE` double(8,6) NOT NULL,
  `LAST_UPDATED` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `STREET_TRANSLATION_ID` (`STREET_TRANSLATION_ID`),
  CONSTRAINT `ADDRESS_ibfk_1` FOREIGN KEY (`STREET_TRANSLATION_ID`) REFERENCES `translation` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2148 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `facility`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facility` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `PHONE` varchar(12) DEFAULT NULL,
  `ADDRESS_ID` int NOT NULL,
  `TITLE_TRANSLATION_ID` int NOT NULL,
  `URL` varchar(200) DEFAULT NULL,
  `CITY_ID` int NOT NULL,
  `LAST_UPDATED` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `TITLE_TRANSLATION_ID` (`TITLE_TRANSLATION_ID`),
  KEY `CITY_ID` (`CITY_ID`),
  KEY `FACILITY_ibfk_2` (`ADDRESS_ID`),
  CONSTRAINT `FACILITY_ibfk_1` FOREIGN KEY (`TITLE_TRANSLATION_ID`) REFERENCES `translation` (`ID`),
  CONSTRAINT `FACILITY_ibfk_2` FOREIGN KEY (`ADDRESS_ID`) REFERENCES `address` (`ID`),
  CONSTRAINT `FACILITY_ibfk_3` FOREIGN KEY (`CITY_ID`) REFERENCES `city` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2148 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `facility_activity`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facility_activity` (
  `FACILITY_ID` int NOT NULL,
  `ACTIVITY_ID` int NOT NULL,
  `LAST_UPDATED` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`FACILITY_ID`,`ACTIVITY_ID`),
  KEY `FACILITY_ACTIVITY_ibfk_2` (`ACTIVITY_ID`),
  CONSTRAINT `FACILITY_ACTIVITY_ibfk_1` FOREIGN KEY (`FACILITY_ID`) REFERENCES `facility` (`ID`),
  CONSTRAINT `FACILITY_ACTIVITY_ibfk_2` FOREIGN KEY (`ACTIVITY_ID`) REFERENCES `activity` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reference_facility_locationorigin`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reference_facility_locationorigin` (
  `FACILITY_ID` int NOT NULL,
  `LOCATION_ID` int NOT NULL,
  PRIMARY KEY (`FACILITY_ID`),
  CONSTRAINT `REFERENCE_FACILITY_LOCATIONORIGIN_ibfk_1` FOREIGN KEY (`FACILITY_ID`) REFERENCES `facility` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `availability`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `availability` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `FACILITY_ID` int NOT NULL,
  `ACTIVITY_ID` int NOT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `IS_AVAILABLE` tinyint DEFAULT '1',
  `MIN_AGE` int DEFAULT NULL,
  `MAX_AGE` int DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `AVAILABILITY_ibfk_1` (`FACILITY_ID`),
  KEY `AVAILABILITY_ibfk_2` (`ACTIVITY_ID`),
  CONSTRAINT `AVAILABILITY_ibfk_1` FOREIGN KEY (`FACILITY_ID`) REFERENCES `facility` (`ID`),
  CONSTRAINT `AVAILABILITY_ibfk_2` FOREIGN KEY (`ACTIVITY_ID`) REFERENCES `activity` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20067 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES ('en','English','2023-10-25 20:33:03'),('Fr','French','2023-10-25 20:33:03');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Ottawa','2023-08-21 23:28:54'),(2,'Toronto','2023-08-21 23:28:54');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;