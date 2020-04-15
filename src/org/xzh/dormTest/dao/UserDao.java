package org.xzh.dormTest.dao;

import org.xzh.dormTest.bean.User;

public interface UserDao {

	User findByNamAndPass(String name, String password);

}
