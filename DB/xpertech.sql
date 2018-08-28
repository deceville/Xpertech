-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 28, 2018 at 04:17 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `xpertech`
--

-- --------------------------------------------------------

--
-- Table structure for table `cablebox`
--

CREATE TABLE `cablebox` (
  `box_number` int(11) NOT NULL,
  `manufacturer` varchar(30) NOT NULL,
  `model` varchar(30) NOT NULL,
  `color` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cablebox`
--

INSERT INTO `cablebox` (`box_number`, `manufacturer`, `model`, `color`) VALUES
(1001, 'Skyworth', 'HD 7400', 'Black');

-- --------------------------------------------------------

--
-- Table structure for table `channels`
--

CREATE TABLE `channels` (
  `channel_number` int(11) NOT NULL,
  `channel_name` varchar(30) NOT NULL,
  `package_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `channels`
--

INSERT INTO `channels` (`channel_number`, `channel_name`, `package_id`) VALUES
(2, 'ABS_CBN', 1),
(103, 'TVN', 2);

-- --------------------------------------------------------

--
-- Table structure for table `ownership`
--

CREATE TABLE `ownership` (
  `ownership_id` bigint(20) NOT NULL,
  `box_number` int(11) NOT NULL,
  `subscriber_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ownership`
--

INSERT INTO `ownership` (`ownership_id`, `box_number`, `subscriber_id`) VALUES
(10011000001, 1001, 1000001),
(10011000002, 1001, 1000002);

-- --------------------------------------------------------

--
-- Table structure for table `packages`
--

CREATE TABLE `packages` (
  `package_number` int(11) NOT NULL,
  `package_name` varchar(20) NOT NULL,
  `box_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `packages`
--

INSERT INTO `packages` (`package_number`, `package_name`, `box_number`) VALUES
(1, 'Crystal Package', 1001),
(2, 'Diamond Package', 1001);

-- --------------------------------------------------------

--
-- Table structure for table `prices`
--

CREATE TABLE `prices` (
  `price_number` int(11) NOT NULL,
  `digital_box` double NOT NULL,
  `monthsub_mainline` double NOT NULL,
  `monthsub_extline` double NOT NULL,
  `package_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prices`
--

INSERT INTO `prices` (`price_number`, `digital_box`, `monthsub_mainline`, `monthsub_extline`, `package_id`) VALUES
(1, 1000, 480, 70, 1),
(2, 1000, 680, 100, 2);

-- --------------------------------------------------------

--
-- Table structure for table `remote_control`
--

CREATE TABLE `remote_control` (
  `remote_id` int(11) NOT NULL,
  `remote_instruction` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `selfinstall`
--

CREATE TABLE `selfinstall` (
  `selfinstall_id` int(11) NOT NULL,
  `selfinstall_title` varchar(30) NOT NULL,
  `box_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `selfinstall`
--

INSERT INTO `selfinstall` (`selfinstall_id`, `selfinstall_title`, `box_number`) VALUES
(1, 'Unpacking', 1001),
(2, 'Plugging In', 1001),
(3, 'Powering up the Box', 1001),
(4, 'Support and Activating Service', 1001);

-- --------------------------------------------------------

--
-- Table structure for table `selfinstall_steps`
--

CREATE TABLE `selfinstall_steps` (
  `install_steps_id` int(11) NOT NULL,
  `install_steps_desc` varchar(255) NOT NULL,
  `install_image_id` int(11) DEFAULT NULL,
  `selfinstall_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `stat_info`
--

CREATE TABLE `stat_info` (
  `type` varchar(255) NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subscribers`
--

CREATE TABLE `subscribers` (
  `account_number` int(11) NOT NULL,
  `sub_name` varchar(30) NOT NULL,
  `sub_address` varchar(255) NOT NULL,
  `sub_contactnumber` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subscribers`
--

INSERT INTO `subscribers` (`account_number`, `sub_name`, `sub_address`, `sub_contactnumber`) VALUES
(1000001, 'Josh Sanchez', 'Bacolod City', '09096195112'),
(1000002, 'Nilry Balasa', 'Silay City', '09096192133'),
(1000003, 'Rhea Martinez', 'Bacolod City', '09096192382'),
(1000004, 'Melji Aribang', 'Silay City', '09781237817'),
(1000005, 'Jacob Estrada', 'Bacolod City', '09765849336');

-- --------------------------------------------------------

--
-- Table structure for table `troubleshoot`
--

CREATE TABLE `troubleshoot` (
  `troubleshoot_id` int(11) NOT NULL,
  `troubleshoot_title` varchar(255) NOT NULL,
  `box_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `troubleshoot`
--

INSERT INTO `troubleshoot` (`troubleshoot_id`, `troubleshoot_title`, `box_number`) VALUES
(1, 'STB Configuration', 1001),
(2, 'TV Configuration (via Simple Set Button)', 1001),
(3, 'My set top box (STB) is not Booting Up.', 1001),
(4, 'My TV has No Audio and/or Video Output', 1001),
(5, 'My TV is Showing \"Technical Problem\" Error / Pixilated Pictures/ON and OFF Programming', 1001),
(6, 'My TV Screen is Showing an Error Code - E1 / E2 / E11 / E4 / E6 / E14', 1001);

-- --------------------------------------------------------

--
-- Table structure for table `troubleshoot_steps`
--

CREATE TABLE `troubleshoot_steps` (
  `trbl_steps_id` int(11) NOT NULL,
  `trbl_steps_desc` varchar(255) NOT NULL,
  `trbl_image_id` int(11) DEFAULT NULL,
  `troubleshoot_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `troubleshoot_steps`
--

INSERT INTO `troubleshoot_steps` (`trbl_steps_id`, `trbl_steps_desc`, `trbl_image_id`, `troubleshoot_id`) VALUES
(11, 'On the Remote, press and hold OK button and Power button simultaneously until LED blinks 2x', NULL, 1),
(12, 'Press 9-8-2 on the remote to unlock it for RCU programming, LED should blink 4x', NULL, 1),
(13, 'Press and hold the OK button and Power button again simultaneously for 3-5 seconds until the LED blinks 2x', NULL, 1),
(14, 'Press the assigned code 4998/2319 to be controlled', NULL, 1),
(15, 'Remote control LED will blink 2x once correct code is entered', NULL, 1),
(16, 'Press and hold the OK button and Power button simultaneously for 3-5 seconds until the LED blinks 2x', NULL, 1),
(17, 'Press 9-8-2 on the remote to lock it for RCU programming, LED should blink 2x', NULL, 1),
(21, 'Turn on your TV', NULL, 2),
(22, 'Press and hold the Simple Set button until LED blinks 2x', NULL, 2),
(23, 'While pointing the remote control to your TV, press and hold the number button which corresponds to your TV brand until the TV turns off by itself', NULL, 2),
(24, 'Turn on your TV using the TV remote control', NULL, 2),
(25, 'Once turned on, perform a test using your remote control to your TV by\r\npressing the Volume Up and Volume Down\r\npressing the Mute button', NULL, 2),
(26, 'Test the Channel +/- key on the Remote control', NULL, 2),
(31, 'Make sure your STB is plugged in', NULL, 3),
(32, 'Check your STB front panel if it is turned on (LED is green)', NULL, 3),
(33, 'If light is green and still not booting up, perform hard reset by unplugging the STB from the wall socket and plug it back in after 5 seconds', NULL, 3),
(41, 'Make sure that your TV is not on standby mode', NULL, 4),
(42, 'Check the connections between the STB and TV if firmly and properly connected', NULL, 4),
(43, 'On your TV, select the correct Audio/Video input or source (example: AV1, AV2, HDM1 HDM2, etc.)', NULL, 4),
(44, 'Power on the l STB', NULL, 4),
(45, 'Check TV and STB volume functions', NULL, 4),
(46, 'If issue persists, perform hard reset by unplugging the STB from the wall socket and plug it back in after 5 seconds', NULL, 4),
(51, 'Check if coaxial cable (RG6) is firmly connected and secured', NULL, 5),
(52, 'Press the MENU button on your remote control then navigate to SETTINGS', NULL, 5),
(53, 'Key in default PIN as 0000 or 9998', NULL, 5),
(54, 'Navigate to the following options SYSTEM SETUP > INSTALLATION SETUP > SATELLITE SETUP > LNB POWERING', NULL, 5),
(55, 'Toggle ON/OFF using the LEFT and RIGHT buttons on the remote', NULL, 5),
(56, 'For the Primary STB - must be set to ON\r\nFor 2nd/3rd STB - must be set to OFF\r\n', NULL, 5),
(61, 'Turn the STB off and locate where the smart card is inserted', NULL, 6),
(62, 'Gently take out the smart card and check for any physical defects', NULL, 6),
(63, 'You may try to wipe the gold chip with a soft, dry, non-abrasive cloth to clear any dirt build up', NULL, 6),
(64, 'Insert the smart card back to the card slot the same way how it was removed', NULL, 6),
(65, 'Make sure that the smart card is properly inserted and seated securely', NULL, 6);

-- --------------------------------------------------------

--
-- Table structure for table `usermanual`
--

CREATE TABLE `usermanual` (
  `manual_id` int(11) NOT NULL,
  `manual_title` varchar(30) NOT NULL,
  `box_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `usermanual_steps`
--

CREATE TABLE `usermanual_steps` (
  `manual_steps_id` int(11) NOT NULL,
  `manual_steps_desc` varchar(255) NOT NULL,
  `manual_image_id` int(11) DEFAULT NULL,
  `manual_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cablebox`
--
ALTER TABLE `cablebox`
  ADD PRIMARY KEY (`box_number`);

--
-- Indexes for table `channels`
--
ALTER TABLE `channels`
  ADD PRIMARY KEY (`channel_number`),
  ADD KEY `channels_ibfk_1` (`package_id`);

--
-- Indexes for table `ownership`
--
ALTER TABLE `ownership`
  ADD PRIMARY KEY (`ownership_id`),
  ADD KEY `ownership_ibfk_1` (`subscriber_id`),
  ADD KEY `ownership_ibfk_2` (`box_number`);

--
-- Indexes for table `packages`
--
ALTER TABLE `packages`
  ADD PRIMARY KEY (`package_number`),
  ADD KEY `packages_ibfk_3` (`box_number`);

--
-- Indexes for table `prices`
--
ALTER TABLE `prices`
  ADD PRIMARY KEY (`price_number`),
  ADD KEY `package_id` (`package_id`);

--
-- Indexes for table `remote_control`
--
ALTER TABLE `remote_control`
  ADD PRIMARY KEY (`remote_id`);

--
-- Indexes for table `selfinstall`
--
ALTER TABLE `selfinstall`
  ADD PRIMARY KEY (`selfinstall_id`),
  ADD KEY `selfinstall_ibfk_1` (`box_number`);

--
-- Indexes for table `selfinstall_steps`
--
ALTER TABLE `selfinstall_steps`
  ADD PRIMARY KEY (`install_steps_id`),
  ADD KEY `selfinstall_steps_ibfk_1` (`selfinstall_id`);

--
-- Indexes for table `subscribers`
--
ALTER TABLE `subscribers`
  ADD PRIMARY KEY (`account_number`);

--
-- Indexes for table `troubleshoot`
--
ALTER TABLE `troubleshoot`
  ADD PRIMARY KEY (`troubleshoot_id`),
  ADD KEY `troubleshoot_ibfk_1` (`box_number`);

--
-- Indexes for table `troubleshoot_steps`
--
ALTER TABLE `troubleshoot_steps`
  ADD PRIMARY KEY (`trbl_steps_id`),
  ADD KEY `troubleshoot_steps_ibfk_1` (`troubleshoot_id`);

--
-- Indexes for table `usermanual`
--
ALTER TABLE `usermanual`
  ADD PRIMARY KEY (`manual_id`),
  ADD KEY `usermanual_ibfk_1` (`box_number`);

--
-- Indexes for table `usermanual_steps`
--
ALTER TABLE `usermanual_steps`
  ADD PRIMARY KEY (`manual_steps_id`),
  ADD KEY `usermanual_steps_ibfk_1` (`manual_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `channels`
--
ALTER TABLE `channels`
  ADD CONSTRAINT `channels_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_number`);

--
-- Constraints for table `ownership`
--
ALTER TABLE `ownership`
  ADD CONSTRAINT `ownership_ibfk_1` FOREIGN KEY (`subscriber_id`) REFERENCES `subscribers` (`account_number`),
  ADD CONSTRAINT `ownership_ibfk_2` FOREIGN KEY (`box_number`) REFERENCES `cablebox` (`box_number`);

--
-- Constraints for table `packages`
--
ALTER TABLE `packages`
  ADD CONSTRAINT `packages_ibfk_3` FOREIGN KEY (`box_number`) REFERENCES `cablebox` (`box_number`);

--
-- Constraints for table `prices`
--
ALTER TABLE `prices`
  ADD CONSTRAINT `prices_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_number`);

--
-- Constraints for table `remote_control`
--
ALTER TABLE `remote_control`
  ADD CONSTRAINT `remote_control_ibfk_1` FOREIGN KEY (`remote_id`) REFERENCES `cablebox` (`box_number`);

--
-- Constraints for table `selfinstall`
--
ALTER TABLE `selfinstall`
  ADD CONSTRAINT `selfinstall_ibfk_1` FOREIGN KEY (`box_number`) REFERENCES `cablebox` (`box_number`);

--
-- Constraints for table `selfinstall_steps`
--
ALTER TABLE `selfinstall_steps`
  ADD CONSTRAINT `selfinstall_steps_ibfk_1` FOREIGN KEY (`selfinstall_id`) REFERENCES `selfinstall` (`selfinstall_id`);

--
-- Constraints for table `troubleshoot`
--
ALTER TABLE `troubleshoot`
  ADD CONSTRAINT `troubleshoot_ibfk_1` FOREIGN KEY (`box_number`) REFERENCES `cablebox` (`box_number`);

--
-- Constraints for table `troubleshoot_steps`
--
ALTER TABLE `troubleshoot_steps`
  ADD CONSTRAINT `troubleshoot_steps_ibfk_1` FOREIGN KEY (`troubleshoot_id`) REFERENCES `troubleshoot` (`troubleshoot_id`);

--
-- Constraints for table `usermanual`
--
ALTER TABLE `usermanual`
  ADD CONSTRAINT `usermanual_ibfk_1` FOREIGN KEY (`box_number`) REFERENCES `cablebox` (`box_number`);

--
-- Constraints for table `usermanual_steps`
--
ALTER TABLE `usermanual_steps`
  ADD CONSTRAINT `usermanual_steps_ibfk_1` FOREIGN KEY (`manual_id`) REFERENCES `usermanual` (`manual_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
