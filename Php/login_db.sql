-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 05, 2017 at 09:28 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `login_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `place`
--

CREATE TABLE IF NOT EXISTS `place` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `place`
--

INSERT INTO `place` (`ID`, `NAME`) VALUES
(1, 'Neyveli'),
(2, 'Coimbatore'),
(3, 'Chennai'),
(4, '');

-- --------------------------------------------------------

--
-- Table structure for table `user_credentials`
--

CREATE TABLE IF NOT EXISTS `user_credentials` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(40) NOT NULL,
  `UNAME` varchar(15) NOT NULL,
  `PWD` varchar(15) NOT NULL,
  `EMAIL` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNAME` (`UNAME`,`EMAIL`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `user_credentials`
--

INSERT INTO `user_credentials` (`ID`, `NAME`, `UNAME`, `PWD`, `EMAIL`) VALUES
(1, 'Karthik Raj', 'rkarthikraj', '12345', 'rkarthykraj@gmail.com'),
(2, 'Vishnu', 'vishnugt', '12345', 'vishnugt@gmail.com'),
(7, 'Surendiran', 'surendiran', '12345', 'surendiran@gmail.com'),
(8, 'Monu', 'monu', '12345', 'monu@gmail.com');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
