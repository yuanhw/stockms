package stock.dao;

import java.util.List;

import stock.vo.Product;

public interface IProductDao {
	boolean doCreate(Product pro);
	boolean doUpdate(Product pro);
	boolean doDelete(String pid);
	boolean doCreate(List<Product> list);
	boolean doUpdate(List<Product> list);
	boolean doDelete(List<String> listId);
	List<Product> findAll();
	Product findById(String id);
}
