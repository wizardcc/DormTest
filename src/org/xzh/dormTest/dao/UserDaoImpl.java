package org.xzh.dormTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.xzh.dormTest.bean.User;
import org.xzh.dormTest.util.ConnectionFactory;

public class UserDaoImpl implements UserDao {

	@Override
	public User findByStuCodeAndPass(String stuCode, String password) {
		//① 获取连接（数据库地址  用户名 密码）,用java.sql的包
		Connection connection = ConnectionFactory.getConnection();
		
		try {
			//② 准备SQL语句
			String sql = "select * from tb_user where stu_code = ? and password=?  and disabled = 0";
			
			//③ 获取运输载体
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1, stuCode);
			preparedStatement.setString(2, password);
			//④ 执行sql语句,⑤ 获取执行后的结果
			//查询的结果封装在ResultSet中，此时表头也查出来了，指针在表头位置，如果指针里面有数据才封装
			ResultSet rs = preparedStatement.executeQuery();
			
			//查询结果中包含表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体封装该行数据
			while (rs.next()) {
				User user = new User();
				//每一行的数据封装在一个实体bean中，根据字段名获取字段值，注意该字段是什么类型，就get什么类型
				user.setId(rs.getInt("id"));
				user.setCreateUserId(rs.getInt("create_user_id"));
				user.setDisabled(rs.getInt("disabled"));
				user.setDormBuildId(rs.getInt("dormBuildId"));
				user.setDormCode(rs.getString("dorm_Code"));
				user.setName(rs.getString("name"));
				user.setPassWord(rs.getString("passWord"));
				user.setRoleId(rs.getInt("role_id"));
				user.setSex(rs.getString("sex"));
				user.setStuCode(rs.getString("stu_code"));
				user.setTel(rs.getString("tel"));
				
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
