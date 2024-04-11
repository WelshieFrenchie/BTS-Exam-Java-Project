-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           8.0.30 - MySQL Community Server - GPL
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour ludojava
CREATE DATABASE IF NOT EXISTS `ludojava` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ludojava`;

-- Listage de la structure de table ludojava. admin
CREATE TABLE IF NOT EXISTS `admin` (
  `idPers` int NOT NULL,
  `estAutorisé` int NOT NULL,
  PRIMARY KEY (`idPers`,`estAutorisé`),
  KEY `FK_admin_autorisations` (`estAutorisé`),
  CONSTRAINT `FK_admin_autorisations` FOREIGN KEY (`estAutorisé`) REFERENCES `autorisations` (`idAuto`),
  CONSTRAINT `FK_admin_personne` FOREIGN KEY (`idPers`) REFERENCES `personne` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table ludojava.admin : ~0 rows (environ)
DELETE FROM `admin`;

-- Listage de la structure de table ludojava. autorisations
CREATE TABLE IF NOT EXISTS `autorisations` (
  `idAuto` int NOT NULL AUTO_INCREMENT,
  `nomAuto` int DEFAULT NULL,
  PRIMARY KEY (`idAuto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table ludojava.autorisations : ~0 rows (environ)
DELETE FROM `autorisations`;

-- Listage de la structure de table ludojava. commentaires
CREATE TABLE IF NOT EXISTS `commentaires` (
  `idUser` int NOT NULL,
  `idJeu` int NOT NULL,
  `dateCom` datetime NOT NULL,
  `scoreCom` int NOT NULL,
  `descCom` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`idUser`,`idJeu`),
  KEY `FK_commentaires_jeu` (`idJeu`),
  CONSTRAINT `FK_commentaires_jeu` FOREIGN KEY (`idJeu`) REFERENCES `jeu` (`idJeu`),
  CONSTRAINT `FK_commentaires_personne` FOREIGN KEY (`idUser`) REFERENCES `personne` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table ludojava.commentaires : ~0 rows (environ)
DELETE FROM `commentaires`;

-- Listage de la structure de table ludojava. esttypo
CREATE TABLE IF NOT EXISTS `esttypo` (
  `jeu` int NOT NULL,
  `genre` int NOT NULL,
  PRIMARY KEY (`jeu`,`genre`),
  KEY `FK__typologie` (`genre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table ludojava.esttypo : ~0 rows (environ)
DELETE FROM `esttypo`;

-- Listage de la structure de table ludojava. jeu
CREATE TABLE IF NOT EXISTS `jeu` (
  `idJeu` int NOT NULL AUTO_INCREMENT,
  `nomJeu` varchar(50) NOT NULL DEFAULT '',
  `descJeu` varchar(50) NOT NULL DEFAULT '',
  `dispojeu` int NOT NULL DEFAULT '0',
  `conditionJeu` int NOT NULL,
  `nbJoueurs` int DEFAULT NULL,
  `ageMin` int DEFAULT NULL,
  `duréeJeu` time DEFAULT NULL,
  PRIMARY KEY (`idJeu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table ludojava.jeu : ~0 rows (environ)
DELETE FROM `jeu`;

-- Listage de la structure de table ludojava. personne
CREATE TABLE IF NOT EXISTS `personne` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `prenom` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `mail` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pwd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table ludojava.personne : ~0 rows (environ)
DELETE FROM `personne`;

-- Listage de la structure de table ludojava. typologie
CREATE TABLE IF NOT EXISTS `typologie` (
  `idGenre` int NOT NULL AUTO_INCREMENT,
  `nomGenre` varchar(30) NOT NULL,
  PRIMARY KEY (`idGenre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table ludojava.typologie : ~0 rows (environ)
DELETE FROM `typologie`;
INSERT INTO `typologie` (`idGenre`, `nomGenre`) VALUES
	(1, 'Jeu de Cartes'),
	(2, 'Jeu de Plateau');

-- Listage de la structure de table ludojava. user
CREATE TABLE IF NOT EXISTS `user` (
  `user` int NOT NULL,
  `adresseUser` int NOT NULL,
  PRIMARY KEY (`user`,`adresseUser`),
  CONSTRAINT `FK__user` FOREIGN KEY (`user`) REFERENCES `personne` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table ludojava.user : ~0 rows (environ)
DELETE FROM `user`;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
