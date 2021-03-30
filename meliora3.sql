-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 30 Mars 2021 à 04:23
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `meliora3`
--

-- --------------------------------------------------------

--
-- Structure de la table `aliment`
--

CREATE TABLE IF NOT EXISTS `aliment` (
  `id_aliment` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) NOT NULL,
  `recette` varchar(255) NOT NULL,
  `calorie` float NOT NULL,
  `gras` float NOT NULL,
  `carbs` float NOT NULL,
  `id_regime` int(11) NOT NULL,
  PRIMARY KEY (`id_aliment`),
  KEY `id_regime` (`id_regime`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `citations`
--

CREATE TABLE IF NOT EXISTS `citations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auteur` varchar(255) NOT NULL,
  `text` varchar(255) NOT NULL,
  `genre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `citations`
--

INSERT INTO `citations` (`id`, `auteur`, `text`, `genre`) VALUES
(1, 'T.K.V.Desikachar', 'Le succès du yoga ne réside pas dans notre habileté à performer des postures, mais comment elles changent notre façon de vivre notre vie', 'la vie'),
(2, 'Sri B.K.S Iyengar', 'le yoga c’est comme la musique. Le rythme du corps, la mélodie de l’esprit et l’harmony de l’âme créent la symphonie.', 'monde'),
(3, 'Rumi', 'Ta tâche n’est pas de chercher l’amour, mais simplement de chercher et trouver tous les obstacles que tu as construits contre l’amour.', 'philosophes'),
(4, 'Hatha Yoga Pradipika', 'Quand le souffle est instable, les pensées aussi errent. Mais quand le souffle est stable, l’esprit lui aussi devient calme. c’est ainsi que les yogis atteignent la longévité. Donc chacun devrait apprendre à contrôler sa respiration. ', 'ouvrages '),
(5, 'Yogi Bhajan', 'La paix réelle est inébranlable… la béatitude reste inchangée en cas de gain ou de perte.', 'sages '),
(6, 'Victor Hugo', 'Celui qui médite vit dans l''obscurité ; celui qui ne médite pas vit dans l''aveuglement. Nous n''avons que le choix du noir', 'tristesse');

-- --------------------------------------------------------

--
-- Structure de la table `coach`
--

CREATE TABLE IF NOT EXISTS `coach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `tel` int(8) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `rating` double NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `coach`
--

INSERT INTO `coach` (`id`, `nom`, `prenom`, `email`, `password`, `tel`, `adresse`, `image`, `rating`, `date`) VALUES
(1, 'ffvdvhhhhhhhh', 'dfdfd', 'dfdff', '$2y$13$e8aVOcgenjPK18uJ7Rc4VOor/sWc3bSDQ7/fG8mqMslAQmG4kkMIu', 1, 'fdddfdf', 'file:/C:/Users/asus/Downloads/126113679_720156881921914_6784085250357083015_n.jpg', 3.6666666666666665, '2012-03-15'),
(3, 'Ahmedgg', 'Gontara', 'agontara7@gmail.com', '$2y$13$jB8sFcgYiHipLnw/q75ZXeJjXxfjC19NkADSjCJnOsUoZY6cTjwWy', 24669000, 'mourouj 8', 'file:/C:/Users/asus/Downloads/detective.png', 0, '2009-03-13'),
(4, 'Ahmed', 'shili', 'ahmed@yahoo.com', '$2y$13$/U0yNzJttHLdLk0VDkcgzuxJv04fCr8Z09csQteAoMbomb9rT48Aa', 24645600, 'ghazela', 'file:/C:/Users/asus/Downloads/téléchargement.png', 0, '2009-03-13');

-- --------------------------------------------------------

--
-- Structure de la table `e_books`
--

CREATE TABLE IF NOT EXISTS `e_books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auteur` varchar(255) NOT NULL,
  `genre` varchar(255) NOT NULL,
  `favoris` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `evaluation` double NOT NULL,
  `id_c` int(11) NOT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_c` (`id_c`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `e_books`
--

INSERT INTO `e_books` (`id`, `auteur`, `genre`, `favoris`, `titre`, `evaluation`, `id_c`, `image`) VALUES
(1, 'Mathilde Piton', 'zen', 4, 'Yoga attitude', 2.16, 4, 'C:\\Users\\Kenza\\Desktop\\Meliora-java-melioraa2\\Meliora-java-meliora\\src\\images\\images\\Capture d’écran 2021-03-30 023327.png'),
(2, 'Andre Van Lysebeth', 'beaute', 2, 'J apprends le yoga', 0, 5, 'C:\\Users\\Kenza\\Desktop\\Meliora-java-melioraa2\\Meliora-java-meliora\\src\\images\\images\\Capture d’écran 2021-03-30 023749.png'),
(3, 'Emmanuel Carrere', 'Yoga', 4, 'Yoga', 4.29, 4, 'C:\\Users\\Kenza\\Desktop\\Meliora-java-melioraa2\\Meliora-java-meliora\\src\\images\\images\\Capture d’écran 2021-03-30 024010.png'),
(4, 'Sandrine Bridoux', 'exercice', 1, 'Mon cahier Yoga sculpt', 0, 1, 'C:\\Users\\Kenza\\Desktop\\Meliora-java-melioraa2\\Meliora-java-meliora\\src\\images\\images\\mon cahier yoga sculpt.png'),
(5, 'Elene du pon', 'yoga ', 3, 'du corps et de l''esprit', 2.49, 4, 'C:\\Users\\Kenza\\Desktop\\Meliora-java-melioraa2\\Meliora-java-meliora\\src\\images\\images\\Capture d’écran 2021-03-30 030940.png'),
(6, 'Martina Selway', 'petit', 3, 'Le yoga des petits', 2.83, 3, 'C:\\Users\\Kenza\\Desktop\\Meliora-java-melioraa2\\Meliora-java-meliora\\src\\images\\images\\Capture d’écran 2021-03-30 031237.png');

-- --------------------------------------------------------

--
-- Structure de la table `favoris`
--

CREATE TABLE IF NOT EXISTS `favoris` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_music` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  KEY `id_music` (`id_music`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `favoris`
--

INSERT INTO `favoris` (`id`, `id_user`, `id_music`) VALUES
(1, 1, 2),
(2, 1, 5);

-- --------------------------------------------------------

--
-- Structure de la table `health`
--

CREATE TABLE IF NOT EXISTS `health` (
  `id_health` int(11) NOT NULL AUTO_INCREMENT,
  `total_calories` int(11) NOT NULL,
  `total_carbs` int(11) NOT NULL,
  `total_gras` int(11) NOT NULL,
  `moy_tension` int(11) NOT NULL,
  `poids` int(11) NOT NULL,
  `hauteur` int(11) NOT NULL,
  `id_User` int(11) NOT NULL,
  PRIMARY KEY (`id_health`),
  KEY `fk_health` (`id_User`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `liste_taches`
--

CREATE TABLE IF NOT EXISTS `liste_taches` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_p` int(11) NOT NULL,
  `id_t` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  `nom_tache` varchar(255) DEFAULT NULL,
  `type_tache` varchar(255) DEFAULT NULL,
  `etat_du_tache` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_p` (`id_p`),
  KEY `id_t` (`id_t`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `liste_taches`
--

INSERT INTO `liste_taches` (`id`, `id_p`, `id_t`, `date`, `nom_tache`, `type_tache`, `etat_du_tache`) VALUES
(2, 3, NULL, '2021-03-12 00:00:00', '22', '22', 0),
(4, 14, 2, '2021-03-09 02:00:00', 'video du yoga', 'video', 0),
(5, 14, 2, '2021-03-09 02:00:00', 'video du yoga', 'video', 0),
(6, 14, 2, '2021-03-09 02:00:00', 'video du yoga', 'video', 0),
(7, 15, 2, '2021-03-02 02:00:00', 'video du yoga', 'video', 0),
(8, 15, 2, '2021-03-02 02:00:00', 'video du yoga', 'video', 0),
(9, 16, 2, '2021-03-01 02:00:00', 'video du yoga', 'video', 0),
(10, 16, 2, '2021-03-01 02:00:00', 'video du yoga', 'video', 0);

-- --------------------------------------------------------

--
-- Structure de la table `musique`
--

CREATE TABLE IF NOT EXISTS `musique` (
  `nombre` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(50) NOT NULL,
  `genre` varchar(20) NOT NULL,
  `Artiste` varchar(50) NOT NULL,
  `MusicPath` varchar(250) NOT NULL,
  `image` varchar(250) DEFAULT 'noImage',
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `musique`
--

INSERT INTO `musique` (`nombre`, `titre`, `genre`, `Artiste`, `MusicPath`, `image`) VALUES
(1, 'Demons', 'Calm', 'Imagine Dragons', 'file:\\C:\\Users\\asus\\Desktop\\projet\\music\\Demons--Imagine-dragons.mp3', 'file:\\C:\\Users\\asus\\Desktop\\projet\\music\\AlbumImg\\Demons--Imagine-dragons.jpg'),
(2, 'I don''t  wanna be you anymore', 'Pop', 'Bellie Eilish', 'file:\\C:\\Users\\asus\\Desktop\\projet\\music\\I-dont-wanna-be-you-anymore--Bellie-Eillish.mp3', 'file:\\C:\\Users\\asus\\Desktop\\projet\\music\\AlbumImg\\I-dont-wanna-be-you-anymore--Bellie-Eillish.jpg'),
(3, 'I hate you i love you', 'Sad', 'Gnash', 'file:\\C:\\Users\\asus\\Desktop\\projet\\music\\I-hate-u-i-love-u--Gnash.mp3', 'file:/C:/Users/asus/Desktop/projet/music/AlbumImg/I-hate-u-i-love-u--Gnash.jpg'),
(4, 'Summertime sadness', 'Sad', 'Lana del rey', 'file:/C:/Users/asus/Desktop/projet/music/Summertime-sadness--Lana-del-rey.mp3', 'file:/C:/Users/asus/Desktop/projet/music/AlbumImg/Summertime-sadness--Lana-del-rey.jpg'),
(5, 'PhotoGraph ( Cover )', 'blabla', 'Ed Sheeran', 'file:/C:/Users/asus/Desktop/projet/music/Photograph--Ed-sheeran.mp3', 'file:/C:/Users/asus/Desktop/projet/music/AlbumImg/Photograph--Ed-sheeran.jpg'),
(6, 'On our knees', 'I don''t know', 'Konoba', 'file:/C:/Users/asus/Desktop/projet/music/On-our-knees--Konoba.mp3', 'file:/C:/Users/asus/Desktop/projet/music/AlbumImg/On-our-knees--Konoba.jpg'),
(7, 'Shallow', 'ila5', 'Lady Gaga', 'file:/C:/Users/asus/Desktop/projet/music/Shallow--lady-gaga-bradley-cooper.mp3', 'file:/C:/Users/asus/Desktop/projet/music/AlbumImg/Shallow--lady-gaga-bradley-cooper.jpg'),
(8, 'Simply falling', 'falling', 'Lyeoka', 'file:/C:/Users/asus/Desktop/projet/music/Simply-falling--Iyeoka.mp3', 'file:/C:/Users/asus/Desktop/projet/music/AlbumImg/Simply-falling--Iyeoka.jpg'),
(9, 'fff', 'ggg', 'KKK', 'file:/C:/Users/asus/Desktop/projet/music/On-our-knees--Konoba.mp3', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `planning`
--

CREATE TABLE IF NOT EXISTS `planning` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_U` int(11) NOT NULL,
  `liker` int(11) DEFAULT '0',
  `disliker` int(11) DEFAULT '0',
  `id_C` int(11) DEFAULT NULL,
  `date_creation` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom_p` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_U` (`id_U`),
  KEY `id_C` (`id_C`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Contenu de la table `planning`
--

INSERT INTO `planning` (`id`, `id_U`, `liker`, `disliker`, `id_C`, `date_creation`, `description`, `nom_p`) VALUES
(3, 6, 0, 0, 1, '2021-03-03', NULL, NULL),
(4, 1, 0, 0, NULL, '2021-03-28', 'mm', NULL),
(5, 1, 0, 0, NULL, '2021-03-28', 'hh', NULL),
(6, 1, 0, 0, NULL, '2021-03-28', 'z', NULL),
(7, 1, 0, 0, NULL, '2021-03-28', 'z', NULL),
(8, 1, 0, 0, NULL, '2021-03-28', 'ss', NULL),
(10, 1, 0, 0, NULL, '2021-03-28', 'cc', NULL),
(11, 1, 0, 0, NULL, '2021-03-28', 'a', NULL),
(12, 1, 0, 0, NULL, '2021-03-28', 'c', NULL),
(13, 1, 0, 0, NULL, '2021-03-28', 'aa', NULL),
(14, 1, 0, 0, NULL, '2021-03-28', 'b', NULL),
(15, 1, 0, 0, NULL, '2021-03-28', 's', NULL),
(16, 1, 0, 0, NULL, '2021-03-28', 'a', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `planning_user`
--

CREATE TABLE IF NOT EXISTS `planning_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_p` int(11) NOT NULL,
  `id_u` int(11) NOT NULL,
  `date_ajout` datetime DEFAULT CURRENT_TIMESTAMP,
  `date_suppr` date DEFAULT NULL,
  `nom_planning` varchar(255) DEFAULT NULL,
  `nb_tache` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_p` (`id_p`),
  KEY `id_u` (`id_u`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Contenu de la table `planning_user`
--

INSERT INTO `planning_user` (`id`, `id_p`, `id_u`, `date_ajout`, `date_suppr`, `nom_planning`, `nb_tache`) VALUES
(6, 3, 1, '2021-03-27 00:00:00', NULL, 's', NULL),
(7, 3, 1, '2021-03-27 00:00:00', NULL, 's', NULL),
(10, 6, 1, '2021-03-28 00:00:00', NULL, 'z', NULL),
(14, 10, 1, '2021-03-28 00:00:00', NULL, 'cc', NULL),
(15, 11, 1, '2021-03-28 00:00:00', NULL, 'a', NULL),
(16, 12, 1, '2021-03-28 00:00:00', NULL, 'c', NULL),
(17, 3, 1, '2021-03-28 00:00:00', NULL, 'test', NULL),
(18, 13, 1, '2021-03-28 00:00:00', NULL, 'aa', NULL),
(19, 14, 1, '2021-03-28 00:00:00', NULL, 'b', NULL),
(20, 15, 1, '2021-03-28 00:00:00', NULL, 's', NULL),
(21, 16, 1, '2021-03-28 00:00:00', NULL, 'a', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE IF NOT EXISTS `reclamation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sujetReclamation` varchar(255) NOT NULL,
  `statu` varchar(255) NOT NULL,
  `descriptionReclamation` varchar(255) NOT NULL,
  `dateReclamation` date NOT NULL,
  `id_client` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_client` (`id_client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Structure de la table `regime`
--

CREATE TABLE IF NOT EXISTS `regime` (
  `id_regime` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `duration` int(11) NOT NULL,
  `id_User` int(11) NOT NULL,
  PRIMARY KEY (`id_regime`),
  KEY `id_User` (`id_User`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `review`
--

CREATE TABLE IF NOT EXISTS `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_coach` int(11) NOT NULL,
  `etat` varchar(20) NOT NULL,
  `rating` double NOT NULL,
  `comment` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idcoach` (`id_coach`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `review`
--

INSERT INTO `review` (`id`, `id_coach`, `etat`, `rating`, `comment`) VALUES
(1, 1, 'Satisfait', 4, 'raby m3ak'),
(2, 1, 'Normal', 3, 'kooz'),
(3, 1, 'Médiocre', 1, 'z'),
(4, 1, 'Satisfait', 5, 'zffffff'),
(5, 1, 'Normal', 2, 'ppp'),
(6, 1, 'Satisfait', 5, 'ffffffsqdqdsq'),
(7, 1, 'Satisfait', 5, 'bbbbbb'),
(8, 1, 'Satisfait', 5, 'jjjjjj'),
(9, 1, 'Normal', 3, 'xx'),
(10, 1, 'Normal', 2, 'dd'),
(11, 1, 'Satisfait', 5, 'eeeeeeeeeeeeeeeeeee'),
(12, 1, 'Satisfait', 4, 'ddddd');

-- --------------------------------------------------------

--
-- Structure de la table `tache`
--

CREATE TABLE IF NOT EXISTS `tache` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_tache` enum('video','musique','citation','e_books') NOT NULL,
  `nom_tache` varchar(255) NOT NULL,
  `id_v` int(11) DEFAULT NULL,
  `id_m` int(11) DEFAULT NULL,
  `id_e` int(11) DEFAULT NULL,
  `id_c` int(11) DEFAULT NULL,
  `idnonnull` int(11) NOT NULL,
  `like` int(11) DEFAULT '0',
  `dislike` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `tache_citation` (`id_c`),
  KEY `tache_ebook` (`id_e`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `tache`
--

INSERT INTO `tache` (`id`, `type_tache`, `nom_tache`, `id_v`, `id_m`, `id_e`, `id_c`, `idnonnull`, `like`, `dislike`) VALUES
(2, 'video', 'video du yoga', 1, NULL, NULL, NULL, 1, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `prenom` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateNai` date DEFAULT NULL,
  `adresse` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=20 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `password`, `roles`, `prenom`, `dateNai`, `adresse`, `tel`) VALUES
(1, 'mimi', 'khaled.abdelmoumen@esprit.tn', '$2y$13$auaSgCK3Ydm4S5TYeZj4rehpGq61ccxFeJ8dHdZGgD24AvlKAGVW6', 'Client', NULL, '2021-02-02', 'mimi', '5487354'),
(5, 'kenza', 'kenza@gmail.com', '$2y$13$ivS2EWHBs93Y9VjboGHjZOYGbwel4xk9BniH1SGkroUeKwcLGS/WC', 'Client', NULL, '2021-02-02', 'kram', '25444777'),
(6, 'khaled', 'khaled@gmail.com', '$2y$13$H7zhJmobuWyC2lexu6LHveRlGK7Bmj1txCBItCFIHdufFhEYFxMhu', 'Admin', NULL, '2021-02-18', 'rades', '25444777'),
(7, 'ahmed', 'ahmed@gmail.com', '$2y$13$j8NAWMygyosLT.aPq/vPaetrC5.DwGBc09pSOIyreQh3gmoflczdu', 'Coach', NULL, '2021-02-17', 'ariena soghra', '55789456'),
(8, 'ras', 'ras@gmail.com', '$2y$13$rNIUjHQxRV6bJBero6jHguOj4G3bs/npwKZQfRWdxWH0uCm79zlKS', 'Coach', NULL, '2021-02-09', 'ras', '48653438'),
(9, 'firas', 'firas@gmail.com', '$2y$13$eizUHML9DSbNPGyrzpV39ePGsgK/q2QNO.dboCr/kbafOQaTHEKZy', 'Coach', NULL, '2021-03-03', 'rades', '55888999'),
(10, 'amira', 'amira@gmail.com', '$2y$13$xNHJNU.Pi1hqnwqGI7Cate4Q8rrKNqjimhs7.y4k3m8DHFIdmLqoC', 'Coach', NULL, '2021-01-06', 'menzah', '55777444'),
(11, 'safa', 'safa@gmail.com', '$2y$13$8i6CgijYETRHbTeyrJ87gOOKtM5xUEB5C0qm9yEQx2pRFJUDtjpZ6', 'Admin', NULL, '2021-01-06', 'hamlenf', '55888999'),
(12, 'flen', 'flen@gmail.com', '$2y$13$rtxuK0TpuJBLjrHcOxwptO7QPDWYjhvGS1ZuBai.9bZZ.EvtFv/iW', 'Admin', NULL, '2021-03-03', 'ariena', '777888899'),
(13, 'mohamed', 'mohamed@gmail.com', '$2y$13$OmP6msdygD/ZXoUCvP7gR.zpyUP4VSn2gkem4O84I2amMgQv9bNi6', 'Client', NULL, '2021-02-02', 'rades', '88555444'),
(14, 'zaineb', 'zaineb@gmail.com', '$2y$13$82Ld3pm.kTY/kUZthuW5UuK6xs7pT9FC6daDCAGcv2nCqc6EBKOFK', 'Client', NULL, '2021-03-02', 'mourouj', '55888999'),
(15, 'foulen ben foulen', 'foulen@gmail.com', '$2y$13$jZdHxnqohSfyMao6o3xJiuph.0PeCPHYfk1ynOF/KHrPlrslf4FrK', 'Coach', NULL, '2021-03-02', 'zahra', '20589661'),
(16, 'aaa', 'aaa@gmail.com', '$2y$13$uADKZi6G3KQrwOOXI/PxK.zCHWtGxdK2e5QTO5K3Yrp0CshTbX3OG', 'Client', NULL, '2021-03-01', 'aaa', '6448885'),
(17, 'bbb', 'b@gmail.com', '$2y$13$aCohjVayCHUgc9pV131fxuymuV49NNo6JceSGSKgRxvCCzK7Zy.Ke', 'Client', NULL, '2021-03-01', 'djshjs', '54545445'),
(18, 'test', 'test@gmail.com', '$2y$13$cplx4AzsnbeL628kKviIauhPb1gP3UI9O0ZOuH5b1YYshnm47gKCy', 'Client', NULL, '2021-03-02', 'test', '22558996'),
(19, 'testest', 'testest@gmail.com', '$2y$13$W9IqLmAnlUKFlT9tfQp6vuOzGvgl35/ff5Ocdq8JZTT/k7hqslNfm', 'Client', NULL, '2021-03-03', 'jsp', '22555888');

-- --------------------------------------------------------

--
-- Structure de la table `video`
--

CREATE TABLE IF NOT EXISTS `video` (
  `id_v` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(20) NOT NULL,
  `genre` varchar(20) NOT NULL,
  `VideoPath` varchar(500) NOT NULL,
  `nb_likes` int(30) NOT NULL,
  `nb_dislikes` int(30) NOT NULL,
  `mailSent` int(30) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_v`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `video`
--

INSERT INTO `video` (`id_v`, `titre`, `genre`, `VideoPath`, `nb_likes`, `nb_dislikes`, `mailSent`) VALUES
(1, 'mle', 'zeza', 'file:/C:/Users/SAFA/Downloads/%5BEgyBest%5D.Al.Hareth.2020.WEB-DL.720p.x264.mp4', 55, 66, 1),
(2, 'mle', 'zeza', 'file:/C:/Users/SAFA/Downloads/EgyBest.Peaky.Blinders.S05E06.WEB-DL.720p.x264.mp4', 55, 66, 1),
(3, 'dddd', 'd', 'file:/C:/Users/SAFA/Downloads/Brown%20and%20Green%20Scrapbook%20Meditation%20Healthcare%20Medical%20Presentation.mp4', 15, 10, 0),
(4, 'FFF', 'dFFF', 'file:/C:/Users/SAFA/Downloads/Brown%20and%20Green%20Scrapbook%20Meditation%20Healthcare%20Medical%20Presentation.mp4', 10, 15, 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `aliment`
--
ALTER TABLE `aliment`
  ADD CONSTRAINT `aliment_ibfk_1` FOREIGN KEY (`id_regime`) REFERENCES `regime` (`id_regime`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `e_books`
--
ALTER TABLE `e_books`
  ADD CONSTRAINT `e_books_ibfk_1` FOREIGN KEY (`id_c`) REFERENCES `citations` (`id`);

--
-- Contraintes pour la table `favoris`
--
ALTER TABLE `favoris`
  ADD CONSTRAINT `fk_idmusic` FOREIGN KEY (`id_music`) REFERENCES `musique` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_iduser` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `health`
--
ALTER TABLE `health`
  ADD CONSTRAINT `fk_health` FOREIGN KEY (`id_User`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `liste_taches`
--
ALTER TABLE `liste_taches`
  ADD CONSTRAINT `liste_taches_ibfk_1` FOREIGN KEY (`id_p`) REFERENCES `planning` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `liste_taches_ibfk_2` FOREIGN KEY (`id_t`) REFERENCES `tache` (`id`);

--
-- Contraintes pour la table `planning`
--
ALTER TABLE `planning`
  ADD CONSTRAINT `planning_ibfk_1` FOREIGN KEY (`id_U`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `planning_ibfk_2` FOREIGN KEY (`id_C`) REFERENCES `coach` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `planning_user`
--
ALTER TABLE `planning_user`
  ADD CONSTRAINT `planning_user_ibfk_1` FOREIGN KEY (`id_p`) REFERENCES `planning` (`id`),
  ADD CONSTRAINT `planning_user_ibfk_2` FOREIGN KEY (`id_u`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `reclamation_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `regime`
--
ALTER TABLE `regime`
  ADD CONSTRAINT `regime_ibfk_1` FOREIGN KEY (`id_User`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `fk_idcoach` FOREIGN KEY (`id_coach`) REFERENCES `coach` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `tache`
--
ALTER TABLE `tache`
  ADD CONSTRAINT `tache_citation` FOREIGN KEY (`id_c`) REFERENCES `citations` (`id`),
  ADD CONSTRAINT `tache_ebook` FOREIGN KEY (`id_e`) REFERENCES `e_books` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
