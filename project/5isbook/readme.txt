关于ssh版本：struts2.3.1.2+spring2.5.6+hibernate3.3.2

关于后台开发规范：
1.svn上只提交代码跟有必要的jar包，不用提交那些eclipse的配置文件,svn提交时写上必要的注释。
2.同一编码为utf8
3.程序中所定义的各种类名以及变量名需带上语义，方法上加上必要的注释
4.数据库表名用小写字母表示，若有连词用下划线链接。
	注：由于Role是数据库关键字，所以其表明后面增加了下划线：role_
5.java里面的代码编写方式采用陀峰式:MainTest

在系统缓存信息上对一下信息做了缓存：具体看ehcache.xml
1.acegi,权限管理中用户验证信息
2.cms商品文档的定义
3.商品的分类树

注意：
1.classpath上的hibernate.cfg.xml只是测试建表用的，hibernate上的配置都集成到spring配置文件里面了(beans.xml)
	其用法：利用SchemaExport进行建表：运行junit测试用例中Main类的testCteateTable方法即可。
	
关于权限以及管理员的约定：
	系统中共存在4中用户状态:权限也逐级递增
		ROLE_ANONYMOUS(匿名状态,不用数据库定义，acegi中已经定义好了)
		ROLE_USER(普通用户状态)
		ROLE_ADMIN(普通管理员状态)
		ROLE_SUPER_ADMIN（超级管理员状态）
