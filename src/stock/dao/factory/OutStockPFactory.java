package stock.dao.factory;

import stock.dao.proxy.OutStockDaoProxy;

public class OutStockPFactory {
	public static OutStockDaoProxy newInstence(){
		return new OutStockDaoProxy();
	}
}
