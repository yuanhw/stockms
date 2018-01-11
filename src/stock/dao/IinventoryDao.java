package stock.dao;

import java.util.List;

import stock.vo.Inventory;

public interface IinventoryDao {
	boolean doCreate(Inventory ven);
	boolean doAdd(List<Inventory> list);
	boolean doSub(List<Inventory> list);
	boolean doUpdate(Inventory ven);
	boolean doDelete(Inventory ven);
	List<Inventory> findAll();
	Inventory findById(Inventory ven);
	List<Inventory> findAllByPid(String pid);
	List<Inventory> findAllByWid(String wid);
	List<Inventory> findAllByKeyWord(String wid,String pid);
}
