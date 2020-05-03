package org.xzh.dormTest.service;

import org.xzh.dormTest.bean.Record;
import org.xzh.dormTest.bean.User;

public interface RecordService {

	void save(Record record);

	Integer getToTalNum(String startDate, String endDate, String dormBuildId, String searchType, String keyword,
			User userCurr);

}
