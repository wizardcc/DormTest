package org.xzh.dormTest.service;

import java.util.List;

import org.xzh.dormTest.bean.User;

public interface UserService {

	User findByStuCodeAndPass(String name, String password);

	void saveManager(User user, String[] dormBuildIds);

	List<User> findManager(String searchType, String keyword);

}
