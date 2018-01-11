package stock.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import stock.dao.IUserDao;
import stock.vo.User;

public class UserDaoImpl implements IUserDao {

	private Session session;
	
	public UserDaoImpl(Session session){
		this.session=session;
	}
	
	@Override
	public boolean doCreate(User user) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			session.save(user);
			ts.commit();
		}catch(RuntimeException e){
			if(ts!=null){
				ts.rollback();
			}
			tag=false;
			e.printStackTrace();
		}
		return tag;
	}

	@Override
	public boolean doUpdate(User user) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			session.update(user);
			ts.commit();
		}catch(RuntimeException e){
			if(ts!=null){
				ts.rollback();
			}
			tag=false;
			e.printStackTrace();
		}
		return tag;
	}

	@Override
	public boolean doDelete(String uid) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("delete User where uid = '"+uid+"'");
			query.executeUpdate();
			ts.commit();
		}catch(RuntimeException e){
			if(ts!=null){
				ts.rollback();
			}
			tag=false;
			e.printStackTrace();
		}
		return tag;
	}

	@Override
	public boolean doCreate(List<User> list) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			for(User uid : list){
				session.save(uid);
			}
			ts.commit();
		}catch(RuntimeException e){
			if(ts!=null){
				ts.rollback();
			}
			tag=false;
			e.printStackTrace();
		}
		return tag;
	}

	@Override
	public boolean doUpdate(List<User> list) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			for(User uid : list){
				session.update(uid);
			}
			ts.commit();
		}catch(RuntimeException e){
			if(ts!=null){
				ts.rollback();
			}
			tag=false;
			e.printStackTrace();
		}
		return tag;
	}

	@Override
	public boolean doDelete(List<String> listId) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			for(String pid : listId){
				Query query=session.createQuery("delete User where uid = '"+pid+"'");
				query.executeUpdate();
			}
			ts.commit();
		}catch(RuntimeException e){
			if(ts!=null){
				ts.rollback();
			}
			tag=false;
			e.printStackTrace();;
		}
		return tag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		List<User> list=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("from User");
			list=query.list();
			ts.commit();
		}catch(Exception e){
			if(ts!=null){
				ts.rollback();
			}
			list=null;
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public User findById(String id) {
		User uid=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			uid=(User)session.get(User.class, id);
			ts.commit();
		}catch(Exception e){
			if(ts!=null){
				ts.rollback();
			}
			uid=null;
			e.printStackTrace();
		}
		return uid;
	}

}
