CREATE DATABASE IF NOT EXISTS `garanti_service`;
USE `garanti_service`;

CREATE TABLE `garanti_applications` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `create_date` datetime,
  `update_date` datetime,
  `status` ENUM('INITIAL', 'IN_PROGRESS', 'DONE')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;