-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 14, 2021 at 01:02 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `itsboulot`
--

-- --------------------------------------------------------

--
-- Table structure for table `applicant`
--

CREATE TABLE `applicant` (
  `id` int(11) NOT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `num_ratings` int(11) NOT NULL,
  `rating` double NOT NULL,
  `skills` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `applicant`
--

INSERT INTO `applicant` (`id`, `experience`, `num_ratings`, `rating`, `skills`) VALUES
(6, NULL, 1, 10, 'Smart, Quick Typing, PHP'),
(4, NULL, 0, 0, 'Computer specialist , Manager'),
(9, NULL, 0, 0, 'Swimming');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `job_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `comment`, `date`, `job_id`) VALUES
(1, 'At what time does the job start?', '2021/01/06 18:17:21', 1),
(22, 'How many papers are there?', '2021/01/11 17:33:41', 4),
(21, 'Yes ', '2021/01/11 16:45:52', 5),
(20, 'Hello is this job still available?', '2021/01/11 16:44:49', 5),
(17, 'At 11 o\'clock', '2021/01/06 18:48:18', 1),
(23, 'Is the job still available?', '2021/01/12 15:45:17', 12),
(24, 'Yes, is it?', '2021/01/12 15:45:57', 12);

-- --------------------------------------------------------

--
-- Table structure for table `job`
--

CREATE TABLE `job` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `payment` double NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `status_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `job`
--

INSERT INTO `job` (`id`, `address`, `city`, `date`, `description`, `payment`, `title`, `status_id`, `user_id`) VALUES
(1, 'St. Bartz Str. 78', 'Eindhoven', '2021-02-21', 'A standard clean is something that a homeowner does (or should do) regularly to upkeep the cleanliness of their homes. Regular chores, such as vacuuming and mopping the floors, general bathroom cleaning, kitchen cleaning, and dusting.', 120, 'House Cleaning', 1, 1),
(2, 'The Lounge', 'Amsterdam', '2021-09-01', 'f you\'re after more of a challenge than a 1-on-1 match, then you can enter a tournament. From the main screen, click on the “Tournaments” button. Here you\'ll see the same locations, but you\'ll be playing against more players.', 100, '8 Ball Pool Tournament', 1, 1),
(5, 'Horverd Str', 'Eindhoven', '2021-02-13', 'What is writing a book chapter review is all about? The goal of this project is to share your viewpoint of the literature work or some part of it (chapter) while supporting your opinion with strong evidence taken from the text directly.', 12, 'Reviewing a book', 2, 2),
(4, 'Bojdaijk 12', 'Eindhoven', '2021-03-02', 'the act of classifying something on a scale by quality, rank, size, or progression, etc. a union dispute over pay and grading.', 35, 'Grading Papers', 1, 2),
(6, 'The Park', 'Amsterdam', '2021-02-01', 'Let\'s run together on weekends at 9 o\'clock until 10.', 0, 'Running partner', 1, 2),
(7, 'The Rocks', 'Eindhoven', '2021-02-03', 'To keep everything neat, Good Housekeeping recommends that you perform certain cleaning tasks every day, including sweeping the kitchen floor, wiping down the kitchen counters, and sanitizing the sinks', 75, 'Clean my house', 1, 5),
(8, 'ul. Grafa 1', 'Sofia', '2020-12-12', 'You should post regularly on the website.', 50, 'Post offers', 2, 5),
(9, 'Center', 'Eindhoven', '2000-10-10', 'No', 9, 'Report This job', 6, 5),
(10, 'St. Laurent', 'Eindhoven', '2021-02-13', 'I want someone to teach me how to play the guitar', 120, 'Play the guitar', 1, 8),
(11, 'Main Street', 'Eindhoven', '2021-01-28', 'I need a professional who is able to fix a hole in my roof.', 300, 'Fix the roof', 4, 1),
(12, 'Central Park', 'Eindhoven', '2021-02-10', 'I want to have a photoshoot of my precious dog and its three small babies. I want a professional photographer.', 50, 'Shooting in the park', 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `job_application`
--

CREATE TABLE `job_application` (
  `id` int(11) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `applicant_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `job_application`
--

INSERT INTO `job_application` (`id`, `date`, `description`, `applicant_id`, `job_id`, `status_id`) VALUES
(1, '1/3/2021, 4:23:31 PM', 'Hello, I believe I can do this job.', 6, 8, 2),
(2, '1/3/2021, 4:23:51 PM', 'I Am a good writer and you should hire me', 6, 5, 2),
(3, '1/12/2021, 11:33:30 AM', 'I have fixed the roof in my house and I can help with yours.', 4, 11, 2),
(4, '1/12/2021, 3:40:06 PM', 'I am qualified. I\'ve been shooting pics since I am 10.', 6, 12, 1),
(5, '1/12/2021, 3:41:17 PM', 'I am a professional, but I take 70$/h ', 4, 12, 3);

-- --------------------------------------------------------

--
-- Table structure for table `job_report`
--

CREATE TABLE `job_report` (
  `id` int(11) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `job_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `job_report`
--

INSERT INTO `job_report` (`id`, `date`, `description`, `job_id`) VALUES
(1, '1/3/2021, 4:33:34 PM', NULL, 9);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`id`, `name`) VALUES
(1, 'Available'),
(2, 'Completed'),
(3, 'Accepted'),
(5, 'Denied'),
(4, 'In Progress'),
(6, 'For Review');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `date_of_birth` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `city`, `country`, `date_of_birth`, `email`, `first_name`, `image`, `last_name`, `password`, `phone_number`, `role`, `username`) VALUES
(1, 'Sofia', 'Bulgaria', '21-09-2000', 'alex@email.com', 'Aleksandar', 'https://cdn.shopify.com/s/files/1/0045/5104/9304/t/27/assets/AC_ECOM_SITE_2020_REFRESH_1_INDEX_M2_THUMBS-V2-1.jpg?v=8913815134086573859', 'Dimitrov', '$2a$10$vHg1kJAMEV2qa3lJBUCHaOEDye4dKkSwLZuHIGFsEbsuWuYEP7afe', '123456789', 'POSTER', 'asho'),
(2, 'Amsterdam', 'Netherlands ', '16-12-1978', 'alanmark@email.com', 'Alan', 'https://pbs.twimg.com/profile_images/944712346500587522/duOz-D8s_400x400.jpg', 'Mark', '$2a$10$FT8qXw.An8EroHONgtJ5YO8uXxeM2qszZiwVAVSbQ32/uRJBQUMni', '1234567', 'POSTER', 'alanmark'),
(3, NULL, NULL, NULL, 'admin@email.com', 'Admin', NULL, 'Admin', '$2a$10$4Pc3JfgQAVyNHQ74TL5GI..9rqe2J.9cetD.KqVJOYNxiByNzbYm.', NULL, 'ADMIN', 'admin'),
(4, 'Amsterdam', 'The Netherlands', '1992-12-08', 'teressa@email.com', 'Teressa', 'https://i.pinimg.com/originals/97/ed/6b/97ed6b370803649addbf66144c18c194.png', 'Fork', '$2a$10$gUyh4SIp0Z3hUca3KMh93.G4DWVS7QE3b7E5eZJfG.c1XFW7schHa', '456787999', 'SEEKER', 'teressa'),
(5, 'Sofia', 'Bulgaria', '18-12-2000', 'juliepetrova.jk@gmail.com', 'Yulia', 'https://storage.googleapis.com/afs-prod/media/media:2d630a18c9f34425b2f82d130be6ac0a/800.jpeg', 'Yulia', '$2a$10$HCtaq5vkAzwzW2quzicsD.wxjVfhdWsV.y9xxpaApwUq0cjEQte2i', '1234567', 'POSTER', 'juliepetrova.jk@gmail.com'),
(6, 'Eindhoven', 'The Netherlands', '12-12-2008', 'psabeva.js@gmail.com', 'Valia', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpv3hiE0zKupAekeAVtXLR3zwVBrGn98MHSw&usqp=CAU', 'Valia', '$2a$10$N.wPoymsO5eMZ1pO9IFcaOQF3ZY.xLN/w6CgUebpatssuzg2pXeta', '987657654', 'SEEKER', 'valia'),
(8, NULL, NULL, NULL, 'elmora@email.com', 'Elmora', NULL, 'Reets', '$2a$10$kXjIdw2nNxYTdu.wjC13rOnhUN9r3I2ux3/eJCG3uTX.j9h1VFW4i', NULL, 'POSTER', 'elmora123'),
(9, NULL, NULL, NULL, 'petar@email.com', 'Petar', NULL, 'Dinker', '$2a$10$ad0nZFaERu8PwSL3SW52o.BRJjVDJkFHiEGevRkbzs5Eua8ELjJbS', NULL, 'SEEKER', 'petar');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `applicant`
--
ALTER TABLE `applicant`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `job`
--
ALTER TABLE `job`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtpro3k2axsnrs8ilpt8nmmf27` (`status_id`),
  ADD KEY `FKihd6m3auwpenduntl3e1opcoq` (`user_id`);

--
-- Indexes for table `job_application`
--
ALTER TABLE `job_application`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKc2m3mq1eerfordrqr8k71klfc` (`applicant_id`),
  ADD KEY `FKdepcvxeq3gyb4438ws0qjycc7` (`job_id`),
  ADD KEY `FKa2qla7ms9x21imejkv8sjk9p1` (`status_id`);

--
-- Indexes for table `job_report`
--
ALTER TABLE `job_report`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbcjr4x0witqoxoyq8jfqllaof` (`job_id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `job`
--
ALTER TABLE `job`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `job_application`
--
ALTER TABLE `job_application`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `job_report`
--
ALTER TABLE `job_report`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
