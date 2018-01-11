package stock.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import stock.dao.IinventoryDao;
import stock.vo.Inventory;

public class InventoryDaoImpl implements IinventoryDao {

	private Session session;
	
	public InventoryDaoImpl(Session session){
		this.session=session;
	}
	
	@Override
	public boolean doCreate(Inventory ven) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			session.save(ven);
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
	public boolean doUpdate(Inventory ven) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			session.update(ven);
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
	public boolean doDelete(Inventory ven) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("delete Inventory where wid = '"+ven.getWid()+"' and pid='"
					+ven.getPid()+"' and unitprice="+ven.getUnitprice());
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
	public List<Inventory> findAll() {
		List<Inventory> list=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("from Inventory");
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
	public Inventory findById(Inventory ven) {
		Inventory inven=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			inven=(Inventory)session.get(Inventory.class, ven);
			ts.commit();
		}catch(Exception e){
			if(ts!=null){
				ts.rollback();
			}
			inven=null;
			e.printStackTrace();
		}
		return inven;
	}

	@Override
	public boolean doAdd(List<Inventory> list) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			for(Inventory ven : list){
				int tnum=ven.getNumbers();
				Inventory temp=(Inventory)session.get(Inventory.class, ven);
				if(temp==null){
					session.save(ven);
				}else{
					ven.setNumbers(ven.getNumbers()+tnum);
					session.update(ven);
				}
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
	public boolean doSub(List<Inventory> list) {
		boolean tag=true;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			for(Inventory ven : list){
				int tnum=ven.getNumbers();
				Inventory temp=(Inventory)session.get(Inventory.class, ven);
				if(temp==null){
					ts.rollback();
					return false;
				}else{
					int stocknum=temp.getNumbers();
					ven.setNumbers(stocknum-tnum);
					if(tnum<=stocknum){
						if(ven.getNumbers()==0){
							session.delete(ven);
						}else{
							session.update(ven);
						}
					}else{
						ts.rollback();
						return false;
					}
				}
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> findAllByPid(String pid) {
		List<Inventory> list=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("from Inventory where pid='"+pid+"'");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> findAllByWid(String wid) {
		List<Inventory> list=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("from Inventory where wid='"+wid+"'");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> findAllByKeyWord(String wid, String pid) {
		List<Inventory> list=null;
		Transaction ts=null;
		try{
			ts=session.beginTransaction();
			Query query=session.createQuery("from Inventory where wid='"+wid+"' and pid='"+pid+"'");
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
