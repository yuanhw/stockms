package stock.dao.proxy;

import java.util.List;

import stock.dao.IWareHouse;
import stock.dao.impl.WareHouseDaoImpl;
import stock.hibernate.StockSessionFactory;
import stock.vo.WareHouse;

public class WareHouseDaoProxy implements IWareHouse {

private WareHouseDaoImpl wdaoimpl;
	
	public WareHouseDaoProxy(){
		this.wdaoimpl=new WareHouseDaoImpl(StockSessionFactory.getSession());
	}
	@Override
	public boolean doCreate(WareHouse wh) {
		boolean tag=false;
		try{
			tag=this.wdaoimpl.doCreate(wh);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doUpdate(WareHouse wh) {
		boolean tag=false;
		try{
			tag=this.wdaoimpl.doUpdate(wh);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doDelete(String wid) {
		boolean tag=false;
		try{
			tag=this.wdaoimpl.doDelete(wid);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doCreate(List<WareHouse> list) {
		boolean tag=false;
		try{
			tag=this.wdaoimpl.doCreate(list);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doUpdate(List<WareHouse> list) {
		boolean tag=false;
		try{
			tag=this.wdaoimpl.doUpdate(list);
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
			tag=this.wdaoimpl.doDelete(listId);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public List<WareHouse> findAll() {
		List<WareHouse> list=null;
		try{
			list=this.wdaoimpl.findAll();
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return list;
	}

	@Override
	public WareHouse findById(String id) {
		WareHouse pro=null;
		try{
			pro=this.wdaoimpl.findById(id);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return pro;
	}

}
