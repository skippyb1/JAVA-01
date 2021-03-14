/*
 Navicat Premium Data Transfer

 Source Server         : local5.7
 Source Server Type    : MySQL
 Source Server Version : 50709
 Source Host           : localhost:3306
 Source Schema         : week08-ds0

 Target Server Type    : MySQL
 Target Server Version : 50709
 File Encoding         : 65001

 Date: 13/03/2021 19:58:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_order_info1
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info1`;
CREATE TABLE `s_order_info1`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info10
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info10`;
CREATE TABLE `s_order_info10`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info11
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info11`;
CREATE TABLE `s_order_info11`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info12
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info12`;
CREATE TABLE `s_order_info12`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info13
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info13`;
CREATE TABLE `s_order_info13`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info14
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info14`;
CREATE TABLE `s_order_info14`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info15
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info15`;
CREATE TABLE `s_order_info15`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info16
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info16`;
CREATE TABLE `s_order_info16`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info2
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info2`;
CREATE TABLE `s_order_info2`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info3
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info3`;
CREATE TABLE `s_order_info3`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info4
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info4`;
CREATE TABLE `s_order_info4`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info5
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info5`;
CREATE TABLE `s_order_info5`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info6
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info6`;
CREATE TABLE `s_order_info6`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info7
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info7`;
CREATE TABLE `s_order_info7`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info8
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info8`;
CREATE TABLE `s_order_info8`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_order_info9
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info9`;
CREATE TABLE `s_order_info9`  (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总价',
  `pay_price` int(255) NULL DEFAULT NULL COMMENT '实付款',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_product_info
-- ----------------------------
DROP TABLE IF EXISTS `s_product_info`;
CREATE TABLE `s_product_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `origin_price` decimal(16, 2) NULL DEFAULT NULL COMMENT '原价',
  `type_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品号',
  `discount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '折扣(1%-100%)',
  `producer` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生产商',
  `shop` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商铺',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '商品状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for s_user_info
-- ----------------------------
DROP TABLE IF EXISTS `s_user_info`;
CREATE TABLE `s_user_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `login` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  `idno` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `age` tinyint(3) NULL DEFAULT NULL COMMENT '年龄',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别',
  `phone` bigint(11) NULL DEFAULT NULL COMMENT '手机号',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
