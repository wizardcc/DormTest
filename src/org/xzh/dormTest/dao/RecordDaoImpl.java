package org.xzh.dormTest.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xzh.dormTest.bean.DormBuild;
import org.xzh.dormTest.bean.Record;
import org.xzh.dormTest.bean.User;
import org.xzh.dormTest.util.ConnectionFactory;

public class RecordDaoImpl implements RecordDao {

	@Override
	public void save(Record record) {
		//① 获取连接（数据库地址  用户名 密码）
		Connection  connection = 	ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO tb_record(student_id,DATE,remark,disabled) VALUES(?,?,?,?)";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setInt(1,record.getStudentId());
			preparedStatement.setDate(2, new Date(record.getDate().getTime()));
			preparedStatement.setString(3, record.getRemark());
			preparedStatement.setInt(4, record.getDisabled());
			
			//④执行SQL,更新
			 preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

}
