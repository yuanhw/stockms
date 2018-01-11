package stock.dao.factory;

import stock.dao.proxy.ProductDaoProxy;

public class ProductPFactory {
	public static ProductDaoProxy newInstence(){
		return new ProductDaoProxy();
	}
}
