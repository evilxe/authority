/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : shiro

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 26/10/2020 20:17:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `perms` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `menu_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单类型 CATALOGUE:目录，MENUS:菜单，BUTTON:按钮',
  `order_num` int(3) NOT NULL DEFAULT 0 COMMENT '排序',
  `component` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `menu_status` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态 ENDBLED：启用，DISABLED：禁用',
  `creator_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifier_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('04e4e4bbd9ff454ea3560b61eb01eff1', '1431cbd97cc245d28d24b8cb2098a3a5', '添加菜单', NULL, 'systems:menu:saveMenu', 'BUTTON', 1, '22222', ' ENABLED', '9b994c970ad04f998188ce6af005b29a', '2020-09-02 10:55:50', '9b994c970ad04f998188ce6af005b29a', '2020-09-02 11:15:32');
INSERT INTO `menu` VALUES ('0d954719c3a0427485a6ffe07632b6b6', 'dba5ef2858e8458490b5afe0256bf486', '添加角色', NULL, 'systems:user:addRole', 'BUTTON', 0, NULL, ' ENABLED', '9b994c970ad04f998188ce6af005b29a', '2020-09-01 15:43:41', NULL, NULL);
INSERT INTO `menu` VALUES ('1431cbd97cc245d28d24b8cb2098a3a5', NULL, '菜单管理', NULL, '', 'CATALOGUE', 0, NULL, ' ENABLED', '9b994c970ad04f998188ce6af005b29a', '2020-09-02 10:52:46', NULL, NULL);
INSERT INTO `menu` VALUES ('5251705dc5d74abba8b4552b5e288fcb', '1431cbd97cc245d28d24b8cb2098a3a5', '菜单列表', NULL, 'systems:menu:getMenuListPage', 'MENUS', 1, '11', ' ENABLED', '9b994c970ad04f998188ce6af005b29a', '2020-09-02 10:55:17', NULL, NULL);
INSERT INTO `menu` VALUES ('5fe8560b5bc24669a6cf805830fefc53', 'dba5ef2858e8458490b5afe0256bf486', '用户列表', NULL, 'systems:user:getUserListPage', 'MENUS', 0, NULL, ' ENABLED', '9b994c970ad04f998188ce6af005b29a', '2020-09-01 15:40:30', NULL, NULL);
INSERT INTO `menu` VALUES ('dba5ef2858e8458490b5afe0256bf486', NULL, '用户管理', NULL, '', 'CATALOGUE', 0, NULL, ' ENABLED', '9b994c970ad04f998188ce6af005b29a', '2020-09-01 15:36:29', NULL, NULL);
INSERT INTO `menu` VALUES ('e6726a32f6624065b045be988ddca5ed', 'dba5ef2858e8458490b5afe0256bf486', '添加用户', NULL, 'systems:user:saveUser', 'BUTTON', 0, NULL, ' ENABLED', '9b994c970ad04f998188ce6af005b29a', '2020-09-01 15:42:45', NULL, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `role_status` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 ENDBLED：启用，DISABLED：禁',
  `creator_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifier_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('66678d1410984f1ab2428766b3f2fddc', '超级管理远', '超级管理远', ' ENABLED', '9b994c970ad04f998188ce6af005b29a', '2020-09-01 09:32:43', NULL, NULL);
INSERT INTO `role` VALUES ('77926b05a0f747169025412af000c858', '运营经理', NULL, ' ENABLED', '9b994c970ad04f998188ce6af005b29a', '2020-09-01 15:13:06', NULL, NULL);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色主键ID',
  `meun_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单主键ID',
  `creator_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('14a0374d0a8841c3b1306dcab4f62754', '66678d1410984f1ab2428766b3f2fddc', 'e6726a32f6624065b045be988ddca5ed', '9b994c970ad04f998188ce6af005b29a', '2020-09-02 10:57:22');
INSERT INTO `role_menu` VALUES ('38fefb1914c74f42b7376eafa981b24f', '66678d1410984f1ab2428766b3f2fddc', '1431cbd97cc245d28d24b8cb2098a3a5', '9b994c970ad04f998188ce6af005b29a', '2020-09-02 10:57:22');
INSERT INTO `role_menu` VALUES ('614027e7d49b404ea06aa510d40e9fd4', '66678d1410984f1ab2428766b3f2fddc', '5251705dc5d74abba8b4552b5e288fcb', '9b994c970ad04f998188ce6af005b29a', '2020-09-02 10:57:22');
INSERT INTO `role_menu` VALUES ('6309b6e9a6ee40639e676a6834edda6d', '66678d1410984f1ab2428766b3f2fddc', 'dba5ef2858e8458490b5afe0256bf486', '9b994c970ad04f998188ce6af005b29a', '2020-09-02 10:57:22');
INSERT INTO `role_menu` VALUES ('9a0017be6d754ee0b2c0385b3a58dbf0', '66678d1410984f1ab2428766b3f2fddc', '5fe8560b5bc24669a6cf805830fefc53', '9b994c970ad04f998188ce6af005b29a', '2020-09-02 10:57:22');
INSERT INTO `role_menu` VALUES ('c675f16229b34454a3d8ec218ba47887', '66678d1410984f1ab2428766b3f2fddc', '04e4e4bbd9ff454ea3560b61eb01eff1', '9b994c970ad04f998188ce6af005b29a', '2020-09-02 10:57:22');
INSERT INTO `role_menu` VALUES ('d72cda512165410bbd44e5abd6b61dfc', '66678d1410984f1ab2428766b3f2fddc', '0d954719c3a0427485a6ffe07632b6b6', '9b994c970ad04f998188ce6af005b29a', '2020-09-02 10:57:22');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `account` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐',
  `real_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `mobile` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  `last_login_date` datetime NULL DEFAULT NULL COMMENT '最近登陆时间',
  `creator_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifier_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('9b994c970ad04f998188ce6af005b29a', 'fzw', 'd1b09b112e4793b532c3ed5195080361', 'P9IkXfrQ', '方', '17775704605', '1111', ' ENABLED', NULL, '9b994c970ad04f998188ce6af005b29a', '2020-08-26 11:29:12', NULL, NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键ID',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户主键ID',
  `creator_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifier_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('4785be5bee484e8fbddd2efc21c5ea7f', '66678d1410984f1ab2428766b3f2fddc', '9b994c970ad04f998188ce6af005b29a', '9b994c970ad04f998188ce6af005b29a', '2020-09-02 11:00:06', '', NULL);

SET FOREIGN_KEY_CHECKS = 1;
