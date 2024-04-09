CREATE DATABASE IF NOT EXISTS `akbank_service`;
USE `akbank_service`;

CREATE TABLE `akbank_applications` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `create_date` datetime,
  `update_date` datetime,
  `status` ENUM('INITIAL', 'IN_PROGRESS', 'DONE')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;