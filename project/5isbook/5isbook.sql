-- MySQL dump 10.13  Distrib 5.5.27, for Win32 (x86)
--
-- Host: localhost    Database: 5isbook
-- ------------------------------------------------------
-- Server version	5.5.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `long_tel` varchar(32) DEFAULT NULL,
  `real_name` varchar(23) NOT NULL,
  `short_tel` varchar(32) DEFAULT NULL,
  `user_account` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBB979BF4701ED851` (`user_account`),
  CONSTRAINT `FKBB979BF4701ED851` FOREIGN KEY (`user_account`) REFERENCES `user` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'华山区21栋206','15902078207','张宇','628207','fuck@qq.com');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attribute`
--

DROP TABLE IF EXISTS `attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display_name` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `document_id` int(11) DEFAULT NULL,
  `raw_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC7AA9CB844DC55` (`document_id`),
  KEY `FKC7AA9C35D3BB5F` (`raw_id`),
  CONSTRAINT `FKC7AA9C35D3BB5F` FOREIGN KEY (`raw_id`) REFERENCES `raw` (`id`),
  CONSTRAINT `FKC7AA9CB844DC55` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute`
--

LOCK TABLES `attribute` WRITE;
/*!40000 ALTER TABLE `attribute` DISABLE KEYS */;
INSERT INTO `attribute` VALUES (1,NULL,'publisher',1,1),(2,NULL,'author-intro',1,2),(3,'图书图片(大)','large_image',1,1),(4,'图书图片(中)','medium_image',1,1),(5,'页数','pages',1,1),(6,'作者','author',1,1),(7,'isbn','isbn',1,1),(8,'书名','title',1,1),(9,'详情','summary',1,2),(10,'原始图书图片(小)','image',1,1),(11,'出版时间','pubdate',1,1);
/*!40000 ALTER TABLE `attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `big_text_entity_value`
--

DROP TABLE IF EXISTS `big_text_entity_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `big_text_entity_value` (
  `content` text,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4B73FE6851FA660E` (`id`),
  CONSTRAINT `FK4B73FE6851FA660E` FOREIGN KEY (`id`) REFERENCES `entity_value` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `big_text_entity_value`
--

LOCK TABLES `big_text_entity_value` WRITE;
/*!40000 ALTER TABLE `big_text_entity_value` DISABLE KEYS */;
INSERT INTO `big_text_entity_value` VALUES ('丁燕，诗人，作家。二十世纪70年代生于新疆哈密，汉族。毕业于中国人民大学新闻学院本科，新疆师范大学人文学院研究生。诗作入选百种诗歌选本。出版诗集《午夜葡萄园》，曾获得多种诗歌奖项，2011年获第三届“中国当代十大杰出青年诗人”。\n同时从事小说、散文、评论创作，有长篇小说《木兰》、散文集《和生命约会四十周》、《王洛宾音乐地图》等。\n2010年迁居广东东莞， 专事写作，系东莞文学院签约作家。',141),('讲述80后、90后打工女孩的寻梦历程\n追问现代工业洪流中的个体命运\n在东莞，这个中国最大的制造业基地，数百万女孩从乡村奔赴这里，固定在流水线旁，日夜重复着机械的劳动。\n在工厂，女孩们每周工作六天，每天工作十一个小时。\n在流水线上，人是固定的螺丝钉，每个身体应该采取的姿势，都被清晰而准确地规定好。一个简单的动作，一百次地重复，一万次地重复 ，一万次乘以一万次地重复。一切围绕着机器旋转，人成为无意识的附庸。\n2011年，为深入理解东莞工厂女孩的生活，诗人丁燕先后在两家电子厂、一家注塑厂打工，经历了200天最真实、最深刻的工厂生活，记录下这些工厂女孩青春、爱情与梦想的萌生与破灭，也是对裹挟了无数人命运的现代工业洪流的追问。\n只有亲历过工厂生活的人，才能写下这样细致、敏感、真实的记录。',143),('加西亚?马尔克斯（Gabriel García Márquez）\n1927年出生于哥伦比亚马格达莱纳海滨小镇阿拉卡塔卡。童年与外祖父母一起生活。1936年随父母迁居苏克雷。1947年考入波哥大国立大学。1948年因内战辍学，进入报界。五十年代开始出版文学作品。1961年《没有人给他写信的上校》问世。1967年出版《百年孤独》。1982年获诺贝尔文学奖。\n加西亚?马尔克斯中文官方网站 http://www.douban.com/marquez',148),('★ 五十六年了，上校唯一做过的事情就是等待。\n★《没有人给他写信的上校》我写了九遍，它是我所有作品中最无懈可击的，可以面对任何敌人。 ——加西亚?马尔克斯\n★《百年孤独》在问世之前就已经被我自己超越了。事实上，我认为我最好的作品是《没有人给他写信的上校》。  ——加西亚?马尔克斯\n★《没有人给 他写信的上校》是一部完美杰作。——《2666》作者罗贝托?波拉尼奥\n《没有人给他写信的上校》是加西亚?马尔克斯最负盛名的中篇小说，在世界文学史上与海明威的《老人与海》齐名。马尔克斯自认《没有人给他写信的上校》的艺术成就要超越《百年孤独》。\n《没有人给他写信的上校》讲述了一位七十多岁的老上校，五十六年来一直等待退伍金的绝望生活。这位著名的“没有人给他写信的上校”，被誉为“20世纪小说中最难忘的人物”，小说结尾也被誉为“所有文学作品中最完美的一段”。',149);
/*!40000 ALTER TABLE `big_text_entity_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `basic_code` varchar(128) NOT NULL,
  `display_name` varchar(128) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK302BCFEE51E3249` (`parent_id`),
  CONSTRAINT `FK302BCFEE51E3249` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=330 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'1','图书',NULL),(2,'1.2','社会科学',1),(3,'1.2.4','社会',2),(4,'1.2.4.3','人类学',3),(5,'1.2.4.3.1','文化人类学',4),(6,'1.2.4.3.3','心理学',4),(7,'1.2.4.3.4','人口学',4),(8,'1.2.4.3.2','民族学',4),(9,'1.2.4.1','教育',3),(10,'1.2.4.1.3','信息与知识传播',9),(11,'1.2.4.1.2','教育',9),(12,'1.2.4.1.1','语言文字',9),(13,'1.2.4.2','社会学',3),(14,'1.2.4.2.1','公共关系',13),(15,'1.2.4.2.5','统计学',13),(16,'1.2.4.2.4','社会科学总论',13),(17,'1.2.4.2.2','社会学',13),(18,'1.2.4.2.3','社会保障',13),(19,'1.2.2','经济金融',2),(20,'1.2.2.3','经济学',19),(21,'1.2.2.3.5','经济学理论',20),(22,'1.2.2.3.3','中国经济',20),(23,'1.2.2.3.4','审计',20),(24,'1.2.2.3.2','经济史',20),(25,'1.2.2.3.1','会计',20),(26,'1.2.2.3.6','保险',20),(27,'1.2.2.1','世界经济',19),(28,'1.2.2.1.2','国际金融',27),(29,'1.2.2.1.3','西方经济学',27),(30,'1.2.2.1.4','世界经济',27),(31,'1.2.2.1.1','国际贸易',27),(32,'1.2.2.2','货币学',19),(33,'1.2.2.2.3','金融与货币',32),(34,'1.2.2.2.2','财经税收',32),(35,'1.2.2.2.1','投资理财',32),(36,'1.2.1','管理',2),(37,'1.2.1.2','公共管理',36),(38,'1.2.1.2.4','社会医学与卫生事业管理',37),(39,'1.2.1.2.2','行政管理',37),(40,'1.2.1.2.6','社会保障',37),(41,'1.2.1.2.3','教育经济与管理',37),(42,'1.2.1.2.5','土地资源管理',37),(43,'1.2.1.2.1','公共事业管理',37),(44,'1.2.1.4','图书馆与情报_档案管理',36),(45,'1.2.1.4.2','档案学',44),(46,'1.2.1.4.3','情报学',44),(47,'1.2.1.4.1','图书馆学',44),(48,'1.2.1.3','农业经济管理',36),(49,'1.2.1.3.2','旅游管理',48),(50,'1.2.1.3.3','林业经济管理',48),(51,'1.2.1.3.1','农业经济管理',48),(52,'1.2.1.1','企业管理',36),(53,'1.2.1.1.6','工商管理',52),(54,'1.2.1.1.2','市场营销',52),(55,'1.2.1.1.8','技术经济及管理',52),(56,'1.2.1.1.7','会计学',52),(57,'1.2.1.1.5','财务管理',52),(58,'1.2.1.1.4','企业文化',52),(59,'1.2.1.1.3','战略管理',52),(60,'1.2.1.1.1','人力资源管理',52),(61,'1.2.3','法律',2),(62,'1.2.3.2','国际',61),(63,'1.2.3.2.2','外国法律与港澳台法律',62),(64,'1.2.3.2.1','国际法',62),(65,'1.2.3.4','法学理论',61),(66,'1.2.3.4.3','法律法规',65),(67,'1.2.3.4.1','法学史',65),(68,'1.2.3.4.2','法理学',65),(69,'1.2.3.3','商法',61),(70,'1.2.3.3.1','经济法',69),(71,'1.2.3.1','民事与行政',61),(72,'1.2.3.1.4','行政法',71),(73,'1.2.3.1.5','诉讼法',71),(74,'1.2.3.1.2','刑法',71),(75,'1.2.3.1.1','民法',71),(76,'1.2.3.1.3','犯罪学',71),(77,'1.3','文学',1),(78,'1.3.1','语言学习',77),(79,'1.3.1.1','专业英语',78),(80,'1.3.1.1.6','服务业英语',79),(81,'1.3.1.1.9','科技英语',79),(82,'1.3.1.1.10','法律英语',79),(83,'1.3.1.1.12','新闻英语',79),(84,'1.3.1.1.3','金融英语',79),(85,'1.3.1.1.4','求职面试英语',79),(86,'1.3.1.1.7','办公室英语',79),(87,'1.3.1.1.8','医疗英语',79),(88,'1.3.1.1.5','旅游业英语',79),(89,'1.3.1.1.11','计算机英语',79),(90,'1.3.1.1.13','其他工种英语',79),(91,'1.3.1.1.1','商务英语',79),(92,'1.3.1.1.2','经贸英语',79),(93,'1.3.1.4','其他语种语法',78),(94,'1.3.1.4.3','词汇',93),(95,'1.3.1.4.1','口语',93),(96,'1.3.1.4.9','考试',93),(97,'1.3.1.4.10','实用类',93),(98,'1.3.1.4.7','翻译',93),(99,'1.3.1.4.8','语音',93),(100,'1.3.1.4.2','听力',93),(101,'1.3.1.4.6','写作',93),(102,'1.3.1.4.5','阅读',93),(103,'1.3.1.4.4','语法',93),(104,'1.3.1.2','英语考试',78),(105,'1.3.1.2.13','全国公共英语等级考试',104),(106,'1.3.1.2.12','GCT',104),(107,'1.3.1.2.14','高校英语应用能力考试',104),(108,'1.3.1.2.15','剑桥商业英语考试',104),(109,'1.3.1.2.6','雅思',104),(110,'1.3.1.2.9','MBA',104),(111,'1.3.1.2.11','SAT',104),(112,'1.3.1.2.19','博士入学考试',104),(113,'1.3.1.2.10','GMAT',104),(114,'1.3.1.2.17','职称考试',104),(115,'1.3.1.2.18','专升本考试',104),(116,'1.3.1.2.16','翻译考试',104),(117,'1.3.1.2.2','大学英语六级',104),(118,'1.3.1.2.8','托业',104),(119,'1.3.1.2.7','托福',104),(120,'1.3.1.2.4','专业英语八级',104),(121,'1.3.1.2.5','研究生入学考试',104),(122,'1.3.1.2.3','专业英语四级',104),(123,'1.3.1.2.1','大学英语四级',104),(124,'1.3.1.3','英语语法',78),(125,'1.3.1.3.10','方法、技巧',124),(126,'1.3.1.3.7','翻译',124),(127,'1.3.1.3.2','听力',124),(128,'1.3.1.3.9','短句、谚语',124),(129,'1.3.1.3.11','英语书法字帖',124),(130,'1.3.1.3.3','词汇',124),(131,'1.3.1.3.6','写作',124),(132,'1.3.1.3.8','语音',124),(133,'1.3.1.3.5','阅读',124),(134,'1.3.1.3.4','语法',124),(135,'1.3.1.3.1','口语',124),(136,'1.3.2','艺术',77),(137,'1.3.2.5','戏剧影视文学',136),(138,'1.3.2.5.4','当代电影艺术导论',137),(139,'1.3.2.5.3','电视画面编辑',137),(140,'1.3.2.5.7','现代动画概论',137),(141,'1.3.2.5.1','电视摄像',137),(142,'1.3.2.5.8','摄影',137),(143,'1.3.2.5.6','三维动画设计与制作',137),(144,'1.3.2.5.5','外国电影史',137),(145,'1.3.2.5.2','摄影基础',137),(146,'1.3.2.4','建筑与环境设计',136),(147,'1.3.2.4.9','外国建筑史',146),(148,'1.3.2.4.6','建筑、形式空间与秩序',146),(149,'1.3.2.4.3','景观建筑 手绘效果图表现技法',146),(150,'1.3.2.4.4','公共建筑设计原理',146),(151,'1.3.2.4.2','幼儿园建筑设计',146),(152,'1.3.2.4.8','建筑构造（下册）',146),(153,'1.3.2.4.1','建筑制图',146),(154,'1.3.2.4.7','建筑结构选型',146),(155,'1.3.2.4.5','图解思考-建筑表现技法（第三版）',146),(156,'1.3.2.4.10','室内环境设计——理论与入门方法',146),(157,'1.3.2.6','园林工程设计',136),(158,'1.3.2.6.10','草坪学',157),(159,'1.3.2.6.8','园林树木学',157),(160,'1.3.2.6.6','景观设计',157),(161,'1.3.2.6.2','园林工程',157),(162,'1.3.2.6.3','园林规划设计',157),(163,'1.3.2.6.9','园林计算机辅助设计',157),(164,'1.3.2.6.4','城市公园景观设计',157),(165,'1.3.2.6.5','城市绿地系统规划',157),(166,'1.3.2.6.1','实用园林绘画技法',157),(167,'1.3.2.6.7','园林花卉学',157),(168,'1.3.2.1','音乐',136),(169,'1.3.2.1.1','音乐表演',168),(170,'1.3.2.1.2','舞台音乐剧',168),(171,'1.3.2.1.3','音乐编导与创作',168),(172,'1.3.2.2','服装设计',136),(173,'1.3.2.2.6','中西服装发展史',172),(174,'1.3.2.2.5','服装画技法',172),(175,'1.3.2.2.4','服装设计结构',172),(176,'1.3.2.2.7','成衣工艺学',172),(177,'1.3.2.2.3','西方服装史',172),(178,'1.3.2.2.2','西方服装史',172),(179,'1.3.2.2.1','中国服装史',172),(180,'1.3.2.3','多媒体网页设计',136),(181,'1.3.2.3.1','Photoshop图形图像处理',180),(182,'1.3.2.3.2','Flash cs4 多媒体教学经典教材',180),(183,'1.3.2.3.4','遥感数字图像处理',180),(184,'1.3.2.3.3','数字图像处理技术',180),(185,'1.4','工具',1),(186,'1.4.1','工具书',185),(187,'1.4.1.1','研究生入学考试',186),(188,'1.4.1.10','字典',186),(189,'1.4.1.4','法律考试',186),(190,'1.4.1.8','艺术类考试',186),(191,'1.4.1.5','计算机考试',186),(192,'1.4.1.2','自学考试',186),(193,'1.4.1.7','医学类考试',186),(194,'1.4.1.6','公务员考试',186),(195,'1.4.1.9','资格-职称考试',186),(196,'1.4.1.3','汉语考试',186),(197,'1.1','自然科学',1),(198,'1.1.1','计算机网络',197),(199,'1.1.1.5','软件工程',198),(200,'1.1.1.5.8','CMM',199),(201,'1.1.1.5.4','软件过程',199),(202,'1.1.1.5.6','软件架构',199),(203,'1.1.1.5.7','UML',199),(204,'1.1.1.5.3','软件方法',199),(205,'1.1.1.5.5','面向对象',199),(206,'1.1.1.5.1','软件工程理论',199),(207,'1.1.1.5.2','需求分析',199),(208,'1.1.1.5.9','质量测试维护',199),(209,'1.1.1.8','搜索与管理',198),(210,'1.1.1.8.1','网络管理',209),(211,'1.1.1.8.2','搜索引擎',209),(212,'1.1.1.6','操作系统',198),(213,'1.1.1.6.1','操作系统理论',212),(214,'1.1.1.6.6','移动终端',212),(215,'1.1.1.6.5','DOS',212),(216,'1.1.1.6.2','Microsoft Windows',212),(217,'1.1.1.6.4','max os',212),(218,'1.1.1.6.3','linux/unix',212),(219,'1.1.1.2','编程开发',198),(220,'1.1.1.2.3','语言与开发工具',219),(221,'1.1.1.2.4','web开发',219),(222,'1.1.1.2.5','游戏开发',219),(223,'1.1.1.2.1','编译原理与工具',219),(224,'1.1.1.2.2','算法与数据结构',219),(225,'1.1.1.3','计算机理论',198),(226,'1.1.1.3.3','计算机数学',225),(227,'1.1.1.3.2','计算机结构、设计与制造',225),(228,'1.1.1.3.1','计算机基础理论',225),(229,'1.1.1.1','多媒体与网页开发',198),(230,'1.1.1.1.1','计算机图形学',229),(231,'1.1.1.1.7','网页制作工具',229),(232,'1.1.1.1.3','平面设计',229),(233,'1.1.1.1.4','网页设计',229),(234,'1.1.1.1.2','计算机多媒体',229),(235,'1.1.1.1.6','网站建立与管理',229),(236,'1.1.1.1.5','课件制作',229),(237,'1.1.1.9','安全加密',198),(238,'1.1.1.9.4','计算机病毒',237),(239,'1.1.1.9.3','计算机网络安全',237),(240,'1.1.1.9.5','加密与解密',237),(241,'1.1.1.9.1','计算机设备安全',237),(242,'1.1.1.9.2','计算机数据安全',237),(243,'1.1.1.7','网络通讯',198),(244,'1.1.1.7.4','WebServer',243),(245,'1.1.1.7.2','网络组建',243),(246,'1.1.1.7.3','网络协议',243),(247,'1.1.1.7.1','计算机网络理论',243),(248,'1.1.1.4','数据库',198),(249,'1.1.1.4.2','数据库类型',248),(250,'1.1.1.4.6','SQL语言',248),(251,'1.1.1.4.3','数据仓库、数据挖掘',248),(252,'1.1.1.4.1','数据库理论',248),(253,'1.1.1.4.5','工具',248),(254,'1.1.1.4.4','数据库设计与管理',248),(255,'1.1.3','医学卫生',197),(256,'1.1.3.2','药学',255),(257,'1.1.3.2.4','医学工具书',256),(258,'1.1.3.2.3','医学理论',256),(259,'1.1.3.2.2','西药',256),(260,'1.1.3.2.1','中药',256),(261,'1.1.3.1','基础医学',255),(262,'1.1.3.1.5','微生物学',261),(263,'1.1.3.1.6','寄生虫学',261),(264,'1.1.3.1.2','生物学',261),(265,'1.1.3.1.4','免疫学',261),(266,'1.1.3.1.7','解剖学',261),(267,'1.1.3.1.1','病理学',261),(268,'1.1.3.1.3','生理学',261),(269,'1.1.3.1.8','组织学',261),(270,'1.1.3.1.9','胚胎学',261),(271,'1.1.3.4','护理学',255),(272,'1.1.3.4.2','医学/药物教材',271),(273,'1.1.3.4.1','中医',271),(274,'1.1.3.4.7','基础医学',271),(275,'1.1.3.4.3','药学',271),(276,'1.1.3.4.4','其他临床医学',271),(277,'1.1.3.4.5','内科学',271),(278,'1.1.3.4.6','医技学',271),(279,'1.1.3.3','中医学',255),(280,'1.1.3.3.3','针灸',279),(281,'1.1.3.3.5','推拿、按摩',279),(282,'1.1.3.3.6','拔罐、刮痧',279),(283,'1.1.3.3.1','中医临床',279),(284,'1.1.3.3.7','中医经典古籍',279),(285,'1.1.3.3.4','中西医结合',279),(286,'1.1.3.3.2','基础理论',279),(287,'1.1.3.5','临床医学',255),(288,'1.1.3.5.5','眼科学',287),(289,'1.1.3.5.3','精神病、心理学病',287),(290,'1.1.3.5.8','特种医学',287),(291,'1.1.3.5.4','口腔科学',287),(292,'1.1.3.5.2','肿瘤学',287),(293,'1.1.3.5.7','耳鼻咽喉科学',287),(294,'1.1.3.5.9','麻醉学',287),(295,'1.1.3.5.10','性病科',287),(296,'1.1.3.5.6','皮肤学',287),(297,'1.1.3.5.1','临床医学理论',287),(298,'1.1.2','科技工程',197),(299,'1.1.2.2','农林牧渔',298),(300,'1.1.2.2.5','生物技术',299),(301,'1.1.2.2.3','园艺',299),(302,'1.1.2.2.1','农林学',299),(303,'1.1.2.2.4','生物工程',299),(304,'1.1.2.2.2','茶草学',299),(305,'1.1.2.5','材料工程',298),(306,'1.1.2.5.4','电子信息材料',305),(307,'1.1.2.5.3','高分子材料',305),(308,'1.1.2.5.1','金属材料',305),(309,'1.1.2.5.2','无机非金属材料',305),(310,'1.1.2.6','动力能源',298),(311,'1.1.2.6.3','流体机械及工程',310),(312,'1.1.2.6.2','热力发动机',310),(313,'1.1.2.6.4','空调与制冷',310),(314,'1.1.2.6.1','热能工程',310),(315,'1.1.2.4','化学工业',298),(316,'1.1.2.4.3','加工化学',315),(317,'1.1.2.4.2','无机化学',315),(318,'1.1.2.4.1','有机化学',315),(319,'1.1.2.3','土木工程',298),(320,'1.1.2.3.1','土木',319),(321,'1.1.2.3.2','建筑',319),(322,'1.1.2.7','环境工程',298),(323,'1.1.2.7.2','环境技术',322),(324,'1.1.2.7.1','污染防治',322),(325,'1.1.2.1','电子通讯',298),(326,'1.1.2.1.1','电子科技与技术',325),(327,'1.1.2.1.2','电力工程',325),(328,'1.1.2.1.4','信息技术',325),(329,'1.1.2.1.3','电气工程 ',325);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_time`
--

DROP TABLE IF EXISTS `delivery_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_time`
--

LOCK TABLES `delivery_time` WRITE;
/*!40000 ALTER TABLE `delivery_time` DISABLE KEYS */;
INSERT INTO `delivery_time` VALUES (1,'周末'),(2,'工作日内');
/*!40000 ALTER TABLE `delivery_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display_name` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (1,'书本','book');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entity`
--

DROP TABLE IF EXISTS `entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `document_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB29DE3E3B844DC55` (`document_id`),
  KEY `FKB29DE3E3C5602D75` (`category_id`),
  CONSTRAINT `FKB29DE3E3B844DC55` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  CONSTRAINT `FKB29DE3E3C5602D75` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entity`
--

LOCK TABLES `entity` WRITE;
/*!40000 ALTER TABLE `entity` DISABLE KEYS */;
INSERT INTO `entity` VALUES (13,5.00,70,1),(14,6.00,70,1);
/*!40000 ALTER TABLE `entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entity_type`
--

DROP TABLE IF EXISTS `entity_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entity_type` (
  `storage_count` int(11) DEFAULT NULL,
  `goods_type_id` int(11) NOT NULL DEFAULT '0',
  `entity_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`entity_id`,`goods_type_id`),
  KEY `FK4C655A166FB6A15` (`entity_id`),
  KEY `FK4C655A162A768431` (`goods_type_id`),
  CONSTRAINT `FK4C655A162A768431` FOREIGN KEY (`goods_type_id`) REFERENCES `goods_type` (`id`),
  CONSTRAINT `FK4C655A166FB6A15` FOREIGN KEY (`entity_id`) REFERENCES `entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entity_type`
--

LOCK TABLES `entity_type` WRITE;
/*!40000 ALTER TABLE `entity_type` DISABLE KEYS */;
INSERT INTO `entity_type` VALUES (7,1,13),(10,2,13),(10,3,13),(6,1,14),(6,2,14),(6,3,14);
/*!40000 ALTER TABLE `entity_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entity_value`
--

DROP TABLE IF EXISTS `entity_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entity_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attribute_id` int(11) NOT NULL,
  `entity_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK405722156FB6A15` (`entity_id`),
  KEY `FK405722153413E71F` (`attribute_id`),
  CONSTRAINT `FK405722153413E71F` FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`),
  CONSTRAINT `FK405722156FB6A15` FOREIGN KEY (`entity_id`) REFERENCES `entity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entity_value`
--

LOCK TABLES `entity_value` WRITE;
/*!40000 ALTER TABLE `entity_value` DISABLE KEYS */;
INSERT INTO `entity_value` VALUES (133,11,13),(134,10,13),(135,1,13),(136,3,13),(137,5,13),(138,8,13),(139,6,13),(140,7,13),(141,2,13),(142,4,13),(143,9,13),(144,8,14),(145,7,14),(146,10,14),(147,11,14),(148,2,14),(149,9,14),(150,6,14),(151,3,14),(152,5,14),(153,1,14),(154,4,14);
/*!40000 ALTER TABLE `entity_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods_type`
--

DROP TABLE IF EXISTS `goods_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discount` decimal(19,2) DEFAULT NULL,
  `display_name` varchar(255) NOT NULL,
  `name` varchar(32) NOT NULL,
  `storage_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods_type`
--

LOCK TABLES `goods_type` WRITE;
/*!40000 ALTER TABLE `goods_type` DISABLE KEYS */;
INSERT INTO `goods_type` VALUES (1,0.80,'正版','authorised_edition','Z'),(2,0.50,'盗版','pirate_edition','D'),(3,0.30,'复印','copy_edition','F');
/*!40000 ALTER TABLE `goods_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_info`
--

DROP TABLE IF EXISTS `login_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(15) NOT NULL,
  `login_time` datetime DEFAULT NULL,
  `user_account` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK97D067C4701ED851` (`user_account`),
  CONSTRAINT `FK97D067C4701ED851` FOREIGN KEY (`user_account`) REFERENCES `user` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_info`
--

LOCK TABLES `login_info` WRITE;
/*!40000 ALTER TABLE `login_info` DISABLE KEYS */;
INSERT INTO `login_info` VALUES (1,'0:0:0:0:0:0:0:1','2013-05-23 22:11:28','fuck@qq.com'),(2,'0:0:0:0:0:0:0:1','2013-05-24 00:44:18','fuck@qq.com');
/*!40000 ALTER TABLE `login_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_`
--

DROP TABLE IF EXISTS `order_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `delivery_price` decimal(19,2) NOT NULL,
  `long_tel` varchar(32) DEFAULT NULL,
  `real_name` varchar(32) DEFAULT NULL,
  `short_tel` varchar(32) DEFAULT NULL,
  `delivery_time_id` int(11) NOT NULL,
  `order_state_id` int(11) NOT NULL,
  `user_account` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC3DF62D1122BE2F9` (`delivery_time_id`),
  KEY `FKC3DF62D1701ED851` (`user_account`),
  KEY `FKC3DF62D1DD343053` (`order_state_id`),
  CONSTRAINT `FKC3DF62D1122BE2F9` FOREIGN KEY (`delivery_time_id`) REFERENCES `delivery_time` (`id`),
  CONSTRAINT `FKC3DF62D1701ED851` FOREIGN KEY (`user_account`) REFERENCES `user` (`account`),
  CONSTRAINT `FKC3DF62D1DD343053` FOREIGN KEY (`order_state_id`) REFERENCES `order_state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_`
--

LOCK TABLES `order_` WRITE;
/*!40000 ALTER TABLE `order_` DISABLE KEYS */;
INSERT INTO `order_` VALUES (1,'华山区21栋206','2013-05-24 00:53:30',0.00,'15902078207','张宇','628207',1,1,'fuck@qq.com');
/*!40000 ALTER TABLE `order_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item` (
  `buy_num` int(11) NOT NULL,
  `sto_code_list` longtext,
  `order_id` int(11) NOT NULL DEFAULT '0',
  `entity_id` int(11) NOT NULL DEFAULT '0',
  `entity_id_2` int(11) NOT NULL DEFAULT '0',
  `goods_type_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`entity_id`,`entity_id_2`,`goods_type_id`,`order_id`),
  KEY `FK2D110D64E0011814` (`entity_id_2`,`goods_type_id`),
  KEY `FK2D110D646FB6A15` (`entity_id`),
  KEY `FK2D110D64B9264824` (`order_id`),
  CONSTRAINT `FK2D110D646FB6A15` FOREIGN KEY (`entity_id`) REFERENCES `entity` (`id`),
  CONSTRAINT `FK2D110D64B9264824` FOREIGN KEY (`order_id`) REFERENCES `order_` (`id`),
  CONSTRAINT `FK2D110D64E0011814` FOREIGN KEY (`entity_id_2`, `goods_type_id`) REFERENCES `entity_type` (`entity_id`, `goods_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (3,'1.2.3.3.1.13.Z[8~10]',1,13,13,1);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_state`
--

DROP TABLE IF EXISTS `order_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state` varchar(255) DEFAULT NULL,
  `next_state_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK759D7C60313B764E` (`next_state_id`),
  CONSTRAINT `FK759D7C60313B764E` FOREIGN KEY (`next_state_id`) REFERENCES `order_state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_state`
--

LOCK TABLES `order_state` WRITE;
/*!40000 ALTER TABLE `order_state` DISABLE KEYS */;
INSERT INTO `order_state` VALUES (1,'COMMITTED',2),(2,'DISPOSED',3),(3,'OUT_STORAGE',4),(4,'RECEIVED',NULL),(5,'CANCEL',NULL);
/*!40000 ALTER TABLE `order_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_state_admin`
--

DROP TABLE IF EXISTS `order_state_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_state_admin` (
  `time` datetime DEFAULT NULL,
  `order_id` int(11) NOT NULL,
  `order_state_id` int(11) NOT NULL,
  `account` varchar(32) NOT NULL,
  PRIMARY KEY (`order_id`,`order_state_id`),
  KEY `FKE891AB0DD343053` (`order_state_id`),
  KEY `FKE891AB0B9264824` (`order_id`),
  KEY `FKE891AB0152F40C5` (`account`),
  CONSTRAINT `FKE891AB0152F40C5` FOREIGN KEY (`account`) REFERENCES `user` (`account`),
  CONSTRAINT `FKE891AB0B9264824` FOREIGN KEY (`order_id`) REFERENCES `order_` (`id`),
  CONSTRAINT `FKE891AB0DD343053` FOREIGN KEY (`order_state_id`) REFERENCES `order_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_state_admin`
--

LOCK TABLES `order_state_admin` WRITE;
/*!40000 ALTER TABLE `order_state_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_state_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raw`
--

DROP TABLE IF EXISTS `raw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `raw` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display_name` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raw`
--

LOCK TABLES `raw` WRITE;
/*!40000 ALTER TABLE `raw` DISABLE KEYS */;
INSERT INTO `raw` VALUES (1,'文本','text'),(2,'富文本','richText');
/*!40000 ALTER TABLE `raw` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display_name` varchar(16) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'普通用户','ROLE_USER'),(2,'管理员','ROLE_ADMIN'),(3,'超级管理员','ROLE_SUPER_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `text_entity_value`
--

DROP TABLE IF EXISTS `text_entity_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `text_entity_value` (
  `content` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC5D4D7E751FA660E` (`id`),
  CONSTRAINT `FKC5D4D7E751FA660E` FOREIGN KEY (`id`) REFERENCES `entity_value` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `text_entity_value`
--

LOCK TABLES `text_entity_value` WRITE;
/*!40000 ALTER TABLE `text_entity_value` DISABLE KEYS */;
INSERT INTO `text_entity_value` VALUES ('2013-5',133),('http://img3.douban.com/spic/s26262170.jpg',134),('外文出版社',135),('http://img3.douban.com/lpic/s26262170.jpg',136),('296',137),('工厂女孩',138),('丁燕',139),('9787119081755',140),('http://img3.douban.com/mpic/s26262170.jpg',142),('没有人给他写信的上校',144),('9787544264013',145),('http://img3.douban.com/spic/s26377562.jpg',146),('2013-5',147),('[哥伦比亚] 加西亚·马尔克斯',150),('http://img3.douban.com/lpic/s26377562.jpg',151),('96',152),('南海出版公司',153),('http://img3.douban.com/mpic/s26377562.jpg',154);
/*!40000 ALTER TABLE `text_entity_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `account` varchar(32) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(16) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `user_info_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`account`),
  KEY `FK36EBCBAD6BE0C7` (`role_id`),
  KEY `FK36EBCB11F5C13E` (`user_info_id`),
  CONSTRAINT `FK36EBCB11F5C13E` FOREIGN KEY (`user_info_id`) REFERENCES `user_info` (`id`),
  CONSTRAINT `FK36EBCBAD6BE0C7` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('fuck@qq.com','','haha',1,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birthday` varchar(16) DEFAULT NULL,
  `gender` varchar(8) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  `qq` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-25 19:53:22
