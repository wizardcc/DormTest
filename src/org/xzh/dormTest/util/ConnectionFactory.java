package org.xzh.dormTest.util;

import java.sql.Connection;
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
}
