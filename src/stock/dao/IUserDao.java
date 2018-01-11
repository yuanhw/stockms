package stock.dao;

import java.util.List;

import stock.vo.User;

public interface IUserDao {
	boolean doCreate(User user);
	boolean doUpdate(User user);
	boolean doDelete(String uid);
	boolean doCreate(List<User> list);
	boolean doUpdate(List<User> list);
	boolean doDelete(List<String> listId);
	List<User> findAll();
	User findById(String id);
}
