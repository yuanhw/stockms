package stock.dao.factory;

import stock.dao.proxy.InventoryDaoProxy;

public class InventoryFactory {
	public static InventoryDaoProxy newInstance(){
		return new InventoryDaoProxy();
	}
}
