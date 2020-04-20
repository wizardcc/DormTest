package org.xzh.dormTest.service;

import org.xzh.dormTest.bean.DormBuild;

public interface DormBuildService {

	DormBuild findByName(String name);

	void save(DormBuild build);

}
