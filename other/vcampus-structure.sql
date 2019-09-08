/*
Navicat MySQL Data Transfer

Source Server Version : 50723
Source Database       : vcampus

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-09-08 17:15:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_book
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
  `isbn` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `number` int(10) NOT NULL,
  `chargable` int(1) NOT NULL,
  `borrower` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `details` varchar(255) NOT NULL,
  `pictureURL` varchar(255) NOT NULL,
  `borrowTime` date NOT NULL,
  `renewornot` int(11) NOT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_class
-- ----------------------------
DROP TABLE IF EXISTS `tb_class`;
CREATE TABLE `tb_class` (
  `id` varchar(19) NOT NULL,
  `className` varchar(255) NOT NULL,
  `time` varchar(20) NOT NULL,
  `teacher` varchar(5) NOT NULL,
  `classroom` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_emoticon
-- ----------------------------
DROP TABLE IF EXISTS `tb_emoticon`;
CREATE TABLE `tb_emoticon` (
  `name` varchar(255) NOT NULL,
  `addr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_manager
-- ----------------------------
DROP TABLE IF EXISTS `tb_manager`;
CREATE TABLE `tb_manager` (
  `card_number` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `manager_type` varchar(255) NOT NULL,
  PRIMARY KEY (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_opencourse
-- ----------------------------
DROP TABLE IF EXISTS `tb_opencourse`;
CREATE TABLE `tb_opencourse` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CourseName` varchar(255) NOT NULL,
  `Speaker` varchar(255) NOT NULL,
  `Preview` varchar(255) NOT NULL,
  `video` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `price` float(10,2) NOT NULL,
  `picture` varchar(255) NOT NULL,
  `number` int(11) NOT NULL,
  `information` varchar(255) NOT NULL,
  `heat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `card_number` varchar(255) NOT NULL,
  `student_number` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `academy` varchar(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `balance` double(255,0) NOT NULL,
  `selected_course` varchar(1000) NOT NULL,
  PRIMARY KEY (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher` (
  `card_number` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `academy` varchar(255) NOT NULL,
  `id_in_academy` varchar(255) NOT NULL,
  PRIMARY KEY (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
