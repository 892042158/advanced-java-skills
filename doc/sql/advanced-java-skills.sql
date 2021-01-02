/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50732
Source Host           : localhost:3306
Source Database       : advanced-java-skills

Target Server Type    : MYSQL
Target Server Version : 50732
File Encoding         : 65001

Date: 2020-12-14 01:26:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_system_user
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user`;
CREATE TABLE `t_system_user` (
  `id` bigint(13) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `birthday` varchar(20) DEFAULT NULL COMMENT '生日',
  `sex` int(1) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `roleId` varchar(255) DEFAULT NULL COMMENT '角色id',
  `deptId` bigint(13) DEFAULT NULL COMMENT '部门id',
  `status` int(1) DEFAULT NULL COMMENT '状态(0：启用  2：冻结  -1：删除）',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1549936664487 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='管理员表';

-- ----------------------------
-- Records of t_system_user
-- ----------------------------
INSERT INTO `t_system_user` VALUES ('1', null, 'admin', '88fdc620f67c5947e350e747c53b6be1', '5xCpGV', '果果', '2018-12-25', '1', '', '', '1', '1', '1', '2018-12-25 13:58:11', '2019-02-11 09:31:01');
INSERT INTO `t_system_user` VALUES ('2', null, 'test', 'efa870b0d2b632add3b805daa749937b', 'VeKO0X', '暗杀', '', '1', '', '', '1545834224068', '1545833237900', '0', '2019-01-01 16:27:56', '2019-01-28 15:34:08');
INSERT INTO `t_system_user` VALUES ('3', null, 'fanfan', '898f791a677ffa86683a227e9bafee9f', 'MNIyqT', '2', '', '1', '', '', '1545834224068', '1545833237900', '0', '2019-01-02 20:43:01', '2019-01-06 20:17:16');
INSERT INTO `t_system_user` VALUES ('4', null, '111', '838d2395a6405de1187fbfbce19afb53', 'FJqIiV', '1', '', '1', '', '', '1545834224068', '1545833237900', '-1', '2019-01-02 21:04:16', '2019-01-06 17:22:02');
INSERT INTO `t_system_user` VALUES ('5', null, 'yu89', 'd271d3a051abbe0bae29098f3d70e02e', 'n52eHY', '11', '', '1', '', '', '1545834224068', '1545833237900', '-1', '2019-01-06 18:25:45', '2019-01-06 18:26:57');
INSERT INTO `t_system_user` VALUES ('6', null, 'test1111', 'ab93055521565dd992bb677763eafacb', 'E8gZ4r', '11', '', '1', '', '', '1545834224068', '1545833237900', '-1', '2019-01-06 18:27:23', null);
INSERT INTO `t_system_user` VALUES ('7', null, 'test1607791906397', 'de76c105336cf6fb01e53f8885dccbe5', 'AFgDJ8', '测试mybatis的xml', '2019-02-12', '1', '892042158@qq.com', '17353620612', '1545834224068', '1545833237900', '0', '2019-02-12 09:57:44', null);
INSERT INTO `t_system_user` VALUES ('1549936664481', null, 'test1607791832637', '123456', null, '测试mybatis的xml', null, null, null, null, null, null, null, null, null);
INSERT INTO `t_system_user` VALUES ('1549936664482', null, 'test1607791884986___0', '123456', null, '测试mybatis的xml', null, null, null, null, null, null, null, null, null);
INSERT INTO `t_system_user` VALUES ('1549936664483', null, 'test1607791884987___1', '123456', null, '测试mybatis的xml', null, null, null, null, null, null, null, null, null);
INSERT INTO `t_system_user` VALUES ('1549936664484', null, 'test1607791884987___2', '123456', null, '测试mybatis的xml', null, null, null, null, null, null, null, null, null);
INSERT INTO `t_system_user` VALUES ('1549936664485', null, 'test1607791884987___3', '123456', null, '测试mybatis的xml', null, null, null, null, null, null, null, null, null);
INSERT INTO `t_system_user` VALUES ('1549936664486', null, 'test1607791884987___4', '123456', null, '测试mybatis的xml', null, null, null, null, null, null, null, null, null);
