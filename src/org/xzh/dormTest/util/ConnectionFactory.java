package org.xzh.dormTest.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	//获取数据源、自动读取c3p0-config.xml文件
	private static DataSource dataSource = new ComboPooledDataSource();
	
	//获取连接,工具里面都是用静态方法来调用，直接类名.方法名，实体类型的还需要New来创建对象才能使用
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//资源关闭工具方法
	public static void close(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet) {
		try {
			//释放资源，根据先开后放
			if(resultSet != null)  resultSet.close();
			if(preparedStatement != null)  preparedStatement.close();
			if(connection != null)  connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
