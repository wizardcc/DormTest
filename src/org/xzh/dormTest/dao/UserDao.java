package org.xzh.dormTest.dao;

import java.util.List;

import org.xzh.dormTest.bean.User;

public interface UserDao {

	User findByStuCodeAndPass(String name, String password);

	String findManagerStuCodeMax();

	Integer saveManager(User user);

	List<User> findManager(String sql);

	User findById(int id);

	void updateManager(User user);

}
