-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2024 at 02:31 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shopgame`
--

-- --------------------------------------------------------

--
-- Table structure for table `accountgame`
--

CREATE TABLE `accountgame` (
  `IngameId` int(11) NOT NULL,
  `UserNameGame` varchar(50) DEFAULT NULL,
  `PasswordGame` varchar(50) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL,
  `GameId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `accountgame`
--

INSERT INTO `accountgame` (`IngameId`, `UserNameGame`, `PasswordGame`, `Price`, `Status`, `GameId`) VALUES
(1, 'phamngochuy1', 'Password1', 50, 1, 1),
(2, 'huyngocpham3', 'Password2', 30, 0, 2),
(3, 'User3', 'Password3', 20, 0, 1),
(4, 'User4', 'Password4', 40, 1, 3),
(5, 'User5', 'Password5', 25, 0, 2),
(6, 'User6', 'Password6', 50, 1, 1),
(7, 'User7', 'Password7', 30, 1, 2),
(8, 'User8', 'Password8', 20, 0, 1),
(9, 'hoasinguoikinh123', 'Password9', 40, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `kindofgame`
--

CREATE TABLE `kindofgame` (
  `GameId` int(11) NOT NULL,
  `NameGame` varchar(100) DEFAULT NULL,
  `Platform` varchar(50) DEFAULT NULL,
  `Genre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `kindofgame`
--

INSERT INTO `kindofgame` (`GameId`, `NameGame`, `Platform`, `Genre`) VALUES
(1, 'LeaguageOfLegends', 'AllPlatform', 'Tactic'),
(2, 'GTA5', 'Windown', 'Action'),
(3, 'ApexLegends', 'Platform1', 'Genre3'),
(4, 'Game4', 'Platform3', 'Genre2'),
(5, 'Game5', 'Platform2', 'Genre1'),
(6, 'Game6', 'Platform1', 'Genre3'),
(7, 'Game7', 'Platform2', 'Genre2'),
(8, 'Game8', 'Platform3', 'Genre1'),
(9, 'Game9', 'Platform1', 'Genre3'),
(10, 'Dota2', 'IOS', 'Romance');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `TransactionID` int(11) NOT NULL,
  `IngameID` int(11) DEFAULT NULL,
  `TransactionDate` date DEFAULT NULL,
  `Total` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`TransactionID`, `IngameID`, `TransactionDate`, `Total`, `UserID`) VALUES
(1, 1, '2024-05-18', 50, 1),
(2, 2, '2024-05-18', 30, 2),
(3, 3, '2024-05-18', 20, 3),
(6, 1, '3924-06-26', 7, 12);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` int(11) NOT NULL,
  `UserName` varchar(50) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `Role` enum('admin','customer') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `UserName`, `Email`, `Password`, `Role`) VALUES
(1, 'admin', 'admin@example.com', '123', 'admin'),
(2, 'user', 'user1@example.com', '123', 'customer'),
(3, 'User2', 'user2@example.com', 'user2password', 'customer'),
(5, 'User4', 'user4@example.com', 'user4password', 'customer'),
(6, 'User5', 'user5@example.com', 'user5password', 'customer'),
(7, 'User6', 'user6@example.com', 'user6password', 'customer'),
(8, 'User7', 'user7@example.com', 'user7password', 'customer'),
(9, 'User8', 'user8@example.com', 'user8password', 'customer'),
(10, 'User9', 'user9@example.com', 'user9password', 'customer'),
(12, 'Huy1', 'Huy1@gmail.com', '1234', 'customer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accountgame`
--
ALTER TABLE `accountgame`
  ADD PRIMARY KEY (`IngameId`),
  ADD KEY `GameId` (`GameId`);

--
-- Indexes for table `kindofgame`
--
ALTER TABLE `kindofgame`
  ADD PRIMARY KEY (`GameId`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`TransactionID`),
  ADD KEY `IngameID` (`IngameID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `TransactionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accountgame`
--
ALTER TABLE `accountgame`
  ADD CONSTRAINT `accountgame_ibfk_1` FOREIGN KEY (`GameId`) REFERENCES `kindofgame` (`GameId`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`IngameID`) REFERENCES `accountgame` (`IngameId`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
