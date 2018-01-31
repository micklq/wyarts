package com.arts.org.service;

import java.util.List;

import com.arts.org.model.entity.Tests;


public interface TestsService extends BaseService<Tests, Long> 
{
	
	public List<Tests> findAllByMapper();
	
	public List<Tests> queryAll();
	
	public List<Tests> queryAllByMapper();
}
