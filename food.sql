-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 24, 2022 at 04:04 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `food`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`username`, `password`) VALUES
('admin1', '$2a$10$9GoFyOLYK85xqovRxUP8WO7CTHbc6Oh/Z.Aq4i/G1/YV1nV/itAzu'),
('admin2', '$2a$10$InaC1PfA49kQBXU6XIazIOt7KKxTCo1gYQncpHoei3qnGMplsX2MK');

-- --------------------------------------------------------

--
-- Table structure for table `amount`
--

CREATE TABLE `amount` (
  `month` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `category_id` bigint(20) NOT NULL,
  `image` varchar(200) DEFAULT NULL,
  `category_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `image`, `category_name`) VALUES
(1, 'pbfb7eb21-89ca-4f0e-939c-a1cb4734f0c3.png', 'fast food'),
(2, 'p47e54c28-c98b-4950-a722-ca58aa5cb224.png', 'Drinks');

-- --------------------------------------------------------

--
-- Table structure for table `orderdetails`
--

CREATE TABLE `orderdetails` (
  `order_detail_id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unit_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orderdetails`
--

INSERT INTO `orderdetails` (`order_detail_id`, `order_id`, `product_id`, `quantity`, `unit_price`) VALUES
(1, 0, 0, 0, 0),
(2, 0, 0, 0, 0),
(3, 0, 1, 2, 50000),
(4, 0, 2, 2, 50000),
(5, 9, 1, 2, 50000),
(6, 9, 2, 1, 50000),
(7, 10, 1, 1, 50000),
(8, 10, 2, 1, 50000),
(9, 10, 1, 2, 50000),
(11, 11, 2, 1, 50000),
(12, 11, 1, 2, 50000),
(13, 12, 2, 1, 50000),
(14, 12, 1, 1, 50000),
(15, 13, 2, 1, 50000),
(16, 13, 1, 1, 50000),
(17, 13, 3, 1, 35000),
(18, 14, 3, 1, 35000),
(19, 14, 2, 1, 50000),
(20, 14, 1, 1, 50000);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `order_date` date DEFAULT NULL,
  `status` smallint(6) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `address`, `amount`, `customer_id`, `order_date`, `status`) VALUES
(1, 'ha noi', 50000, 5, '2022-11-14', 1),
(2, 'ha noi', 100000, 1, '2022-11-14', 0),
(3, 'ha noi', 150000, 1, '2022-11-15', 0),
(4, 'ha noi', 150000, 1, '2022-11-15', 0),
(5, 'ha noi', 150000, 1, '2022-11-15', 0),
(6, 'ha noi', 200000, 1, '2022-11-15', 0),
(7, 'ha noi', 250000, 1, '2022-11-16', 0),
(8, 'ha noi', 200000, 1, '2022-11-16', 0),
(9, 'ha noi', 150000, 1, '2022-11-16', 0),
(10, 'ha noi', 150000, 1, '2022-11-16', 1),
(11, 'ha noi', 150000, 1, '2022-11-17', 1),
(12, 'ha noi', 100000, 1, '2022-11-18', 1),
(13, 'ha noi', 135000, 1, '2022-11-21', 0),
(14, 'ha noi', 135000, 2, '2022-12-21', 0);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `description` varchar(500) NOT NULL,
  `discount` double NOT NULL,
  `enter_date` date DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `quantity` int(11) NOT NULL,
  `status` smallint(6) NOT NULL,
  `unit_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `category_id`, `description`, `discount`, `enter_date`, `image`, `name`, `quantity`, `status`, `unit_price`) VALUES
(1, 1, 'ngon tuyệt đỉnh', 0, NULL, 'p2f166e52-92d9-45a2-8448-6b85c82f770b.png', 'pizza chesse', 10, 0, 50000),
(2, 1, 'thơm ngon khó cưỡng', 0, NULL, 'p4bbc9e0a-0537-4c2b-a09c-40c89aefe5fc.png', 'hamburger', 10, 0, 50000),
(3, 2, 'Thực phẩm tốt cho sức khỏe mọi người', 0, NULL, 'p3eeba73f-98d4-451d-9131-2844dd871d76.png', 'pizza vegetable', 10, 0, 35000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `address`, `email`, `name`, `password`) VALUES
(1, 'hà nội', 'admin', 'admin1', '1'),
(3, NULL, NULL, NULL, NULL),
(4, 'HCM', 'admin1', 'Marco', 'admin'),
(5, 'ha noi2', 'admin2', 'admin', '$2a$10$eIAbK8uKrLcMaPlztZdazeXjgM8q4D/ceq5ijKL9NbpDHjM6YL/cm');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `amount`
--
ALTER TABLE `amount`
  ADD PRIMARY KEY (`month`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD PRIMARY KEY (`order_detail_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `orderdetails`
--
ALTER TABLE `orderdetails`
  MODIFY `order_detail_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
