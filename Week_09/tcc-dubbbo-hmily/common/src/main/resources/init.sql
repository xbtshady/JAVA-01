
SET NAMES utf8mb4;
-- ----------------------------
-- Table structure for wallet
-- ----------------------------
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `amount_cny` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '人民币余额',
  `amount_usb` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '美元余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户钱包' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wallet
-- ----------------------------
INSERT INTO `wallet` VALUES (4, 1, 10, 1);

-- ----------------------------
-- Table structure for wallet_frozen
-- ----------------------------
DROP TABLE IF EXISTS `wallet_frozen`;
CREATE TABLE `wallet_frozen`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `money_cny` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '冻结人民币',
  `money_usb` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '冻结美元',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户冻结钱包' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wallet_frozen
-- ----------------------------
INSERT INTO `wallet_frozen` VALUES (2, 1, 0, 0);

