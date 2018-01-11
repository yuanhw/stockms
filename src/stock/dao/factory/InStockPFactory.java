package stock.dao.factory;

import stock.dao.proxy.InStockDaoProxy;

public class InStockPFactory {
	public static InStockDaoProxy newInstence(){
		return new InStockDaoProxy();
	}
}
