package org.xzh.dormTest.dao;

import java.util.List;

import org.xzh.dormTest.bean.Record;

public interface RecordDao {

	void save(Record record);

	Integer getTotalNum(String sql);

	List<Record> find(String sql);

	Record findById(int id);

}
