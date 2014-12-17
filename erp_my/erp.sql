/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.0.96-community-nt : Database - erp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`erp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `erp`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `uuid` bigint(20) NOT NULL auto_increment,
  `name` varchar(10) NOT NULL,
  `departNum` varchar(10) default NULL,
  `phone` varchar(12) default NULL,
  PRIMARY KEY  (`name`),
  UNIQUE KEY `uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`uuid`,`name`,`departNum`,`phone`) values (12,'美工部',NULL,'12344'),(4,'设计部','3','2342'),(11,'速度发顺丰',NULL,'2342'),(1,'销售部','02','123123123');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `uuid` bigint(20) NOT NULL auto_increment,
  `userName` varchar(15) default NULL,
  `userPass` varchar(32) default NULL,
  `name` varchar(28) default NULL,
  `sex` int(2) default NULL,
  `birthday` bigint(20) default NULL,
  `phone` varchar(15) default NULL,
  `email` varchar(20) default NULL,
  `lastLoginTime` bigint(20) default NULL,
  `lastLoginIp` varchar(20) default NULL,
  `loginTimes` int(10) default NULL,
  `departM` bigint(3) default NULL,
  `addres` varchar(56) default NULL,
  UNIQUE KEY `uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

insert  into `employee`(`uuid`,`userName`,`userPass`,`name`,`sex`,`birthday`,`phone`,`email`,`lastLoginTime`,`lastLoginIp`,`loginTimes`,`departM`,`addres`) values (10,'lisi','21232f297a57a5a743894a0e4a801fc3','李四',0,1417622400000,'12323132','aefsf@',1418180422925,'127.0.0.1',7,4,'速度发顺丰'),(12,'zhangsan','21232f297a57a5a743894a0e4a801fc3','张三',1,1418227200000,'123123','sdfsf@',NULL,NULL,1,11,'sdfsfs'),(16,'admin','202cb962ac59075b964b07152d234b70','admin',0,1418227200000,'121212','ssqsqw',1418786023778,'127.0.0.1',269,1,'sqwsqws'),(17,'wangwu','21232f297a57a5a743894a0e4a801fc3','wang',0,1417363200000,'234234','sfs@',NULL,NULL,1,11,''),(18,'zhaoliu','21232f297a57a5a743894a0e4a801fc3','zhaoliu',1,1417363200000,'','ssfsf',NULL,NULL,1,4,'sfsf'),(19,'zhaoqi','21232f297a57a5a743894a0e4a801fc3','赵其',1,1417536000000,'2342342','123123@',NULL,NULL,1,1,'速度放松放松'),(20,'fff','','',0,NULL,'','',NULL,NULL,NULL,1,'');

/*Table structure for table `employee_role` */

DROP TABLE IF EXISTS `employee_role`;

CREATE TABLE `employee_role` (
  `uuid` bigint(20) NOT NULL auto_increment,
  `employee_uuid` bigint(20) default NULL,
  `role_uuid` bigint(20) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `employee_role` */

insert  into `employee_role`(`uuid`,`employee_uuid`,`role_uuid`) values (8,19,4),(9,19,3),(13,12,3),(14,12,2),(16,18,3),(17,18,4),(19,18,2),(30,17,2),(31,10,8),(32,10,3),(33,16,2);

/*Table structure for table `erp_order` */

DROP TABLE IF EXISTS `erp_order`;

CREATE TABLE `erp_order` (
  `uuid` bigint(20) NOT NULL auto_increment,
  `orderNum` varchar(32) NOT NULL,
  `creator` bigint(20) NOT NULL,
  `createTime` bigint(20) NOT NULL,
  `checker` bigint(20) default NULL,
  `checkTime` bigint(20) default NULL,
  `completor` bigint(20) default NULL,
  `endTime` bigint(20) default NULL,
  `supplierUuid` bigint(20) NOT NULL,
  `orderType` int(1) NOT NULL,
  `type` int(3) NOT NULL,
  `totalNum` int(11) NOT NULL,
  `totalPrice` double(10,2) NOT NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `erp_order` */

insert  into `erp_order`(`uuid`,`orderNum`,`creator`,`createTime`,`checker`,`checkTime`,`completor`,`endTime`,`supplierUuid`,`orderType`,`type`,`totalNum`,`totalPrice`) values (24,'dbc83b05379e255219d71d7b8b86ef4b',16,1418536687174,16,1418550316864,16,1418785222897,4,1,232,3,220.00),(25,'9a455c61b4edeb318e5d7a9aec0328a4',16,1418536701502,16,1418552624808,16,1418785311626,4,1,232,7,1020.00),(26,'8150c06a1bce80a12d5b290c48e60742',16,1418552790599,16,1418621305578,16,1418785323363,6,1,232,8,680.00),(27,'c05d21753dfce6bca763f032cf6add7e',16,1418618630271,16,1418621626491,19,NULL,7,1,121,5,3400.00),(28,'8c7f2b6688803af4ea05e5d91725e096',16,1418619380519,16,1418621988729,19,NULL,4,1,121,1,60.00),(29,'67170ac59de56ade5f6e494782d64cee',16,1418622121127,16,1418622129923,19,NULL,4,1,121,5,420.00),(30,'3d7a61f04683ade5c28e01b41bfeee15',16,1418782189990,16,1418782204972,16,1418782520518,4,1,121,15,1700.00);

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `uuid` bigint(20) NOT NULL auto_increment,
  `name` varchar(30) NOT NULL,
  `origin` varchar(30) NOT NULL,
  `producer` varchar(30) NOT NULL,
  `unit` varchar(30) NOT NULL,
  `inPrice` double(10,2) NOT NULL,
  `outPrice` double(10,2) NOT NULL,
  `goodsTypeUuid` bigint(20) NOT NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`uuid`,`name`,`origin`,`producer`,`unit`,`inPrice`,`outPrice`,`goodsTypeUuid`) values (2,'苹果','广东','苹果公司','箱',60.00,150.00,7),(3,'梨','广西','梨公司','箱',100.00,200.00,7),(4,'酸酸乳','广东东莞','蒙牛集团','件',100.00,200.00,10),(5,'奶冰棍','黑龙江哈尔滨','伊利集团','件',60.00,300.00,9),(6,'羊绒衫上衣','鄂尔多斯','鄂尔多斯','包',500.00,1000.00,11),(7,'女士羊绒衫','包头','鄂尔多斯','包',800.00,2000.23,12),(10,'奶糖','内蒙古呼和浩特','伊利集团','箱',200.00,500.00,8),(11,'水蜜桃','江南','江南水果厂','箱',300.00,600.00,7),(12,'甜点','北京','北京甜点厂','箱',200.00,500.00,8);

/*Table structure for table `goodstype` */

DROP TABLE IF EXISTS `goodstype`;

CREATE TABLE `goodstype` (
  `uuid` bigint(10) NOT NULL auto_increment,
  `name` varchar(18) default NULL,
  `supplier_uuid` bigint(10) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `goodstype` */

insert  into `goodstype`(`uuid`,`name`,`supplier_uuid`) values (7,'水果类',4),(8,'甜食食品',4),(9,'冰棍',6),(10,'酸奶',6),(11,'男士上衣',7),(12,'女士上衣',7);

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `uuid` bigint(10) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `url` varchar(255) default NULL,
  `parent_uuid` bigint(10) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=707 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`uuid`,`name`,`url`,`parent_uuid`) values (1,'系统菜单','-',0),(100,'商品管理','-',1),(101,'供应商','supplier_list',100),(102,'商品类别','goodsType_list',100),(103,'商品','goods_list',100),(200,'采购管理','sfsf',1),(201,'采购订单','order_list',200),(202,'采购退货',NULL,200),(203,'采购审批','order_inApproveList',200),(300,'销售管理','sdfsf',1),(301,'销售订单',NULL,300),(302,'销售退货',NULL,300),(303,'销售审批',NULL,300),(400,'商品运输','sdfs',1),(401,'运输任务指配','transport_transportList',400),(402,'运输任务查询','transport_tasks',400),(500,'仓库管理','sfs',1),(501,'库存查询','storeDetail_list',500),(502,'入库','order_inStoreList',500),(503,'出库',NULL,500),(504,'仓库操作明细','storeOperate_list',500),(600,'报表中心','sfsf',1),(601,'进货报表',NULL,600),(602,'销售报表',NULL,600),(603,'仓库报表',NULL,600),(700,'基础维护',NULL,1),(701,'部门维护','department_list',700),(702,'员工维护','employee_list',700),(703,'角色维护','role_list',700),(704,'资源维护','resource_list',700),(705,'菜单维护','menu_list',700),(706,'仓库管理','store_list',700);

/*Table structure for table `orderdetail` */

DROP TABLE IF EXISTS `orderdetail`;

CREATE TABLE `orderdetail` (
  `uuid` bigint(20) NOT NULL auto_increment,
  `goodsUuid` bigint(20) NOT NULL,
  `price` double(10,2) NOT NULL,
  `num` int(11) NOT NULL,
  `orderUuid` bigint(20) NOT NULL,
  `surplus` int(10) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

/*Data for the table `orderdetail` */

insert  into `orderdetail`(`uuid`,`goodsUuid`,`price`,`num`,`orderUuid`,`surplus`) values (53,2,60.00,2,24,0),(54,3,100.00,1,24,0),(55,3,100.00,2,25,0),(56,11,300.00,1,25,0),(57,2,60.00,1,25,0),(58,5,60.00,3,26,0),(59,4,100.00,5,26,0),(60,6,500.00,2,27,2),(61,7,800.00,3,27,3),(62,2,60.00,1,28,1),(63,3,100.00,3,29,3),(64,2,60.00,2,29,2),(65,3,100.00,6,30,6),(66,10,200.00,4,30,4),(67,2,60.00,5,30,5);

/*Table structure for table `resource` */

DROP TABLE IF EXISTS `resource`;

CREATE TABLE `resource` (
  `uuid` bigint(3) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `url` varchar(255) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `resource` */

insert  into `resource`(`uuid`,`name`,`url`) values (1,'员工列表','org.erp.auth.employee.action.EmployeeAction.list'),(2,'添加/修改员工','org.erp.auth.employee.action.EmployeeAction.save'),(3,'删除员工','org.erp.auth.employee.action.EmployeeAction.delete'),(4,'提交编辑员工','org.erp.auth.employee.action.EmployeeAction.input');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `uuid` bigint(3) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `number` varchar(10) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`uuid`,`name`,`number`) values (2,'总裁','0101'),(3,'员工',''),(4,'主管',''),(7,'销售总监','0707'),(8,'部门总监','0202'),(9,'HR主管','0404');

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `uuid` bigint(10) NOT NULL auto_increment,
  `role_uuid` bigint(10) default NULL,
  `menu_uuid` bigint(10) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

/*Data for the table `role_menu` */

insert  into `role_menu`(`uuid`,`role_uuid`,`menu_uuid`) values (32,9,703),(33,9,702),(34,9,704),(35,9,705),(67,2,702),(68,2,401),(69,2,1),(70,2,102),(71,2,504),(72,2,100),(73,2,103),(74,2,400),(75,2,700),(76,2,101),(77,2,201),(78,2,402),(79,2,600),(80,2,601),(81,2,200),(82,2,701),(83,2,703),(84,2,500),(85,2,203),(86,2,704),(87,2,502),(88,2,706),(89,2,705),(90,2,501);

/*Table structure for table `role_resource` */

DROP TABLE IF EXISTS `role_resource`;

CREATE TABLE `role_resource` (
  `uuid` bigint(3) NOT NULL auto_increment,
  `role_uuid` bigint(20) default NULL,
  `resource_uuid` bigint(20) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `role_resource` */

insert  into `role_resource`(`uuid`,`role_uuid`,`resource_uuid`) values (8,8,2),(18,4,4),(19,4,2),(20,4,1),(21,7,4),(22,7,2),(23,7,1),(29,3,1),(34,9,1),(35,9,2),(40,2,2),(41,2,4),(42,2,1),(43,2,3);

/*Table structure for table `store` */

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `uuid` bigint(10) NOT NULL auto_increment,
  `name` varchar(18) default NULL,
  `address` varchar(20) default NULL,
  `empUuid` bigint(10) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `store` */

insert  into `store`(`uuid`,`name`,`address`,`empUuid`) values (4,'食品仓库','北京',19),(5,'服装仓库','太原',16);

/*Table structure for table `storedetail` */

DROP TABLE IF EXISTS `storedetail`;

CREATE TABLE `storedetail` (
  `uuid` bigint(10) NOT NULL auto_increment,
  `num` int(10) default NULL,
  `storeM` bigint(10) default NULL,
  `goodsM` bigint(10) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `storedetail` */

insert  into `storedetail`(`uuid`,`num`,`storeM`,`goodsM`) values (10,3,4,3),(11,3,4,2),(12,1,4,11),(13,3,4,5),(14,5,4,4);

/*Table structure for table `storeoperate` */

DROP TABLE IF EXISTS `storeoperate`;

CREATE TABLE `storeoperate` (
  `uuid` bigint(10) NOT NULL auto_increment,
  `num` int(10) default NULL,
  `operTime` bigint(50) default NULL,
  `type` int(3) default NULL,
  `storeM` bigint(10) default NULL,
  `empM` bigint(10) default NULL,
  `goodsM` bigint(10) default NULL,
  `orderDetailM` bigint(10) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `storeoperate` */

insert  into `storeoperate`(`uuid`,`num`,`operTime`,`type`,`storeM`,`empM`,`goodsM`,`orderDetailM`) values (21,1,1418785215817,1,4,16,3,54),(22,1,1418785221854,1,4,16,2,53),(23,1,1418785222894,1,4,16,2,53),(24,1,1418785306042,1,4,16,11,56),(25,2,1418785309000,1,4,16,3,55),(26,1,1418785311616,1,4,16,2,57),(27,3,1418785321237,1,4,16,5,58),(28,5,1418785323361,1,4,16,4,59);

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `uuid` bigint(10) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `address` varchar(20) default NULL,
  `phone` varchar(18) default NULL,
  `contact` varchar(18) default NULL,
  `needs` int(1) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `supplier` */

insert  into `supplier`(`uuid`,`name`,`address`,`phone`,`contact`,`needs`) values (4,'北大街食品厂','西安北大街','12344545','老李',0),(5,'包头百货店','包头青山区','4443423222','老赵',1),(6,'呼市奶制品超市','南茶坊','1254644332','老王',1),(7,'鄂尔多斯羊绒衫专卖店','鄂尔多斯西大街中段','12323234','王活明',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
