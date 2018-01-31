package com.arts.org.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import com.arts.org.service.TestsService;
import com.arts.org.data.mapper.TestsDAO;
import com.arts.org.data.repository.*;
import com.arts.org.model.entity.Tests;

@Service("testsService")
public class TestsServiceImpl extends BaseServiceImpl<Tests, Long> implements TestsService{

	@Resource( name = "testsRepository")
	TestsRepository testsRepository;
		
	@Autowired
	TestsDAO testsMapper;
	
	@Resource(name = "testsRepository")
	@Override
	public void setBaseRepository(BaseRepository<Tests, Long> baseRepository) {
		super.setBaseRepository(baseRepository);
	}

	@Override
	public List<Tests> findAllByMapper() {
		// TODO Auto-generated method stub
		return testsMapper.findAllByMapper();	
	}

	@Override
	public List<Tests> queryAll() {
		// TODO Auto-generated method stub
		return testsRepository.queryAll();
	}

	@Override
	public List<Tests> queryAllByMapper() {
		// TODO Auto-generated method stub
		return testsMapper.queryAllByMapper();	
	}	
	

}
