package com.arts.org.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import com.arts.org.service.UserPassportService;
import com.arts.org.data.mapper.UserPassportDAO;
import com.arts.org.data.repository.*;
import com.arts.org.model.entity.UserPassport;

@Service("userPassportService")
public class UserPassportServiceImpl extends BaseServiceImpl<UserPassport, Long> implements UserPassportService{

	@Resource( name = "userPassportRepository")
	UserPassportRepository userPassportRepository;
		
	@Autowired
	UserPassportDAO UserPassportDAO;
	
	@Resource(name = "userPassportRepository")
	@Override
	public void setBaseRepository(BaseRepository<UserPassport, Long> baseRepository) {
		super.setBaseRepository(baseRepository);
	}

		
	

}
