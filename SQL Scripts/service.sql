CREATE DATABASE IF NOT EXISTS `kredinbizde_service`;
USE `kredinbizde_service`;

CREATE TABLE `users` (
  `id` BigInt AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(45),
  `surname` varchar(45),
  `birth_date` DATE,
  `email` varchar(100) unique,
  `password` varchar(300),
  `phone_number` varchar(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `banks` (
  `id` BigInt NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(20) unique
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `loans` (
  `id` BigInt NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `loan_type` ENUM('IHTIYAC_KREDISI', 'KONUT_KREDISI', 'ARAC_KREDISI'),
  `amount` decimal,
  `installment` int,
  `interest_rate` double,
  `bank_id` BigInt,
  CONSTRAINT fk_bank_loan FOREIGN KEY (bank_id) REFERENCES banks(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `credit_cards` (
  `id` BigInt NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `fee` decimal,
  `bank_id` BigInt,
  CONSTRAINT fk_bank_credit_card FOREIGN KEY (bank_id) REFERENCES banks(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `campaigns` (
  `id` BigInt NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` varchar(50),
  `content` varchar(100),
  `due_date` DATE,
  `create_date` DATE,
  `update_date` DATE,
  `sector_type` ENUM('MARKET', 'TRAVELS', 'OTHERS'),
  `credit_card_id` BigInt,
  CONSTRAINT fk_credit_card_campaign FOREIGN KEY (credit_card_id) REFERENCES credit_cards(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `applications` (
  `id` BigInt NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `create_date` datetime,
  `update_date` datetime,
  `status` ENUM('INITIAL', 'IN_PROGRESS', 'DONE'),
  `user_id` BigInt,
  `loan_id` BigInt,
  `credit_card_id` BigInt,
  CONSTRAINT fk_user_application FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_loan_application FOREIGN KEY (loan_id) REFERENCES loans(id),
  CONSTRAINT fk_credit_card_application FOREIGN KEY (credit_card_id) REFERENCES credit_cards(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
