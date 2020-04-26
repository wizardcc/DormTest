package org.xzh.dormTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xzh.dormTest.bean.DormBuild;
import org.xzh.dormTest.bean.User;
import org.xzh.dormTest.util.ConnectionFactory;

public class DormBuildDaoImpl implements DormBuildDao {

	@Override
	public DormBuild findByName(String name) {
		//① 获取连接（数据库地址  用户名 密码）,用java.sql的包
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			//② 准备SQL语句
			String sql = "select * from tb_dormbuild where name = ?";
			
			//③ 获取运输载体
			preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1, name);
			//④ 执行sql语句,⑤ 获取执行后的结果
			//查询的结果封装在ResultSet中，此时表头也查出来了，指针在表头位置，如果指针里面有数据才封装
			rs = preparedStatement.executeQuery();
			
			//查询结果中包含表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体封装该行数据
			while (rs.next()) {
				DormBuild  build = new DormBuild();
				build.setId(rs.getInt("id"));
				build.setName(rs.getString("name"));
				build.setDisabled(rs.getInt("disabled"));
				build.setRemark(rs.getString("remark"));
				
				return build;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		return null;
	}

	@Override
	public void save(DormBuild build) {
		//① 获取连接（数据库地址  用户名 密码）,用java.sql的包
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO tb_dormbuild(NAME,remark,disabled) VALUES(?,?,?) ";
			
			//③ 获取运输载体
			preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1, build.getName());
			preparedStatement.setString(2, build.getRemark());
			preparedStatement.setInt(3, build.getDisabled());
			
			//④ 执行sql语句,⑤ 获取执行后的结果
			//查询的结果封装在ResultSet中，此时表头也查出来了，指针在表头位置，如果指针里面有数据才封装
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	//查询全部的方法
	@Override
	public List<DormBuild> find() {
		//① 获取连接（数据库地址  用户名 密码）,用java.sql的包
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			//② 准备SQL语句
			String sql = "select * from tb_dormbuild ";
			
			//③ 获取运输载体
			preparedStatement = connection.prepareStatement(sql);
			
			//④ 执行sql语句,⑤ 获取执行后的结果
			//查询的结果封装在ResultSet中，此时表头也查出来了，指针在表头位置，如果指针里面有数据才封装
			rs = preparedStatement.executeQuery();
			
			//查询结果中包含表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体封装该行数据
			List<DormBuild> builds = new ArrayList<DormBuild>();
			while (rs.next()) {
				DormBuild  build = new DormBuild();
				build.setId(rs.getInt("id"));
				build.setName(rs.getString("name"));
				build.setDisabled(rs.getInt("disabled"));
				build.setRemark(rs.getString("remark"));
				
				builds.add(build);
			}
			return builds;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		return null;
	}

	@Override
	public DormBuild findById(Integer id) {
		//① 获取连接（数据库地址  用户名 密码）,用java.sql的包
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			//② 准备SQL语句
			String sql = "select * from tb_dormbuild where id = ?";
			
			//③ 获取运输载体
			preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setInt(1, id);
			//④ 执行sql语句,⑤ 获取执行后的结果
			//查询的结果封装在ResultSet中，此时表头也查出来了，指针在表头位置，如果指针里面有数据才封装
			rs = preparedStatement.executeQuery();
			
			//查询结果中包含表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体封装该行数据
			while (rs.next()) {
				DormBuild  build = new DormBuild();
				build.setId(rs.getInt("id"));
				build.setName(rs.getString("name"));
				build.setDisabled(rs.getInt("disabled"));
				build.setRemark(rs.getString("remark"));
				
				return build;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		return null;
	}

	@Override
	public void update(DormBuild build) {
		//① 获取连接（数据库地址  用户名 密码）,用java.sql的包
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			//② 准备SQL语句
			String sql = "UPDATE tb_dormbuild SET NAME = ?,remark =?,disabled = ? WHERE id = ?";
			
			//③ 获取运输载体
			preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1, build.getName());
			preparedStatement.setString(2, build.getRemark());
			preparedStatement.setInt(3, build.getDisabled());
			preparedStatement.setInt(4, build.getId());
			
			//④执行SQL,更新
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	@Override
	public void saveManagerAndBuild(Integer userId, String[] dormBuildIds) {
		//① 获取连接（数据库地址  用户名 密码）
		Connection  connection = 	ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO tb_manage_dormbuild(USER_ID,DormBuild_id) VALUE(?,?)";
			//③ 获取集装箱或者说是车
			preparedStatement = connection.prepareStatement(sql);
			//循环遍历
			for (String dormBuildId : dormBuildIds) {
				//索引从1开始
				preparedStatement.setInt(1, userId);
				preparedStatement.setInt(2, Integer.parseInt(dormBuildId));
				
				//将sql语句添加到批处理
				preparedStatement.addBatch();
			}
			//执行批处理
			preparedStatement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

}
