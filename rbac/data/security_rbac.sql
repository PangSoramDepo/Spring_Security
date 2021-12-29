/*
Navicat MySQL Data Transfer

Source Server         : Web
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : security_rbac

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2021-12-29 13:38:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for access_control
-- ----------------------------
DROP TABLE IF EXISTS `access_control`;
CREATE TABLE `access_control` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `api` varchar(255) DEFAULT NULL,
  `access_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of access_control
-- ----------------------------
INSERT INTO `access_control` VALUES ('1', '/api/authenticate', 'PERMIT');
INSERT INTO `access_control` VALUES ('2', '/api/users/login', 'HAS_ANY_ROLE');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'Admin role', 'ADMIN');
INSERT INTO `role` VALUES ('2', 'User role', 'USER');
INSERT INTO `role` VALUES ('3', 'Member', 'MEMBER');

-- ----------------------------
-- Table structure for role_access_control
-- ----------------------------
DROP TABLE IF EXISTS `role_access_control`;
CREATE TABLE `role_access_control` (
  `access_control_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`access_control_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_access_control
-- ----------------------------
INSERT INTO `role_access_control` VALUES ('2', '1');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'depo', '$2a$12$pwZbi1FoxTQQKbB8I7ANX.NW8lc630mjI6r74WMVsTE8QJ3VKoqbi', '1');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('1', '1');
