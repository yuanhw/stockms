package stock.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import stock.dao.IOutStock;
import stock.vo.OutStock;

public class OutStockDaoImpl implements IOutStock {

	private Session session;
	
	public OutStockDaoImpl(Session session){
		this.session=session;
	}
	
	@Override
	public boolean doCreate(OutStock outstock) {
		// TODO Auto-generated method stub
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			session.save(outstock);
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
	public boolean doDelete(String outid) {
		// TODO Auto-generated method stub
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			OutStock outstock=(OutStock) session.get(OutStock.class, outid);
			final String toutid=outstock.getOutid();
			session.doWork(new Work(){

				@Override
				public void execute(Connection arg0) throws SQLException {
					try{
						String sql="delete from outstockitems where outid='"+toutid+"'";
						Statement sm=arg0.createStatement();
						sm.execute(sql);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			});
			Query query=session.createQuery("delete OutStock where outid = '"+toutid+"'");
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
	public List<OutStock> findAll() {
		// TODO Auto-generated method stub
		List<OutStock> list=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("from OutStock");
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
	public OutStock findById(String outid) {
		// TODO Auto-generated method stub
		OutStock outstock=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			outstock=(OutStock) session.get(OutStock.class, outid);
			ts.commit();
		}catch(RuntimeException e){
			if(ts!=null){
				ts.rollback();
			}
			e.printStackTrace();
		}
		return outstock;
	}

	@Override
	public boolean doUpdateState(OutStock outstock) {
		// TODO Auto-generated method stub
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			session.update(outstock);
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
	public List<OutStock> findAll(String state) {
		// TODO Auto-generated method stub
		List<OutStock> list=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("from OutStock where state='"+state+"'");
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
