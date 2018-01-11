package stock.dao.proxy;

import java.util.List;

import stock.dao.IInStock;
import stock.dao.impl.InStockDaoImpl;
import stock.hibernate.StockSessionFactory;
import stock.vo.InStock;

public class InStockDaoProxy implements IInStock {

	private InStockDaoImpl pdaoimpl;
	
	public InStockDaoProxy(){
		this.pdaoimpl=new InStockDaoImpl(StockSessionFactory.getSession());
	}
	@Override
	public boolean doCreate(InStock instock) {
		// TODO Auto-generated method stub
		boolean tag=false;
		try{
			tag=this.pdaoimpl.doCreate(instock);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doDelete(String inid) {
		// TODO Auto-generated method stub
		boolean tag=false;
		try{
			tag=this.pdaoimpl.doDelete(inid);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public List<InStock> findAll() {
		// TODO Auto-generated method stub
		List<InStock> list=null;
		try{
			list=this.pdaoimpl.findAll();
		}catch(RuntimeException e){
			throw e;
		}
		return list;
	}

	@Override
	public InStock findById(String inid) {
		// TODO Auto-generated method stub
		InStock instock=null;
		try{
			instock=this.pdaoimpl.findById(inid);
		}catch(RuntimeException e){
			throw e;
		}
		return instock;
	}
	@Override
	public boolean doUpdateState(InStock instock) {
		// TODO Auto-generated method stub
		boolean tag=false;
		try{
			tag=this.pdaoimpl.doUpdateState(instock);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}
	@Override
	public List<InStock> findAll(String state) {
		// TODO Auto-generated method stub
		List<InStock> list=null;
		try{
			list=this.pdaoimpl.findAll(state);
		}catch(RuntimeException e){
			throw e;
		}
		return list;
	}

}
