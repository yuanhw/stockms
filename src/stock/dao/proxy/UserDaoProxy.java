package stock.dao.proxy;

import java.util.List;

import stock.dao.IUserDao;
import stock.dao.impl.UserDaoImpl;
import stock.hibernate.StockSessionFactory;
import stock.vo.User;

public class UserDaoProxy implements IUserDao{

	private UserDaoImpl udaoimpl;
	
	public UserDaoProxy(){
		this.udaoimpl=new UserDaoImpl(StockSessionFactory.getSession());
	}
	@Override
	public boolean doCreate(User user) {
		boolean tag=false;
		try{
			tag=this.udaoimpl.doCreate(user);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doUpdate(User user) {
		boolean tag=false;
		try{
			tag=this.udaoimpl.doUpdate(user);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doDelete(String pid) {
		boolean tag=false;
		try{
			tag=this.udaoimpl.doDelete(pid);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doCreate(List<User> list) {
		boolean tag=false;
		try{
			tag=this.udaoimpl.doCreate(list);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doUpdate(List<User> list) {
		boolean tag=false;
		try{
			tag=this.udaoimpl.doUpdate(list);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doDelete(List<String> listId) {
		boolean tag=false;
		try{
			tag=this.udaoimpl.doDelete(listId);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public List<User> findAll() {
		List<User> list=null;
		try{
			list=this.udaoimpl.findAll();
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return list;
	}

	@Override
	public User findById(String id) {
		User pro=null;
		try{
			pro=this.udaoimpl.findById(id);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return pro;
	}

}
