package stock.dao.proxy;

import java.util.List;

import stock.dao.IinventoryDao;
import stock.dao.impl.InventoryDaoImpl;
import stock.hibernate.StockSessionFactory;
import stock.vo.Inventory;

public class InventoryDaoProxy implements IinventoryDao {

	private InventoryDaoImpl invendao;
	public InventoryDaoProxy(){
		this.invendao=new InventoryDaoImpl(StockSessionFactory.getSession());
	}
	@Override
	public boolean doCreate(Inventory ven) {
		// TODO Auto-generated method stub
		boolean tag=false;
		try{
			tag=this.invendao.doCreate(ven);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doUpdate(Inventory ven) {
		// TODO Auto-generated method stub
		boolean tag=false;
		try{
			tag=this.invendao.doUpdate(ven);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doDelete(Inventory ven) {
		// TODO Auto-generated method stub
		boolean tag=false;
		try{
			tag=this.invendao.doDelete(ven);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public List<Inventory> findAll() {
		// TODO Auto-generated method stub
		List<Inventory> list=null;
		try{
			list=this.invendao.findAll();
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return list;
	}

	@Override
	public Inventory findById(Inventory ven) {
		// TODO Auto-generated method stub
		Inventory pro=null;
		try{
			pro=this.invendao.findById(ven);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return pro;
	}
	@Override
	public boolean doAdd(List<Inventory> list) {
		// TODO Auto-generated method stub
		boolean tag=false;
		try{
			tag=this.invendao.doAdd(list);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}
	@Override
	public boolean doSub(List<Inventory> list) {
		// TODO Auto-generated method stub
		boolean tag=false;
		try{
			tag=this.invendao.doSub(list);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}
	@Override
	public List<Inventory> findAllByPid(String pid) {
		// TODO Auto-generated method stub
		List<Inventory> list=null;
		try{
			list=this.invendao.findAllByPid(pid);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return list;
	}
	@Override
	public List<Inventory> findAllByWid(String wid) {
		// TODO Auto-generated method stub
		List<Inventory> list=null;
		try{
			list=this.invendao.findAllByWid(wid);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return list;
	}
	@Override
	public List<Inventory> findAllByKeyWord(String wid, String pid) {
		List<Inventory> list=null;
		try{
			list=this.invendao.findAllByKeyWord(wid, pid);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return list;
	}

}
