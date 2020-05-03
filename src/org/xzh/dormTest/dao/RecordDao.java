package org.xzh.dormTest.dao;

import org.xzh.dormTest.bean.Record;

public interface RecordDao {

	void save(Record record);

	Integer getTotalNum(String sql);

}
