package org.xzh.dormTest.service;

import org.xzh.dormTest.bean.Record;
import org.xzh.dormTest.dao.RecordDao;
import org.xzh.dormTest.dao.RecordDaoImpl;

public class RecordServiceImpl implements RecordService {

	//服务层调Dao层
	private RecordDao recordDao = new RecordDaoImpl();
	@Override
	public void save(Record record) {
		recordDao.save(record);
	}

}
