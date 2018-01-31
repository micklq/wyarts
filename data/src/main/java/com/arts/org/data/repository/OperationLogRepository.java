package com.arts.org.data.repository;

import java.util.List;

import javax.annotation.Resource;

import com.arts.org.model.entity.OperationLog;

/**
 * Created by djyin on 7/19/2014.
 */
@Resource(name = "operationLogRepository")
public interface OperationLogRepository extends BaseRepository<OperationLog, Long> {
	
}
