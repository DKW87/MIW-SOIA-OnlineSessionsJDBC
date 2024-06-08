DROP SCHEMA IF EXISTS OnlineSessions;
CREATE DATABASE  IF NOT EXISTS `OnlineSessions`;
USE `OnlineSessions`;

--
-- Table structure for table `Muzikant`
--
CREATE TABLE `Muzikant` (
  `artiestenNaam` varchar(45) NOT NULL,
  `instrument` varchar(45) NOT NULL,
  `jaarErvaring` int NOT NULL,
  PRIMARY KEY (`artiestenNaam`)
);

INSERT INTO `Muzikant` VALUES ('Adele','Zang',12),
('Andy Summers','Gitaar',59),
('Billie Eilish','Zang',5),
('Candy Dulfer','Alt saxofoon',34),
('Govert Mulder','Klarinet',11),
('Guy Berryman','Bas',24),
('Marco Hendriks','Bas',7),
('Peter Heijmans','Drums',4),
('Phil Collins','Drums',50),
('Rick Wakeman','Toetsen',51);

--
-- Table structure for table `Technicus`
--
CREATE TABLE `Technicus` (
  `codeTechnicus` varchar(6) NOT NULL,
  `voornaam` varchar(45) NOT NULL,
  `mobielNummer` varchar(10) NOT NULL,
  PRIMARY KEY (`codeTechnicus`)
); 

INSERT INTO `Technicus` VALUES ('bontj','Jan','0625748231'),
('dijkd','Dick','0653623418'),
('johna','Albert','0656237822'),
('kompe','Pete','0625367188'),
('moege','Gerard','0634923641');


--
-- Table structure for table `Session`
--
CREATE TABLE `Session` (
  `idSession` int NOT NULL AUTO_INCREMENT,
  `sessionDatum` date NOT NULL,
  `tijdsduur` decimal(3,1) NOT NULL,
  `opname` tinyint NOT NULL,
  `aantalMuzikanten` int NOT NULL,
  `organisator` varchar(45) NOT NULL,
  PRIMARY KEY (`idSession`),
  KEY `fk_Session_Muzikant1_idx` (`organisator`),
  CONSTRAINT `fk_Session_Muzikant1` FOREIGN KEY (`organisator`) REFERENCES `Muzikant` (`artiestenNaam`) ON DELETE RESTRICT ON UPDATE CASCADE
) ;

INSERT INTO `Session` VALUES (1,'2020-12-04',3.0,0,2,'Adele'),
(2,'2020-12-04',2.5,1,7,'Candy Dulfer'),
(3,'2020-12-05',2.5,0,5,'Marco Hendriks'),
(4,'2020-12-05',2.0,0,3,'Billie Eilish'),
(5,'2020-12-05',3.0,1,5,'Peter Heijmans'),
(6,'2020-12-05',3.0,1,4,'Guy Berryman'),
(7,'2020-12-06',1.5,1,1,'Phil Collins'),
(8,'2020-12-06',3.0,1,5,'Peter Heijmans'),
(9,'2020-12-06',3.0,1,3,'Andy Summers'),
(10,'2020-12-06',3.0,1,3,'Billie Eilish'),
(11,'2020-12-07',4.0,0,5,'Govert Mulder'),
(12,'2020-12-08',1.5,0,4,'Guy Berryman'),
(13,'2020-12-09',3.0,1,3,'Billie Eilish');

--
-- Table structure for table `Ondersteuning`
--
CREATE TABLE `Ondersteuning` (
  `codeTechnicus` varchar(6) NOT NULL,
  `idSession` int NOT NULL,
  `rol` varchar(45) NOT NULL,
  PRIMARY KEY (`codeTechnicus`,`idSession`),
  KEY `fk_Technicus_has_Session_Session1_idx` (`idSession`),
  KEY `fk_Technicus_has_Session_Technicus1_idx` (`codeTechnicus`),
  CONSTRAINT `fk_Technicus_has_Session_Session1` FOREIGN KEY (`idSession`) REFERENCES `Session` (`idSession`) ON DELETE CASCADE,
  CONSTRAINT `fk_Technicus_has_Session_Technicus1` FOREIGN KEY (`codeTechnicus`) REFERENCES `Technicus` (`codeTechnicus`)
);

INSERT INTO `Ondersteuning` VALUES ('bontj',2,'Audio'),
('bontj',5,'Audio'),
('bontj',6,'Video/Audio'),
('bontj',7,'Audio'),
('bontj',8,'Video/Audio'),
('bontj',9,'Audio'),
('bontj',11,'Audio'),
('bontj',13,'Streaming'),
('dijkd',1,'Video'),
('dijkd',4,'Overall'),
('dijkd',10,'Overall'),
('dijkd',13,'Video'),
('johna',5,'Video'),
('johna',7,'Video'),
('johna',9,'Video'),
('johna',12,'Overall'),
('kompe',1,'Audio'),
('kompe',3,'Audio'),
('kompe',6,'Streaming'),
('kompe',13,'Audio'),
('moege',1,'Streaming'),
('moege',2,'Video'),
('moege',3,'Video'),
('moege',8,'Streaming'),
('moege',11,'Video'),
('moege',13,'Internet');


--
-- User voor de database te gebruiken in JDBC connectie
--
CREATE USER 'userOnlineSessions'@'localhost' IDENTIFIED BY 'userOnlineSessionsPW';
GRANT ALL PRIVILEGES ON OnlineSessions.* TO 'userOnlineSessions'@'localhost';
