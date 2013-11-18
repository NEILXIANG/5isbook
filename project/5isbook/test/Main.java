import java.sql.SQLException; 
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.wisbook.model.user.User;
import com.wisbook.util.HibernateTemplate;


public class Main {
	
	ClassPathXmlApplicationContext ap;

	public Main(){
		ap = new ClassPathXmlApplicationContext("beans.xml");
	}
	
	@Test
	public void test1() {
		SessionFactory sf = (SessionFactory) ap.getBean("sessionFactory");
		Session session1 = sf.openSession();
		session1.beginTransaction();
		User user1 = (User) session1.load(User.class,"ffdsa");
		session1.getTransaction().commit();
		session1.close();
		sf.close();
		System.out.println(user1.getClass().getName());
		System.out.println(user1.getAccount());
		/*
		Session session2 = sf.openSession();
		session2.beginTransaction();
		User user2 = (User) session2.get(User.class,"so.volador@gmail.com");
		System.out.println(user2.getClass().getName());
		System.out.println(user2.getAccount());
		session2.getTransaction().commit();
		session2.close();
		*/
	}
	
	@Test
	public void test2(){
		
		//该方法演示如何在hibernateTemplate中使用load方法。
		HibernateTemplate hibernateTemplate = (HibernateTemplate) ap.getBean("hibernateTemplate");
		User user1 = (User) hibernateTemplate.execute(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				User user = (User) session.load(User.class,"so.volador@gmail.com");
				Hibernate.initialize(user);//使对象立马加载
				return user;
			}
			
		});
		System.out.println(user1.getPassword());
		
		User user2 = (User) hibernateTemplate.execute(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				User user = (User) session.load(User.class,"so.volador@gmail.com");
				Hibernate.initialize(user);//使对象立马加载
				return user;
			}
			
		});
		System.out.println(user2.getPassword());
	}
	
	 
	public void test3(){
		
	}
	@Test
	public void testCteateTable(){
		new SchemaExport(new AnnotationConfiguration().configure()).create(true, true);
	}

}
