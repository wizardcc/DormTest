package org.xzh.dormTest.service;

import org.xzh.dormTest.bean.User;
import org.xzh.dormTest.dao.UserDao;
import org.xzh.dormTest.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();
	@Override
	public User findByNamAndPass(String name, String password) {
		//没什么业务逻辑，直接将查询结果返回到上一级就可以
		return userDao.findByNamAndPass(name,password);
	}

}
