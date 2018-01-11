package stock.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StockSessionFactory {
	private static SessionFactory sfactory;
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	static{
		Configuration conf=new Configuration();
		sfactory=conf.configure().buildSessionFactory();
	}
	private StockSessionFactory(){
	}
	public static void reBuildSessionFactory(){
		Configuration conf=new Configuration();
		sfactory=conf.configure().buildSessionFactory();
	}
	public static Session getSession(){
		Session session=threadLocal.get();
		if(session==null||!session.isOpen()){
			if(sfactory==null){
				reBuildSessionFactory();
			}
			session=(sfactory!=null)?(sfactory.openSession()):null;
			threadLocal.set(session);
		}
		return session;
	}
	public static void closeSession(){
		Session session=threadLocal.get();
		threadLocal.set(null);
		if(session!=null){
			session.close();
		}
	}
}
