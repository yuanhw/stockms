package stock.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import stock.dao.IProductDao;
import stock.vo.Product;

public class ProductDaoImpl implements IProductDao {

	private Session session;
	
	public ProductDaoImpl(Session session){
		this.session=session;
	}
	
	@Override
	public boolean doCreate(Product pro) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			session.save(pro);
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
	public boolean doUpdate(Product pro) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			session.update(pro);
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
	public boolean doDelete(String pid) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("delete Product where pid = '"+pid+"'");
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
	public boolean doCreate(List<Product> list) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			for(Product pro : list){
				session.save(pro);
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
	public boolean doUpdate(List<Product> list) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			for(Product pro : list){
				session.update(pro);
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
				Query query=session.createQuery("delete Product where pid = '"+pid+"'");
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
	public List<Product> findAll() {
		List<Product> list=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("from Product");
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
	public Product findById(String id) {
		Product pro=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			pro=(Product)session.get(Product.class, id);
			ts.commit();
		}catch(Exception e){
			if(ts!=null){
				ts.rollback();
			}
			pro=null;
			e.printStackTrace();
		}
		return pro;
	}

}
