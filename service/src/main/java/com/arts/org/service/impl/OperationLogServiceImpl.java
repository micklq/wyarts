package com.arts.org.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arts.org.service.OperationLogService;
import com.arts.org.data.repository.BaseRepository;
import com.arts.org.data.repository.OperationLogRepository;
import com.arts.org.model.entity.OperationLog;

@Service("operationLogService")
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLog, Long> implements OperationLogService{

	@Resource( name = "operationLogRepository")
	OperationLogRepository operationLogRepository;
	
		
	@Resource( name = "operationLogRepository")
	@Override
	public void setBaseRepository(BaseRepository<OperationLog, Long> baseRepository) {
		super.setBaseRepository(baseRepository);
	}	

}
