package org.xzh.dormTest.service;

import java.util.List;

import org.xzh.dormTest.bean.DormBuild;
import org.xzh.dormTest.dao.DormBuildDao;
import org.xzh.dormTest.dao.DormBuildDaoImpl;

public class DormBuildServiceImpl implements DormBuildService {
	private DormBuildDao dormBuildDao = new DormBuildDaoImpl();
	@Override
	public DormBuild findByName(String name) {
		// TODO Auto-generated method stub
		return dormBuildDao.findByName(name);
	}
	@Override
	public void save(DormBuild build) {
		dormBuildDao.save(build);
		
	}
	@Override
	public List<DormBuild> find() {
		return dormBuildDao.find();
	}
	@Override
	public DormBuild findById(Integer id) {
		return dormBuildDao.findById(id);
	}
	@Override
	public void update(DormBuild dormBuild) {
		dormBuildDao.update(dormBuild);
	}
	@Override
	public List<DormBuild> findByUserId(Integer id) {
		return dormBuildDao.findByUserId(id);
	}
	@Override
	public void deleteByUserId(Integer id) {
		dormBuildDao.deleteByUserId(id);
	}

}
