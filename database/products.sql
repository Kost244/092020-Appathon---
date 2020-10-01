-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 01, 2020 at 03:21 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `products`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderid` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `orders` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`orders`)),
  `total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderid`, `username`, `orders`, `total`) VALUES
(16, 'kosto', '[1, 2, 3]', 1408),
(17, 'kosto', '[3, 1, 5]', 2678.4),
(18, 'kosto', '[1, 2, 3]', 1792),
(19, 'kosto', '[2, 3]', 1091.2),
(20, 'kosto', '[5, 4]', 3720),
(21, 'kosto', '[2, 3]', 1364),
(22, 'kosto', '[1, 2]', 1232),
(23, 'kosto2', '[3, 2]', 1364),
(24, 'kosto', '[1, 2, 3, 4, 5]', 5704),
(25, 'kosto', '[2, 1, 3]', 1984),
(26, 'kosto', '[3, 4, 2]', 2604),
(27, 'kosto', '[1, 3, 4]', 2108),
(28, 'kosto', '[1, 2, 3]', 1984),
(29, 'kosto', '[1, 2, 3]', 1984),
(30, 'kostgeorge', '[2, 4, 5]', 4836),
(31, 'kostgeorge', '[1, 4, 2]', 2976),
(32, 'kostgeorge', '[1, 2]', 1388.8),
(33, 'kosto', '[1, 2]', 1736),
(34, 'kosto', '[2, 3, 4]', 2604),
(35, 'kosto', '[1]', 620),
(36, 'kosto', '[3, 4]', 1488),
(37, 'kosto', '[2, 3]', 1364),
(38, 'kosto', '[2]', 1116),
(39, 'kosto', '[4, 1, 2, 3]', 2496),
(40, 'kosto', '[1, 3, 5, 2, 4]', 5704),
(41, 'kosto', '[1]', 620),
(42, 'kosto', '[2, 3]', 1364),
(43, 'kosto', '[1, 2, 3]', 1792),
(44, 'kosto', '[3, 5]', 1936),
(45, 'kosto', '[2, 3]', 1364),
(46, 'kosto', '[2, 3, 4]', 2604),
(47, 'kosto', '[3, 1]', 868),
(48, 'kosto', '[2, 3, 4, 5]', 4510),
(49, 'Koooostttooppp', '[1, 2, 3]', 1408),
(50, 'kosto', '[1, 2]', 1876),
(51, 'asda', '[1, 2, 3]', 1408),
(52, 'kosto', '[1, 2, 3]', 2144),
(53, 'kosto', '[1, 2, 3]', 1536),
(54, 'kosto', '[1, 1, 2]', 1736),
(55, 'kosto', '[1, 2, 3, 4]', 2912),
(56, 'kosto', '[1, 2]', 1736),
(57, 'kosto', '[1, 2]', 1736),
(58, 'kosto', '[1, 3, 4]', 1496),
(59, 'kosto', '[1, 2]', 1344);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productid` int(11) NOT NULL,
  `productname` varchar(50) NOT NULL,
  `notaxprice` float NOT NULL,
  `defaulttax` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productid`, `productname`, `notaxprice`, `defaulttax`) VALUES
(1, 'RTX3080', 500, 0.24),
(2, 'RTX3090', 900, 0.24),
(3, 'RTX3060', 200, 0.24),
(4, 'laptop1', 1000, 0.24),
(5, 'laptop2', 2000, 0.24);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userid` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `birth` date NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userid`, `username`, `birth`, `fullname`, `password`) VALUES
(8, 'kosto', '2020-09-14', 'Kostopoulossss', 'aliwsuefhoqieurgcvhnboaesifugh'),
(9, 'george', '2020-10-02', 'George Kost', '123'),
(10, 'kosto2', '2020-09-21', 'Kostop 2', 'pass'),
(11, 'kostgeorge', '2020-09-08', 'ksajdflas', 'pass'),
(12, 'Koooostttooppp', '2020-09-29', 'ssshhhss', '123'),
(13, 'asda', '2020-08-30', 'Asda', 'password1234567890');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderid`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `productid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
