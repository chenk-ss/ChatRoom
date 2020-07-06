/*
Navicat MySQL Data Transfer

Source Server         : taeyeonss
Source Server Version : 50647
Source Host           : 49.234.176.174:3306
Source Database       : ChatRoomTest

Target Server Type    : MYSQL
Target Server Version : 50647
File Encoding         : 65001

Date: 2020-07-06 16:58:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_group`;
CREATE TABLE `tb_group` (
  `ID` varchar(50) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `ADMIN_ID` varchar(50) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `INTRODUCTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_group_member
-- ----------------------------
DROP TABLE IF EXISTS `tb_group_member`;
CREATE TABLE `tb_group_member` (
  `GROUP_ID` varchar(50) NOT NULL,
  `USER_ID` varchar(50) DEFAULT NULL,
  `ROLE_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `ID` varchar(50) NOT NULL,
  `USER_NAME` varchar(50) DEFAULT NULL,
  `USER_ID` varchar(50) DEFAULT NULL,
  `IP` varchar(30) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `MESSAGE` varchar(2000) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `GROUP_ID` varchar(50) DEFAULT NULL,
  `RECEIVER_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_message_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_message_record`;
CREATE TABLE `tb_message_record` (
  `MESSAGE_ID` varchar(50) NOT NULL,
  `IS_READ` int(2) DEFAULT NULL,
  PRIMARY KEY (`MESSAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `ID` varchar(50) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `CODE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `ID` varchar(50) NOT NULL,
  `USER_NAME` varchar(30) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `REAL_NAME` varchar(10) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `STATUS` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
