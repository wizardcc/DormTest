package org.xzh.dormTest.dao;

import org.xzh.dormTest.bean.DormBuild;

public interface DormBuildDao {

	DormBuild findByName(String name);

	void save(DormBuild build);

}
