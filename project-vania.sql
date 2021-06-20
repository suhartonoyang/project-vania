-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2021 at 05:09 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project-vania`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_latih_anjing`
--

CREATE TABLE `data_latih_anjing` (
  `id` int(11) NOT NULL,
  `jenis_kelamin` varchar(255) DEFAULT NULL,
  `gatal_gatal` varchar(255) DEFAULT NULL,
  `mengigit_gigit` varchar(255) DEFAULT NULL,
  `menjilat_kaki` varchar(255) DEFAULT NULL,
  `bulu_rontok` varchar(255) DEFAULT NULL,
  `nafsu_makan` varchar(255) DEFAULT NULL,
  `jamuran` varchar(255) DEFAULT NULL,
  `kropeng` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_latih_anjing`
--

INSERT INTO `data_latih_anjing` (`id`, `jenis_kelamin`, `gatal_gatal`, `mengigit_gigit`, `menjilat_kaki`, `bulu_rontok`, `nafsu_makan`, `jamuran`, `kropeng`, `result`) VALUES
(1, 'Jantan', 'Ya', 'Tidak', 'Tidak', 'Sedang', 'Tidak Bagus', 'Ya', 'Sedang', 'Scabies'),
(2, 'Jantan', 'Ya', 'Tidak', 'Tidak', 'Tidak', 'Bagus', 'Tidak', 'Sedikit', 'Ringworm'),
(3, 'Jantan', 'Ya', 'Ya', 'Ya', 'Ya', 'Tidak Bagus', 'Ya', 'Sedang', 'Canine Atopic Dermatitis'),
(4, 'Betina', 'Ya', 'Ya', 'Ya', 'Sedang', 'Bagus', 'Tidak', 'Sedang', 'Scabies');

-- --------------------------------------------------------

--
-- Table structure for table `data_latih_kucing`
--

CREATE TABLE `data_latih_kucing` (
  `id` int(11) NOT NULL,
  `jenis_kelamin` varchar(255) DEFAULT NULL,
  `gatal_gatal` varchar(255) DEFAULT NULL,
  `kulit_kemerahan` varchar(255) DEFAULT NULL,
  `bulu_rontok` varchar(255) DEFAULT NULL,
  `kulit_kering` varchar(255) DEFAULT NULL,
  `bengkak` varchar(255) DEFAULT NULL,
  `kropeng` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_latih_kucing`
--

INSERT INTO `data_latih_kucing` (`id`, `jenis_kelamin`, `gatal_gatal`, `kulit_kemerahan`, `bulu_rontok`, `kulit_kering`, `bengkak`, `kropeng`, `result`) VALUES
(1, 'Jantan', 'Ya', 'Ya', 'Ya', 'Ya', 'Ya', 'Tidak', 'Scabies');

-- --------------------------------------------------------

--
-- Table structure for table `data_testing_anjing`
--

CREATE TABLE `data_testing_anjing` (
  `id` int(11) NOT NULL,
  `jenis_kelamin` varchar(255) DEFAULT NULL,
  `gatal_gatal` varchar(255) DEFAULT NULL,
  `mengigit_gigit` varchar(255) DEFAULT NULL,
  `menjilat_kaki` varchar(255) DEFAULT NULL,
  `bulu_rontok` varchar(255) DEFAULT NULL,
  `nafsu_makan` varchar(255) DEFAULT NULL,
  `jamuran` varchar(255) DEFAULT NULL,
  `kropeng` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_testing_anjing`
--

INSERT INTO `data_testing_anjing` (`id`, `jenis_kelamin`, `gatal_gatal`, `mengigit_gigit`, `menjilat_kaki`, `bulu_rontok`, `nafsu_makan`, `jamuran`, `kropeng`, `result`) VALUES
(9, 'Betina', 'Tidak', 'Ya', 'Ya', 'Sedang', 'Bagus', 'Tidak', 'Sedang', 'Sedang'),
(10, 'Betina', 'Tidak', 'Ya', 'Ya', 'Sedang', 'Bagus', 'Tidak', 'Sedang', 'Scabies'),
(11, 'Betina', 'Tidak', 'Ya', 'Ya', 'Sedang', 'Bagus', 'Tidak', 'Sedang', 'Scabies');

-- --------------------------------------------------------

--
-- Table structure for table `data_testing_kucing`
--

CREATE TABLE `data_testing_kucing` (
  `id` int(11) NOT NULL,
  `jenis_kelamin` varchar(255) DEFAULT NULL,
  `gatal_gatal` varchar(255) DEFAULT NULL,
  `kulit_kemerahan` varchar(255) DEFAULT NULL,
  `bulu_rontok` varchar(255) DEFAULT NULL,
  `kulit_kering` varchar(255) DEFAULT NULL,
  `bengkak` varchar(255) DEFAULT NULL,
  `kropeng` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_testing_kucing`
--

INSERT INTO `data_testing_kucing` (`id`, `jenis_kelamin`, `gatal_gatal`, `kulit_kemerahan`, `bulu_rontok`, `kulit_kering`, `bengkak`, `kropeng`, `result`) VALUES
(3, 'Jantan', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Ya', 'Scabies');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_latih_anjing`
--
ALTER TABLE `data_latih_anjing`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_latih_kucing`
--
ALTER TABLE `data_latih_kucing`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_testing_anjing`
--
ALTER TABLE `data_testing_anjing`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_testing_kucing`
--
ALTER TABLE `data_testing_kucing`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_latih_anjing`
--
ALTER TABLE `data_latih_anjing`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `data_latih_kucing`
--
ALTER TABLE `data_latih_kucing`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `data_testing_anjing`
--
ALTER TABLE `data_testing_anjing`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `data_testing_kucing`
--
ALTER TABLE `data_testing_kucing`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
