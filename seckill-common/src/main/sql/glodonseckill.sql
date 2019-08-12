/*
Navicat MySQL Data Transfer

Source Server         : 106.14.13.61
Source Server Version : 50716
Source Host           : 106.14.13.61:3306
Source Database       : glodonseckill

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-08-12 19:50:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seckill_product
-- ----------------------------
DROP TABLE IF EXISTS `seckill_product`;
CREATE TABLE `seckill_product` (
  `seckill_id` varchar(20) NOT NULL COMMENT '商品库存ID',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '剩余库存数量',
  `start_time` datetime NOT NULL COMMENT '秒杀开始时间',
  `end_time` datetime NOT NULL COMMENT '秒杀结束时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `product_code` varchar(255) NOT NULL DEFAULT '' COMMENT '商品编码',
  `product_price` decimal(10,2) NOT NULL,
  `seckill_price` decimal(10,2) NOT NULL,
  `product_description` varchar(10000) NOT NULL,
  `product_img` varchar(255) NOT NULL,
  `product_state` int(11) NOT NULL COMMENT '-1:未上架，0：已下架，1：已上架',
  `product_stocks` int(11) NOT NULL,
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- ----------------------------
-- Records of seckill_product
-- ----------------------------
INSERT INTO `seckill_product` VALUES ('1000', '8000元秒杀iphone6', '94', '2019-07-01 10:22:57', '2019-08-30 00:00:00', '2019-07-01 10:22:57', '2222', '8888.00', '8000.00', '<div> <br> <img src=\"https://img.alicdn.com/imgextra/i3/470168984/O1CN01RolXTE2GEin6Gu3Op_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i4/470168984/O1CN01NrSdL12GEika7RPaA_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i4/470168984/O1CN01aKBvMV2GEikbCmEyk_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i2/470168984/O1CN014TsMx42GEikdFML5T_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i4/470168984/O1CN01o2iU1E2GEikeHwzDN_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN01te6vKd2GEikcYoYXC_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i2/470168984/O1CN01HQ8Szd2GEikdMGzr1_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN01gFc2sy2GEikelhZyY_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN018Qk3vi2GEikdFM4Sk_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN01oZUajw2GEikbxTcIx_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"> </div>', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565605681760&di=106571da136727b0fff075a3a50dfee6&imgtype=0&src=http%3A%2F%2Fwww.cn314.com%2Fd%2Ffile%2Fdaogou%2F65f44ef69738c554c01937dc48fa102e.jpg', '0', '0');
INSERT INTO `seckill_product` VALUES ('1001', '800元秒杀ipad', '200', '2019-08-12 00:00:00', '2019-08-24 00:00:00', '2019-08-12 10:22:57', '2222', '888.00', '800.00', '<div> <br> <img src=\"https://img.alicdn.com/imgextra/i3/470168984/O1CN01RolXTE2GEin6Gu3Op_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i4/470168984/O1CN01NrSdL12GEika7RPaA_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i4/470168984/O1CN01aKBvMV2GEikbCmEyk_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i2/470168984/O1CN014TsMx42GEikdFML5T_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i4/470168984/O1CN01o2iU1E2GEikeHwzDN_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN01te6vKd2GEikcYoYXC_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i2/470168984/O1CN01HQ8Szd2GEikdMGzr1_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN01gFc2sy2GEikelhZyY_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN018Qk3vi2GEikdFM4Sk_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN01oZUajw2GEikbxTcIx_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"> </div>', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565607097828&di=709cbd13b85d3db19c545b1dc781e5e8&imgtype=0&src=http%3A%2F%2Fpic1.16pic.com%2F00%2F02%2F61%2F16pic_261591_b.jpg', '0', '0');
INSERT INTO `seckill_product` VALUES ('1002', '6600元秒杀mac book pro', '300', '2019-08-12 00:00:00', '2019-08-24 00:00:00', '2019-08-12 10:22:57', '3333', '6666.00', '6600.00', '<div> <br> <img src=\"https://img.alicdn.com/imgextra/i3/470168984/O1CN01RolXTE2GEin6Gu3Op_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i4/470168984/O1CN01NrSdL12GEika7RPaA_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i4/470168984/O1CN01aKBvMV2GEikbCmEyk_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i2/470168984/O1CN014TsMx42GEikdFML5T_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i4/470168984/O1CN01o2iU1E2GEikeHwzDN_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN01te6vKd2GEikcYoYXC_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i2/470168984/O1CN01HQ8Szd2GEikdMGzr1_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN01gFc2sy2GEikelhZyY_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN018Qk3vi2GEikdFM4Sk_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN01oZUajw2GEikbxTcIx_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"> </div>', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565607117054&di=9be5dcc8d3ece5443708d297ee370032&imgtype=0&src=http%3A%2F%2Fi0.hexunimg.cn%2F2012-08-06%2F144430366.jpg', '0', '0');
INSERT INTO `seckill_product` VALUES ('1003', '7000元秒杀iMac', '400', '2019-08-22 00:00:00', '2019-08-24 00:00:00', '2019-08-12 10:22:57', '4444', '7777.00', '7000.00', '<div> <br> <img src=\"https://img.alicdn.com/imgextra/i3/470168984/O1CN01RolXTE2GEin6Gu3Op_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i4/470168984/O1CN01NrSdL12GEika7RPaA_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i4/470168984/O1CN01aKBvMV2GEikbCmEyk_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i2/470168984/O1CN014TsMx42GEikdFML5T_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i4/470168984/O1CN01o2iU1E2GEikeHwzDN_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN01te6vKd2GEikcYoYXC_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i2/470168984/O1CN01HQ8Szd2GEikdMGzr1_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN01gFc2sy2GEikelhZyY_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN018Qk3vi2GEikdFM4Sk_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"><img src=\"https://img.alicdn.com/imgextra/i1/470168984/O1CN01oZUajw2GEikbxTcIx_!!470168984.jpg\" align=\"absmiddle\" class=\"img-ks-lazyload\"> </div>', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565607131079&di=7fd241244c4464405caa7b364d7788b3&imgtype=0&src=http%3A%2F%2Fimagazin.hu%2Fwp-content%2Fuploads%2F2014%2F10%2Fimac5k.jpg', '0', '0');

-- ----------------------------
-- Table structure for success_killed
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品ID',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态标识:-1:无效 0:成功 1:已付款 2:已发货',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  `product_name` varchar(255) NOT NULL,
  `seckill_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`seckill_id`,`user_phone`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

-- ----------------------------
-- Records of success_killed
-- ----------------------------

-- ----------------------------
-- Table structure for user_admin
-- ----------------------------
DROP TABLE IF EXISTS `user_admin`;
CREATE TABLE `user_admin` (
  `root_name` varchar(255) NOT NULL,
  `root_password` varchar(255) NOT NULL,
  `root_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`root_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_admin
-- ----------------------------
INSERT INTO `user_admin` VALUES ('123', '123', '1');
