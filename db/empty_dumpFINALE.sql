-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: storedb
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `email` varchar(50) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `idcliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`email`),
  KEY `idcliente_idx` (`idcliente`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcliente` int(11) NOT NULL,
  PRIMARY KEY (`id`,`idcliente`),
  KEY `idcliente_idx` (`idcliente`),
  CONSTRAINT `idcliente` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contenuto`
--

DROP TABLE IF EXISTS `contenuto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contenuto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(13) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `dimensione` int(11) NOT NULL DEFAULT '1',
  `versione` varchar(7) DEFAULT NULL,
  `piva_azienda_fornitrice` varchar(11) DEFAULT NULL,
  `icona` varchar(100) DEFAULT 'images/contents_images/',
  `descrizione` varchar(1000) DEFAULT '',
  `prezzo` float DEFAULT '0',
  `cover` varchar(100) DEFAULT 'images/covers/',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `listadesideri`
--

DROP TABLE IF EXISTS `listadesideri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listadesideri` (
  `email_utente` varchar(50) NOT NULL,
  PRIMARY KEY (`email_utente`),
  CONSTRAINT `emailCliente` FOREIGN KEY (`email_utente`) REFERENCES `account` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idcliente` (`idcliente`),
  CONSTRAINT `manager_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `metodo_di_pagamento`
--

DROP TABLE IF EXISTS `metodo_di_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `metodo_di_pagamento` (
  `email_utente` varchar(50) NOT NULL,
  `identificativo` char(16) NOT NULL,
  `codice_sicurezza` char(3) DEFAULT NULL,
  `data_scadenza` date NOT NULL,
  KEY `emailCliente_idx` (`email_utente`),
  CONSTRAINT `emailUtente` FOREIGN KEY (`email_utente`) REFERENCES `account` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recensione` (
  `email_utente` varchar(50) NOT NULL,
  `id_contenuto` int(11) NOT NULL,
  `titolo` varchar(20) NOT NULL,
  `descrizione` varchar(100) NOT NULL,
  `data_recensione` date NOT NULL,
  `valutazione` int(5) DEFAULT '0',
  PRIMARY KEY (`email_utente`,`id_contenuto`),
  KEY `fbkidcontenuto_idx` (`id_contenuto`),
  CONSTRAINT `fbkemailutente` FOREIGN KEY (`email_utente`) REFERENCES `account` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fbkidcontenuto` FOREIGN KEY (`id_contenuto`) REFERENCES `contenuto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `riferita_a`
--

DROP TABLE IF EXISTS `riferita_a`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `riferita_a` (
  `id_listadesideri` varchar(50) NOT NULL,
  `id_contenuto` int(11) NOT NULL,
  PRIMARY KEY (`id_listadesideri`,`id_contenuto`),
  KEY `idContenuto_idx` (`id_contenuto`),
  CONSTRAINT `idContenuto` FOREIGN KEY (`id_contenuto`) REFERENCES `contenuto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idLista` FOREIGN KEY (`id_listadesideri`) REFERENCES `listadesideri` (`email_utente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rimozione_account`
--

DROP TABLE IF EXISTS `rimozione_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rimozione_account` (
  `email_cliente` varchar(50) NOT NULL,
  PRIMARY KEY (`email_cliente`),
  CONSTRAINT `fbkkemailcliente` FOREIGN KEY (`email_cliente`) REFERENCES `account` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ruolo`
--

DROP TABLE IF EXISTS `ruolo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruolo` (
  `ambito` varchar(255) NOT NULL,
  `idmanager` int(11) NOT NULL,
  PRIMARY KEY (`idmanager`,`ambito`),
  CONSTRAINT `ruolo_ibfk_1` FOREIGN KEY (`idmanager`) REFERENCES `manager` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `scarica`
--

DROP TABLE IF EXISTS `scarica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scarica` (
  `email_utente` varchar(50) NOT NULL,
  `id_contenuto` int(11) NOT NULL,
  `data_download` date NOT NULL,
  PRIMARY KEY (`email_utente`,`id_contenuto`),
  KEY `fbkcontenuto_idx` (`id_contenuto`),
  CONSTRAINT `fbkcontenuto` FOREIGN KEY (`id_contenuto`) REFERENCES `contenuto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fbkemail` FOREIGN KEY (`email_utente`) REFERENCES `account` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-28 14:40:55
