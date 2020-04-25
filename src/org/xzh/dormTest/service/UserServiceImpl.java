package org.xzh.dormTest.service;

import org.xzh.dormTest.bean.User;
import org.xzh.dormTest.dao.UserDao;
import org.xzh.dormTest.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();
	@Override
	public User findByStuCodeAndPass(String name, String password) {
		//没什么业务逻辑，直接将查询结果返回到上一级就可以
		return userDao.findByStuCodeAndPass(name,password);
	}
	@Override
	public void saveManager(User user) {
		//找到数据库中宿舍管理员中最大的学号+1作为当前要保存的宿舍管理员的学号
		String managerStuCodeMax = userDao.findManagerStuCodeMax();
		System.out.println("managerStuCodeMax:"+managerStuCodeMax);
		
		user.setStuCode(managerStuCodeMax);
			
		//保存宿舍管理员，返回主键宿舍管理员ID
		Integer userId = userDao.saveManager(user);
		System.out.println("userId:"+userId);
		
		//保存宿舍管理员和宿舍楼的中间表
	}

}
