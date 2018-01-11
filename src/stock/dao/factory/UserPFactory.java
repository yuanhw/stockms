package stock.dao.factory;

import stock.dao.proxy.UserDaoProxy;

public class UserPFactory {
	public static UserDaoProxy newInstence(){
		return new UserDaoProxy();
	}
}
