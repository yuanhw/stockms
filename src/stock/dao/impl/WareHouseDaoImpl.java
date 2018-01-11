package stock.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import stock.dao.IWareHouse;
import stock.vo.WareHouse;

public class WareHouseDaoImpl implements IWareHouse {

	private Session session;
	
	public WareHouseDaoImpl(Session session){
		this.session=session;
	}
	
	@Override
	public boolean doCreate(WareHouse wh) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			session.save(wh);
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
	public boolean doUpdate(WareHouse wh) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			session.update(wh);
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
	public boolean doDelete(String wid) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("delete WareHouse where wid = '"+wid+"'");
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
	public boolean doCreate(List<WareHouse> list) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			for(WareHouse wh : list){
				session.save(wh);
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
	public boolean doUpdate(List<WareHouse> list) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			for(WareHouse wh : list){
				session.update(wh);
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
			for(String wid : listId){
				Query query=session.createQuery("delete WareHouse where wid = '"+wid+"'");
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
	public List<WareHouse> findAll() {
		List<WareHouse> list=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("from WareHouse");
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
	public WareHouse findById(String id) {
		WareHouse wh=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			wh=(WareHouse)session.get(WareHouse.class, id);
			ts.commit();
		}catch(Exception e){
			if(ts!=null){
				ts.rollback();
			}
			wh=null;
			e.printStackTrace();
		}
		return wh;
	}

}
