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
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('francesco.califano96@yahoo.it','11e3e073d82b5236e1bdbcfcfdafa9ff5c5cb08a',26),('mario@yahoo.it','11e3e073d82b5236e1bdbcfcfdafa9ff5c5cb08a',28),('michele.spano@yahoo.it','11e3e073d82b5236e1bdbcfcfdafa9ff5c5cb08a',27);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (2,26);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (26,'Francesco','Califano'),(27,'Michele','Spano'),(28,'Mario','Ruggiero');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `contenuto`
--

LOCK TABLES `contenuto` WRITE;
/*!40000 ALTER TABLE `contenuto` DISABLE KEYS */;
INSERT INTO `contenuto` VALUES (1,'Applicazione','Angry Birds',50,'1.0','9462859ADC9','images/contents_images/apps/Angry_Birds.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',1.99,'images/covers/Angry_Birds_cover_hor.png'),(2,'Applicazione','GoogleChrome',100,'1.0','5462659CDC7','images/contents_images/apps/Google_Chrome.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/Google_Chrome_cover_hor.png'),(3,'Applicazione','Asphalt7',100,'1.0','I5GFD56J7LP','images/contents_images/apps/Asphalt7.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',1.99,'images/covers/'),(4,'Applicazione','Whatsapp',150,'1.0','5462659CDC7','images/contents_images/apps/Whatsapp.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/Whatsapp_cover_ver.jpg'),(5,'Applicazione','Snapchat',63,'1.0','5462659CDC7','images/contents_images/apps/Snapchat.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/Snapchat_cover_hor.jpg'),(6,'Applicazione','Netflix',18,'1.0','9462859ADC9','images/contents_images/apps/Netflix.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/Netflix_cover_ver.jpg'),(7,'Applicazione','Tiger Ball',16,'1.0','I5GFD56J7LP','images/contents_images/apps/Tiger_Ball.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/'),(8,'Applicazione','Final Fantasy',500,'1.0','I5GFD56J7LP','images/contents_images/apps/Final_Fantasy.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',50,'images/covers/Final_Fantasy_XV_cover_hor.png'),(9,'Applicazione','WolframAlpha',20,'1.0','9462859ADC9','images/contents_images/apps/Wolfram_Alpha.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/'),(10,'Applicazione','Runtastic PRO',3,'1.0','9462859ADC9','images/contents_images/apps/Runtastic_PRO.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',3.99,'images/covers/Runtastic_cover_hor.jpg'),(11,'Applicazione','Gangstar Vegas',800,'1.0','67832297D09','images/contents_images/apps/Gangstar_Vegas.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',5,'images/covers/'),(12,'Applicazione','Six-Guns: Gang Showdown',402,'1.0','67832297D09','images/contents_images/apps/Six_Guns.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/'),(13,'Applicazione','Gods of Rome',29,'1.0','67832297D09','images/contents_images/apps/Gods_of_Rome.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/Gods_of_Rome_cover_hor.jpg'),(14,'Applicazione','Clash Royale',54,'1.0','9462859ADC9','images/contents_images/apps/Clash_Royale.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/'),(15,'Applicazione','Clash of Clans',27,'1.0','9462859ADC9','images/contents_images/apps/Clash_of_Clans.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/'),(16,'Applicazione','GTA San Andreas',460,'1.0','5JDD06WDF78','images/contents_images/apps/GTA_San_Andreas.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',10,'images/covers/'),(17,'Applicazione','Max Payne Mobile',177,'1.0','5JDD06WDF78','images/contents_images/apps/Max_Payne_Mobile.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',5,'images/covers/'),(18,'Applicazione','Hitman Sniper',67,'1.0','3FG6770DSM7','images/contents_images/apps/Hitman_Sniper.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',49.99,'images/covers/'),(19,'Applicazione','Final Fantasy IV',133,'1.0','3FG6770DSM7','images/contents_images/apps/Final_Fantasy_IV.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',60,'images/covers/'),(20,'Applicazione','Kingdom Hearts X',133,'1.0','3FG6770DSM7','images/contents_images/apps/Kingdom_Hearts_X.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',50,'images/covers/'),(21,'Musica','Despacito',10,'mp3','GH5G4F33HL7','images/contents_images/music/Despacito.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/'),(22,'Musica','El perdon',5,'mp3','HJE4SA5G7H3','images/contents_images/music/El_Perdon.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',3,'images/covers/'),(23,'Musica','Come un pittore',3,'mp3','LPH53DR45GG','images/contents_images/music/Come_Un_Pittore.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/'),(24,'Musica','Fallen',10,'mp3','LPH53DR45GG','images/contents_images/music/Fallen.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/'),(25,'Musica','Legend',5,'mp3','GH5G4F33HL7','images/contents_images/music/Il_Conforto.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',0,'images/covers/'),(26,'Musica','Shape of you',3,'mp3','HJE4SA5G7H3','images/contents_images/music/Shape_of_You.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',1,'images/covers/'),(27,'Musica','Make a human',2,'mp3','HJE4SA5G7H3','images/contents_images/music/Legend.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',4,'images/covers/'),(28,'Musica','Il Conforto',2,'mp3','O56S1MK774C','images/contents_images/music/Human.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',1.5,'images/covers/'),(29,'Film','Frozen: il regno di ghiaccio',800,'mp4','9232859EDC1','images/contents_images/films/Frozen.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',19,'images/covers/'),(30,'Film','Twilight',1000,'mp4','923E3D9EDC1','images/contents_images/films/Twilight.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',22,'images/covers/'),(31,'Film','Titanic',900,'mp4','9232859EDC1','images/contents_images/films/Titanic.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',14,'images/covers/'),(32,'Film','Il padrino',700,'mp4','9232859EDC1','images/contents_images/films/Il_Padrino.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',13,'images/covers/'),(33,'Film','L\'ultimo samurai',800,'mp4','FGH65RF4D32','images/contents_images/films/L\'ultimo_Samurai.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',14,'images/covers/L\'ultimo_Samurai_cover_ver.jpg'),(34,'Film','Indiana Jones',900,'mp4','FGH65RF4D32','images/contents_images/films/Indiana_Jones.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',25,'images/covers/'),(35,'Film','Doctor Strange',500,'mp4','TGO88W500KD','images/contents_images/films/Doctor_Strange.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\r \r But once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\r \r You’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',20.99,'images/covers/Doctor_Strange_cover_hor.png'),(36,'Film','The Accountant',440,'mp4','TGO88W500KD','images/contents_images/films/The_Accountant.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',30.99,'images/covers/'),(37,'Film','Lo Squalo',330,'mp4','TGO88W500KD','images/contents_images/films/Lo_Squalo.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',3.99,'images/covers/'),(38,'Film','La Mummia',500,'mp4','TGO88W500KD','images/contents_images/films/La_Mummia.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',5.99,'images/covers/'),(39,'Libro','Harry Potter e la camera dei segreti',1,'pdf','9IIKG6543DF','images/contents_images/books/Harry_Potter_Camera_Dei_Segreti.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',12.99,'images/covers/'),(40,'Libro','Anime di vetro',1,'pdf','9462339ABC9','images/contents_images/books/Anime_Di_Vetro.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',10,'images/covers/'),(41,'Libro','La paranza',1,'pdf','9IIKG6543DF','images/contents_images/books/La_Paranza.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',12,'images/covers/'),(42,'Libro','Harry Potter e la pietra filosofale',1,'pdf','9462339ABC9','images/contents_images/books/Harry_Potter_Pietra_Filosofale.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',4.99,'images/covers/'),(43,'Libro','Il labirinto degli spiriti',1,'pdf','9IIKG6543DF','images/contents_images/books/Il_Labirinto_Degli_Spiriti.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',13,'images/covers/'),(44,'Libro','Alice nel paese delle meraviglie',1,'pdf','SD4FGH66FGR','images/contents_images/books/Alice_Nel_Paese_Delle_Meraviglie.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',5.99,'images/covers/'),(45,'Libro','Dentro l\'acqua',1,'pdf','SD4FGH66FGR','images/contents_images/books/Dentro_L\'acqua.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',20.99,'images/covers/'),(46,'Libro','Tredici',1,'pdf','SD4FGH66FGR','images/contents_images/books/Tredici.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.\n\nBut once a user found your app, you still have to convince him to download it. The first steps are your app icon and your app ratings, and if the user decides your mobile application is still worth looking at he will then proceed to your app page.\n\nYou’ve gotten your user all the way here (or he got here after reading a review about your app) so this is your one shot to convince him…Don’t scare him off! Although an app’s description is no longer searchable, it plays a big role in your app marketing.',4,'images/covers/'),(50,'Applicazione','GTAV',1000,'1.0','5JDD06WDF78','images/contents_images/apps/GTA_V.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',19.99,'images/covers/GTA5_cover_hor.jpg'),(51,'Applicazione','Prince of Persia',200,'2.0','67832297D09','images/contents_images/apps/Prince_of_Persia.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',1.99,'images/covers/Prince_of_Persia_cover_ver.jpg'),(52,'Musica','Authentic',10,'mp3','GH5G4F33HL7','images/contents_images/music/Authentic.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',10,'images/covers/'),(53,'Musica','Crash My Party',3,'mp3','GH5G4F33HL7','images/contents_images/music/Crash_My_Party.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',5,'images/covers/'),(54,'Musica','God Son ',2,'mp3','GH5G4F33HL7','images/contents_images/music/God_Son.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',3,'images/covers/'),(55,'Musica','Hinder',4,'mp3','GH5G4F33HL7','images/contents_images/music/Hinder.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',4,'images/covers/'),(56,'Musica','Nine Track Mind',5,'mp3','GH5G4F33HL7','images/contents_images/music/Nine_Track_Mind.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',6,'images/covers/'),(57,'Musica','Radio Music',6,'mp3','GH5G4F33HL7','images/contents_images/music/Radio_Music.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',4,'images/covers/'),(58,'Musica','Sand Star',3,'mp3','GH5G4F33HL7','images/contents_images/music/Sand_Star.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',10,'images/covers/'),(59,'Musica','Simple Plan',5,'mp3','GH5G4F33HL7','images/contents_images/music/Simple_Plan.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',11,'images/covers/'),(60,'Musica','Stars Dance',5,'mp3','GH5G4F33HL7','images/contents_images/music/Stars_Dance.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',12,'images/covers/'),(61,'Musica','The Glow',7,'mp3','GH5G4F33HL7','images/contents_images/music/The_Glow.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',6,'images/covers/'),(62,'Musica','Title',8,'mp3','GH5G4F33HL7','images/contents_images/music/Title.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',7,'images/covers/'),(63,'Musica','To Be Loved',9,'mp3','GH5G4F33HL7','images/contents_images/music/To_Be_Loved.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',5,'images/covers/'),(64,'Musica','In Too Deep',6,'mp3','GH5G4F33HL7','images/contents_images/music/In_Too_Deep.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',7,'images/covers/'),(65,'Musica','Nothing But The Beat',100,'mp3','GH5G4F33HL7','images/contents_images/music/Nothing_But_The_Beat.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',20,'images/covers/'),(78,'Libro','A night in the woods',100,'pdf','9462339ABC9','images/contents_images/books/A_Night_In_The_Woods.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',12,'images/covers/'),(79,'Libro','Catcher in the rye',30,'pdf','9462339ABC9','images/contents_images/books/Catcher_In_The_Rye.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',15,'images/covers/'),(80,'Libro','Dutch',40,'pdf','9462339ABC9','images/contents_images/books/Dutch.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',13,'images/covers/'),(81,'Libro','Game of throne',24,'pdf','9462339ABC9','images/contents_images/books/Game_Of_Thrones.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',10,'images/covers/'),(82,'Libro','Fred',33,'pdf','9462339ABC9','images/contents_images/books/Fred.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',5,'images/covers/'),(83,'Libro','Gone girl',12,'pdf','9462339ABC9','images/contents_images/books/Gone_Girl.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',21,'images/covers/'),(84,'Libro','Good and busy',50,'pdf','9462339ABC9','images/contents_images/books/Good_And_Busy.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',33,'images/covers/'),(85,'Libro','Home sweet home',200,'pdf','9462339ABC9','images/contents_images/books/Home_Sweet_Home.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',10,'images/covers/'),(86,'Libro','Pattern maker',40,'pdf','9462339ABC9','images/contents_images/books/Pattern_Maker.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',10,'images/covers/'),(87,'Libro','Street art',500,'pdf','9462339ABC9','images/contents_images/books/Street_Art.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',5,'images/covers/'),(88,'Libro','Stars',600,'pdf','9462339ABC9','images/contents_images/books/Stars.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',4,'images/covers/'),(89,'Libro','The Happy Lemon',20,'pdf','9462339ABC9','images/contents_images/books/The_Happy_Lemon.png','In terms of App Store Optimization we already talked about how to choose your app name and keywords for an iPhone app, which are the most important things to do to get found by a search in the App Store.',7,'images/covers/');
/*!40000 ALTER TABLE `contenuto` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `listadesideri`
--

LOCK TABLES `listadesideri` WRITE;
/*!40000 ALTER TABLE `listadesideri` DISABLE KEYS */;
INSERT INTO `listadesideri` VALUES ('francesco.califano96@yahoo.it'),('mario@yahoo.it'),('michele.spano@yahoo.it');
/*!40000 ALTER TABLE `listadesideri` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,27);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `metodo_di_pagamento`
--

LOCK TABLES `metodo_di_pagamento` WRITE;
/*!40000 ALTER TABLE `metodo_di_pagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `metodo_di_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `riferita_a`
--

LOCK TABLES `riferita_a` WRITE;
/*!40000 ALTER TABLE `riferita_a` DISABLE KEYS */;
/*!40000 ALTER TABLE `riferita_a` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `rimozione_account`
--

LOCK TABLES `rimozione_account` WRITE;
/*!40000 ALTER TABLE `rimozione_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `rimozione_account` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ruolo`
--

LOCK TABLES `ruolo` WRITE;
/*!40000 ALTER TABLE `ruolo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ruolo` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `scarica`
--

LOCK TABLES `scarica` WRITE;
/*!40000 ALTER TABLE `scarica` DISABLE KEYS */;
/*!40000 ALTER TABLE `scarica` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-28 14:40:14
