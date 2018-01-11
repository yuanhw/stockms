package stock.dao.factory;

import stock.dao.proxy.WareHouseDaoProxy;

public class WareHousePFactory {
	public static WareHouseDaoProxy newInstence(){
		return new WareHouseDaoProxy();
	}
}
