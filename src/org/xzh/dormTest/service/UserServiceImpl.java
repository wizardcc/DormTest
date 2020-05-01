package org.xzh.dormTest.service;

import java.util.List;

import org.xzh.dormTest.bean.DormBuild;
import org.xzh.dormTest.bean.User;
import org.xzh.dormTest.dao.DormBuildDao;
import org.xzh.dormTest.dao.DormBuildDaoImpl;
import org.xzh.dormTest.dao.UserDao;
import org.xzh.dormTest.dao.UserDaoImpl;
import org.xzh.dormTest.util.ConnectionFactory;
import org.xzh.dormTest.util.PageModel;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();
	private DormBuildDao  dormBuildDao = new DormBuildDaoImpl();
	@Override
	public User findByStuCodeAndPass(String name, String password) {
		//没什么业务逻辑，直接将查询结果返回到上一级就可以
		return userDao.findByStuCodeAndPass(name,password);
	}
	@Override
	public void saveManager(User user,String[] dormBuildIds) {
		//找到数据库中宿舍管理员中最大的学号+1作为当前要保存的宿舍管理员的学号
		String managerStuCodeMax = userDao.findManagerStuCodeMax();
		System.out.println("managerStuCodeMax:"+managerStuCodeMax);
		
		user.setStuCode(managerStuCodeMax);
			
		//保存宿舍管理员，返回主键宿舍管理员ID
		Integer userId = userDao.saveManager(user);
		System.out.println("userId:"+userId);
		
		//保存宿舍管理员和宿舍楼的中间表
		dormBuildDao.saveManagerAndBuild(userId,dormBuildIds);
	}
	@Override
	public List<User> findManager(String searchType, String keyword) {
		StringBuffer  sql = new StringBuffer("SELECT * FROM tb_user WHERE role_id=1 ");
		
		if(keyword != null && !keyword.equals("")) {
			//说明用户是点击搜索按钮进行搜索
			if("name".equals(searchType)) {
				sql.append(" and name like '%"+keyword+"%'");
			}else if("sex".equals(searchType)) {
				sql.append(" and sex = '"+keyword.trim()+"'");
			}else if("tel".equals(searchType)) {
				sql.append(" and tel ="+keyword.trim());
			}
		}
		
		System.out.println("sql:"+sql.toString());
		
		//查询宿舍管理员
		List<User> users= userDao.findManager(sql.toString());
		for (User user : users) {
			List<DormBuild>  builds = dormBuildDao.findByUserId(user.getId());
			user.setDormBuilds(builds);
		}
		System.out.println("users:"+users);
		
		return users;
	}
	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}
	@Override
	public void updateManager(User user) {
		userDao.updateManager(user);
	}
	@Override
	public User findByStuCode(String stuCode) {
		// TODO Auto-generated method stub
		return userDao.findByStuCode(stuCode);
	}
	@Override
	public void saveStudent(User user) {
		// TODO Auto-generated method stub
		userDao.saveStudent(user);
	}
	@Override
	public List<User> findStudent(String dormBuildId, String searchType, String keyword,
			User user, PageModel pageModel) {
		
		StringBuffer  sql = new StringBuffer();
		//不管当前用户角色是怎么样，查询的都是学生，所有role_id=2
		sql.append("SELECT user.*,build.name buildName,build.*  FROM tb_user user " + 
				" LEFT JOIN tb_dormbuild  build ON build.`id` = user.dormBuildId "
				+ " where user.role_id = 2");
		
		if(keyword != null && !keyword.equals("") && "name".equals(searchType)) {
			//根据名字查询
			sql.append("  and  user.name like '%"+keyword.trim()+"%'");
		}else if(keyword != null && !keyword.equals("") && "stuCode".equals(searchType)) {
			//根据学号查询
			sql.append(" and user.stu_code = '"+keyword.trim()+"'");
			
		}else if(keyword != null && !keyword.equals("") && "dormCode".equals(searchType)) {
			//根据宿舍编号查询
			sql.append(" and user.dorm_code = '"+keyword.trim()+"'");
			
		}else if(keyword != null && !keyword.equals("") && "sex".equals(searchType)) {
			//根据性别查询
			sql.append(" and user.sex = '"+keyword.trim()+"'");
		}
		else if(keyword != null && !keyword.equals("") && "tel".equals(searchType)) {
			//根据电话号码查询
			sql.append(" and user.tel = '"+keyword.trim()+"'");
		}
		
		if(dormBuildId != null && !dormBuildId.equals("")) {
			//根据宿舍楼id查询
			sql.append(" and user.dormBuildId = '"+dormBuildId+"'");
		}
		
		//判断当前用户是否为宿舍管理员，如是则需查询其管理的所有宿舍楼id
		if(user.getRoleId().equals(1)) {
			//获取当前宿舍管理员管理的所有宿舍楼
			List<DormBuild>  builds  = dormBuildDao.findByUserId(user.getId());
			//限定查询的宿舍楼在用户管理的范围内
			sql.append(" and user.dormBuildId in (");
			for (int i = 0; i < builds.size(); i++) {
				sql.append(builds.get(i).getId()+",");
			}
			
			//删除最后一个,
			sql.deleteCharAt(sql.length()-1);
			sql.append(")");
		}
		
		sql.append(" limit "+pageModel.getStartNum()+" , "+pageModel.getPageSize());
		System.out.println("sql:"+sql);
		
		List<User>  students = userDao.findStudent(sql.toString());
		System.out.println("students:"+students);
		
		return students;
	}
	@Override
	public Integer findTotalNum(String dormBuildId, String searchType, String keyword, User user) {
		StringBuffer  sql = new StringBuffer();
		//不管当前用户角色是怎么样，查询的都是学生，所有role_id=2
		sql.append("SELECT count(*)  FROM tb_user user " + 
				" LEFT JOIN tb_dormbuild  build ON build.`id` = user.dormBuildId "
				+ " where user.role_id = 2");
		
		
		if(keyword != null && !keyword.equals("") && "name".equals(searchType)) {
			//根据名字查询
			sql.append("  and  user.name like '%"+keyword.trim()+"%'");
			
		}else if(keyword != null && !keyword.equals("") && "stuCode".equals(searchType)) {
			//根据学号查询
			sql.append(" and user.stu_code = '"+keyword.trim()+"'");
			
		}else if(keyword != null && !keyword.equals("") && "dormCode".equals(searchType)) {
			//根据宿舍编号查询
			sql.append(" and user.dorm_code = '"+keyword.trim()+"'");
			
		}else if(keyword != null && !keyword.equals("") && "sex".equals(searchType)) {
			//根据性别查询
			sql.append(" and user.sex = '"+keyword.trim()+"'");
		}
		else if(keyword != null && !keyword.equals("") && "tel".equals(searchType)) {
			//根据电话号码查询
			sql.append(" and user.tel = '"+keyword.trim()+"'");
		}
		
		if(dormBuildId != null && !dormBuildId.equals("")) {
			//根据宿舍楼id查询
			sql.append(" and user.dormBuildId = '"+dormBuildId+"'");
		}
		
		//判断当前用户是否为宿舍管理员，如是则需查询其管理的所有宿舍楼id
		if(user.getRoleId().equals(1)) {
			//获取当前宿舍管理员管理的所有宿舍楼
			List<DormBuild>  builds  = dormBuildDao.findByUserId(user.getId());
			
			sql.append(" and user.dormBuildId in (");
			for (int i = 0; i < builds.size(); i++) {
				sql.append(builds.get(i).getId()+",");
			}
			
			//删除最后一个,
			sql.deleteCharAt(sql.length()-1);
			sql.append(")");
		}
		
		System.out.println("sql:"+sql);
		
		return userDao.findTotalNum(sql.toString());
	}
	@Override
	public void updateStudent(User studentUpdate) {
		userDao.updateStudent(studentUpdate);
	}

}
