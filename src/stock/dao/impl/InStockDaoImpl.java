package stock.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import stock.dao.IInStock;
import stock.vo.InStock;

public class InStockDaoImpl implements IInStock {

	private Session session;
	
	public InStockDaoImpl(Session session){
		this.session=session;
	}
	
	@Override
	public boolean doCreate(InStock instock) {
		// TODO Auto-generated method stub
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			session.save(instock);
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
	public boolean doDelete(String inid) {
		// TODO Auto-generated method stub
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			InStock instock=(InStock) session.get(InStock.class, inid);
			final String tinid=instock.getInid();
			session.doWork(new Work(){

				@Override
				public void execute(Connection arg0) throws SQLException {
					try{
						String sql="delete from instockitems where inid='"+tinid+"'";
						Statement sm=arg0.createStatement();
						sm.execute(sql);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			});
			Query query=session.createQuery("delete InStock where inid = '"+tinid+"'");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<InStock> findAll() {
		// TODO Auto-generated method stub
		List<InStock> list=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("from InStock");
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
	public InStock findById(String inid) {
		// TODO Auto-generated method stub
		InStock instock=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			instock=(InStock) session.get(InStock.class, inid);
			ts.commit();
		}catch(RuntimeException e){
			if(ts!=null){
				ts.rollback();
			}
			e.printStackTrace();
		}
		return instock;
	}

	@Override
	public boolean doUpdateState(InStock instock) {
		// TODO Auto-generated method stub
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			session.update(instock);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<InStock> findAll(String state) {
		// TODO Auto-generated method stub
		List<InStock> list=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("from InStock where state='"+state+"'");
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

}
