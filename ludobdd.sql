-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
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


-- Dumping database structure for ludojava
CREATE DATABASE IF NOT EXISTS `ludojava` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ludojava`;

-- Dumping structure for table ludojava.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `idPers` int NOT NULL,
  `estAutorisé` int NOT NULL,
  PRIMARY KEY (`idPers`,`estAutorisé`),
  KEY `FK_admin_autorisations` (`estAutorisé`),
  CONSTRAINT `FK_admin_autorisations` FOREIGN KEY (`estAutorisé`) REFERENCES `autorisations` (`idAuto`),
  CONSTRAINT `FK_admin_personne` FOREIGN KEY (`idPers`) REFERENCES `personne` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ludojava.admin: ~4 rows (approximately)
DELETE FROM `admin`;
INSERT INTO `admin` (`idPers`, `estAutorisé`) VALUES
	(1, 1),
	(1, 2),
	(2, 2),
	(1, 3);

-- Dumping structure for table ludojava.autorisations
CREATE TABLE IF NOT EXISTS `autorisations` (
  `idAuto` int NOT NULL AUTO_INCREMENT,
  `nomAuto` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`idAuto`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ludojava.autorisations: ~3 rows (approximately)
DELETE FROM `autorisations`;
INSERT INTO `autorisations` (`idAuto`, `nomAuto`) VALUES
	(1, 'Ajout'),
	(2, 'Modification'),
	(3, 'Suppression');

-- Dumping structure for table ludojava.estemprunte
CREATE TABLE IF NOT EXISTS `estemprunte` (
  `PretUser` int NOT NULL,
  `PretJeu` int NOT NULL,
  PRIMARY KEY (`PretJeu`,`PretUser`) USING BTREE,
  KEY `FK__personne` (`PretUser`) USING BTREE,
  CONSTRAINT `FK__jeu` FOREIGN KEY (`PretJeu`) REFERENCES `jeu` (`idJeu`),
  CONSTRAINT `FK__personne` FOREIGN KEY (`PretUser`) REFERENCES `personne` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ludojava.estemprunte: ~0 rows (approximately)
DELETE FROM `estemprunte`;

-- Dumping structure for table ludojava.etatjeu
CREATE TABLE IF NOT EXISTS `etatjeu` (
  `idEtat` int NOT NULL AUTO_INCREMENT,
  `Etat` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idEtat`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ludojava.etatjeu: ~10 rows (approximately)
DELETE FROM `etatjeu`;
INSERT INTO `etatjeu` (`idEtat`, `Etat`) VALUES
	(1, 'Neuf'),
	(2, 'Comme neuf'),
	(3, 'Excellent'),
	(4, 'Très bien'),
	(5, 'Bien'),
	(6, 'Moyen'),
	(7, 'Peu bien'),
	(8, 'Mauvais'),
	(9, 'Très mauvais'),
	(10, 'Inutilisable');

-- Dumping structure for table ludojava.jeu
CREATE TABLE IF NOT EXISTS `jeu` (
  `idJeu` int NOT NULL AUTO_INCREMENT,
  `nomJeu` varchar(50) NOT NULL DEFAULT '',
  `descJeu` varchar(700) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `dispojeu` int NOT NULL DEFAULT '0',
  `conditionJeu` int NOT NULL,
  `nbJoueurs` varchar(8) DEFAULT NULL,
  `ageMin` int DEFAULT NULL,
  `duréeJeu` varchar(48) DEFAULT NULL,
  PRIMARY KEY (`idJeu`),
  KEY `FK_jeu_etatjeu` (`conditionJeu`),
  CONSTRAINT `FK_jeu_etatjeu` FOREIGN KEY (`conditionJeu`) REFERENCES `etatjeu` (`idEtat`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ludojava.jeu: ~7 rows (approximately)
DELETE FROM `jeu`;
INSERT INTO `jeu` (`idJeu`, `nomJeu`, `descJeu`, `dispojeu`, `conditionJeu`, `nbJoueurs`, `ageMin`, `duréeJeu`) VALUES
	(1, 'Monopoly', 'Achetez, vendez et négociez pour gagner la partie. Attention à la faillite, à vous de bien choisir les rues pour ruiner vos adversaires et être le dernier sur le plateau de jeu ! Monopoly, le plus célèbre des jeux de société!', 1, 1, '2-8', 8, '1h'),
	(2, 'Uno', 'Le célèbre jeu de défausse pour jouer en famille. Chaque joueur reçoit 7 cartes et on en place une face visible au centre de la table. Chaque joueur à son tour se défausse d\'une carte de sa main qui a la même valeur, la même couleur ou le même symbole que la carte visible sur la table. S\'il ne peut pas joueur il pioche. Quand un joueur n\'a plus de cartes la partie s\'arrête. Les cartes encore en main font marquer des points négatifs. Les cartes spéciales viennent pimenter le jeu.', 1, 2, '2-10', 7, '30m'),
	(3, 'Dobble', 'Dobble est un jeu d’observation et de rapidité pour toute la famille. Le but ? Il existe un seul et unique symbole commun entre chacune des cartes. Sois le premier à le répérer et à le nommer pour remporter la carte.', 0, 3, '2-8', 6, '5 à 15m'),
	(4, 'Trivial Pursuit', 'Jeu très connu depuis plusieurs générations, le Trivial Pursuit se hisse au rang des jeux de société les plus populaires ! Le principe de ce jeu de plateau est simple : il suffit de répondre correctement à 12 questions de culture générale pour remporter la partie. Les questions répondent à plusieurs thèmes (célébrité, divertissement, culture, géographie, histoire, cinéma) chacun modélisé par une couleur. Chaque joueur doit remplir son camembert au fur et à mesure qu’il répond aux bonnes questions, et doit avoir à la fin 1 triangle de chaque couleur dans son camembert.', 0, 8, '2-6', 8, '1h'),
	(5, 'Mille Bornes', 'L’indémodable Mille Bornes, la version phare de la gamme, qui se joue de génération en génération avec la même passion.', 0, 3, '2-8', 6, '30m'),
	(6, 'Risk', 'Menez vos régiments à la victoire. Prenez des décisions pour conquérir le monde. Le monde appartient aux audacieux mais l\'êtes-vous assez pour gagner ? ', 0, 2, '2-5', 10, '1h'),
	(7, 'Cluedo', 'Samuel Lenoir, milliardaire très puissant, est assassiné ! Seuls les 6 invités se trouvaient dans la maison au moment du drame… C’est maintenant à vous de résoudre l’énigme ! Elaborez vos hypothèses en posant des questions à vos adversaires. Qui l’a tué ? Dans quelle pièce ? Et avec quelle arme ? Vous avez trouvez ?', 0, 3, '2-6', 8, '45m');

-- Dumping structure for table ludojava.personne
CREATE TABLE IF NOT EXISTS `personne` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `prenom` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `mail` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pwd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ludojava.personne: ~2 rows (approximately)
DELETE FROM `personne`;
INSERT INTO `personne` (`id`, `nom`, `prenom`, `mail`, `pwd`) VALUES
	(1, NULL, NULL, 'admin@admin.fr', 'admin'),
	(2, 'test', 'test', 'test@test.fr', 'test');

-- Dumping structure for table ludojava.user
CREATE TABLE IF NOT EXISTS `user` (
  `user` int NOT NULL,
  `adresseUser` int NOT NULL,
  PRIMARY KEY (`user`,`adresseUser`),
  CONSTRAINT `FK__user` FOREIGN KEY (`user`) REFERENCES `personne` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ludojava.user: ~0 rows (approximately)
DELETE FROM `user`;

-- Dumping structure for trigger ludojava.estemprunte_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `estemprunte_before_insert` BEFORE INSERT ON `estemprunte` FOR EACH ROW BEGIN
	SET @opt = NEW.PretJeu;
	SET @req = (
	SELECT dispoJeu FROM jeu
	WHERE idJeu = @opt
	);
	
	IF @req = 0 THEN
		SIGNAL SQLSTATE '40004' set message_text = "Erreur: jeu n'est pas disponible";
	END IF
;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger ludojava.jeu_inutilisable
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `jeu_inutilisable` BEFORE UPDATE ON `jeu` FOR EACH ROW BEGIN
	IF NEW.conditionJeu >= 10 THEN
		SET NEW.dispoJeu = 0;
	END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
