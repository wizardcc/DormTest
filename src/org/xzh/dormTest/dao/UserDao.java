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

	User findByStuCode(String stuCode);

	void saveStudent(User user);

	List<User> findStudent(String sql);

	Integer findTotalNum(String sql);

	void updateStudent(User studentUpdate);

	User findByUserIdAndManager(String sql);

	User findStuCodeAndManager(String sql);

	void updatePassWord(User userCur);

}
