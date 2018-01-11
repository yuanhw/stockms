package stock.dao;

import java.util.List;
import stock.vo.InStock;

public interface IInStock {
	boolean doCreate(InStock instock);
	boolean doDelete(String inid);
	boolean doUpdateState(InStock instock);
	List<InStock> findAll();
	List<InStock> findAll(String state);
	InStock findById(String inid);
}
