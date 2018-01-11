package stock.dao;

import java.util.List;

import stock.vo.OutStock;

public interface IOutStock {
	boolean doCreate(OutStock outstock);
	boolean doDelete(String inid);
	boolean doUpdateState(OutStock outstock);
	List<OutStock> findAll();
	List<OutStock> findAll(String state);
	OutStock findById(String outid);
}
