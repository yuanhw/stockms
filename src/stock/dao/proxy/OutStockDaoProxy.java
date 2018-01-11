package stock.dao.proxy;

import java.util.List;

import stock.dao.IOutStock;
import stock.dao.impl.OutStockDaoImpl;
import stock.hibernate.StockSessionFactory;
import stock.vo.OutStock;

public class OutStockDaoProxy implements IOutStock {

	private OutStockDaoImpl pdaoimpl;
	
	public OutStockDaoProxy(){
		this.pdaoimpl=new OutStockDaoImpl(StockSessionFactory.getSession());
	}
	@Override
	public boolean doCreate(OutStock outstock) {
		// TODO Auto-generated method stub
		boolean tag=false;
		try{
			tag=this.pdaoimpl.doCreate(outstock);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doDelete(String outid) {
		// TODO Auto-generated method stub
		boolean tag=false;
		try{
			tag=this.pdaoimpl.doDelete(outid);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public List<OutStock> findAll() {
		// TODO Auto-generated method stub
		List<OutStock> list=null;
		try{
			list=this.pdaoimpl.findAll();
		}catch(RuntimeException e){
			throw e;
		}
		return list;
	}

	@Override
	public OutStock findById(String outid) {
		// TODO Auto-generated method stub
		OutStock outstock=null;
		try{
			outstock=this.pdaoimpl.findById(outid);
		}catch(RuntimeException e){
			throw e;
		}
		return outstock;
	}
	@Override
	public boolean doUpdateState(OutStock outstock) {
		// TODO Auto-generated method stub
		boolean tag=false;
		try{
			tag=this.pdaoimpl.doUpdateState(outstock);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}
	@Override
	public List<OutStock> findAll(String state) {
		// TODO Auto-generated method stub
		List<OutStock> list=null;
		try{
			list=this.pdaoimpl.findAll(state);
		}catch(RuntimeException e){
			throw e;
		}
		return list;
	}

}
