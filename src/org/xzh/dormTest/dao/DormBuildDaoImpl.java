package org.xzh.dormTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.xzh.dormTest.bean.DormBuild;
import org.xzh.dormTest.bean.User;
import org.xzh.dormTest.util.ConnectionFactory;

public class DormBuildDaoImpl implements DormBuildDao {

	@Override
	public DormBuild findByName(String name) {
		//① 获取连接（数据库地址  用户名 密码）,用java.sql的包
		Connection connection = ConnectionFactory.getConnection();
		
		try {
			//② 准备SQL语句
			String sql = "select * from tb_dormbuild where name = ?";
			
			//③ 获取运输载体
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1, name);
			//④ 执行sql语句,⑤ 获取执行后的结果
			//查询的结果封装在ResultSet中，此时表头也查出来了，指针在表头位置，如果指针里面有数据才封装
			ResultSet rs = preparedStatement.executeQuery();
			
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
		}
		return null;
	}

	@Override
	public void save(DormBuild build) {
		//① 获取连接（数据库地址  用户名 密码）,用java.sql的包
		Connection connection = ConnectionFactory.getConnection();
		
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO tb_dormbuild(NAME,remark,disabled) VALUES(?,?,?) ";
			
			//③ 获取运输载体
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
		}
	}

}
