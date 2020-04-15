package org.xzh.dormTest.service;

import org.xzh.dormTest.bean.User;

public interface UserService {

	User findByStuCodeAndPass(String name, String password);

}
