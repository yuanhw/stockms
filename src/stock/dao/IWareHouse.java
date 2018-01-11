package stock.dao;

import java.util.List;

import stock.vo.WareHouse;

public interface IWareHouse {
	boolean doCreate(WareHouse wh);
	boolean doUpdate(WareHouse wh);
	boolean doDelete(String wid);
	boolean doCreate(List<WareHouse> list);
	boolean doUpdate(List<WareHouse> list);
	boolean doDelete(List<String> listId);
	List<WareHouse> findAll();
	WareHouse findById(String id);
}
