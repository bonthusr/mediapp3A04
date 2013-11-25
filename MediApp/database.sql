-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 19, 2013 at 11:52 PM
-- Server version: 5.6.12
-- PHP Version: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `database`
--
CREATE DATABASE IF NOT EXISTS `database` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `database`;

-- --------------------------------------------------------

--
-- Table structure for table `apptSchedule`
--

CREATE TABLE IF NOT EXISTS `apptSchedule` (
  `doctorID` int(20) NOT NULL,
  `patientID` int(20) NOT NULL,
  `apptDay` int(2) NOT NULL,
  `apptMonth` int(2) NOT NULL,
  `apptYear` int(4) NOT NULL,
  `apptTime` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `employeeInfo`
--

CREATE TABLE IF NOT EXISTS `employeeInfo` (
  `priority` int(1) NOT NULL,
  `employeeID` int(20) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(30) NOT NULL,
  `province` varchar(30) NOT NULL,
  `postalCode` varchar(6) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `birthDate` date NOT NULL,
  `location` varchar(30) NOT NULL,
  `startDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `medicalRecord`
--

CREATE TABLE IF NOT EXISTS `medicalRecord` (
  `idNumber` int(20) NOT NULL,
  `physicianName` varchar(30) NOT NULL,
  `physicianPhone` int(10) NOT NULL,
  `weight` int(4) NOT NULL,
  `height` int(3) NOT NULL,
  `bloodType` varchar(10) NOT NULL,
  `allergies` varchar(70) NOT NULL,
  `currentMeds` varchar(70) NOT NULL,
  `recentSickness` varchar(70) NOT NULL,
  `asthma` int(1) NOT NULL,
  `diabetes` int(1) NOT NULL,
  `diseaseType` varchar(30) NOT NULL,
  `other` varchar(50) NOT NULL,
  `fluVac` date NOT NULL,
  `tetanusVac` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `patientInfo`
--

CREATE TABLE IF NOT EXISTS `patientInfo` (
  `patientID` int(20) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `birthDate` date NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(30) NOT NULL,
  `province` varchar(30) NOT NULL,
  `postalCode` varchar(7) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `homePhone` varchar(10) NOT NULL,
  `cellPhone` varchar(10) NOT NULL,
  `healthCard` varchar(15) NOT NULL,
  `sinCard` int(9) NOT NULL,
  `emergName` varchar(30) NOT NULL,
  `emergNumber` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
