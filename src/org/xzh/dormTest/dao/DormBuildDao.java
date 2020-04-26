package org.xzh.dormTest.dao;

import java.util.List;

import org.xzh.dormTest.bean.DormBuild;

public interface DormBuildDao {

	DormBuild findByName(String name);

	void save(DormBuild build);

	List<DormBuild> find();

	DormBuild findById(Integer id);

	void update(DormBuild dormBuild);

	void saveManagerAndBuild(Integer userId, String[] dormBuildIds);

}
