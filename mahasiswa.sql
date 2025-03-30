-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 30 Mar 2025 pada 08.48
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_mahasiswa`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL,
  `nim` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jenis_kelamin` varchar(255) NOT NULL,
  `nilai` enum('A','B','C','D','E') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `nim`, `nama`, `jenis_kelamin`, `nilai`) VALUES
(1, '2203999', 'Amelia Zalfa Julianti', 'Perempuan', 'A'),
(2, '2202292', 'Muhammad Iqbal Fadhilah', 'Laki-laki', 'B'),
(3, '2202346', 'Muhammad Rifky Afandi', 'Laki-laki', 'A'),
(4, '2210239', 'Muhammad Hanif Abdillah', 'Laki-laki', 'C'),
(5, '2202046', 'Nurainun', 'Perempuan', 'E'),
(6, '2205101', 'Kelvin Julian Putra', 'Laki-laki', 'A'),
(7, '2200163', 'Rifanny Lysara Annastasya', 'Perempuan', 'A'),
(8, '2202869', 'Revana Faliha Salma', 'Perempuan', 'B'),
(9, '2209489', 'Rakha Dhifiargo Hariadi', 'Laki-laki', 'C'),
(10, '2203142', 'Roshan Syalwan Nurilham', 'Laki-laki', 'E'),
(11, '2200311', 'Raden Rahman Ismail', 'Laki-laki', 'A'),
(12, '2200978', 'Ratu Syahirah Khairunnisa', 'Perempuan', 'A'),
(13, '2204509', 'Muhammad Fahreza Fauzan', 'Laki-laki', 'A'),
(14, '2205027', 'Muhammad Rizki Revandi', 'Laki-laki', 'B'),
(15, '2203484', 'Arya Aydin Margono', 'Laki-laki', 'C'),
(16, '2200481', 'Marvel Ravindra Dioputra', 'Laki-laki', 'D'),
(17, '2209889', 'Muhammad Fadlul Hafiizh', 'Laki-laki', 'B'),
(18, '2206697', 'Rifa Sania', 'Perempuan', 'D'),
(19, '2207260', 'Imam Chalish Rafidhul Haque', 'Laki-laki', 'A'),
(20, '2204343', 'Meiva Labibah Putri', 'Perempuan', 'A');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
