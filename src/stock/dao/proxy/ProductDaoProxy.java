package stock.dao.proxy;

import java.util.List;

import stock.dao.IProductDao;
import stock.dao.impl.ProductDaoImpl;
import stock.hibernate.StockSessionFactory;
import stock.vo.Product;

public class ProductDaoProxy implements IProductDao{

	private ProductDaoImpl pdaoimpl;
	
	public ProductDaoProxy(){
		this.pdaoimpl=new ProductDaoImpl(StockSessionFactory.getSession());
	}
	@Override
	public boolean doCreate(Product pro) {
		boolean tag=false;
		try{
			tag=this.pdaoimpl.doCreate(pro);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doUpdate(Product pro) {
		boolean tag=false;
		try{
			tag=this.pdaoimpl.doUpdate(pro);
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
			tag=this.pdaoimpl.doDelete(pid);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doCreate(List<Product> list) {
		boolean tag=false;
		try{
			tag=this.pdaoimpl.doCreate(list);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public boolean doUpdate(List<Product> list) {
		boolean tag=false;
		try{
			tag=this.pdaoimpl.doUpdate(list);
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
			tag=this.pdaoimpl.doDelete(listId);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return tag;
	}

	@Override
	public List<Product> findAll() {
		List<Product> list=null;
		try{
			list=this.pdaoimpl.findAll();
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return list;
	}

	@Override
	public Product findById(String id) {
		Product pro=null;
		try{
			pro=this.pdaoimpl.findById(id);
		}catch(RuntimeException e){
			throw e;
		}finally{
			StockSessionFactory.closeSession();
		}
		return pro;
	}

}
