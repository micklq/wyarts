package com.arts.org.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;











import com.arts.org.service.UserSecurityService;
import com.arts.org.data.mapper.UserSecurityDAO;
import com.arts.org.data.repository.*;
import com.arts.org.model.entity.UserSecurity;

@Service("userSecurityService")
public class UserSecurityServiceImpl extends BaseServiceImpl<UserSecurity, Long> implements UserSecurityService{

	@Resource( name = "userSecurityRepository")
	UserSecurityRepository userSecurityRepository;
		
	@Autowired
	UserSecurityDAO userSecurityDAO;
	
	@Resource(name = "userSecurityRepository")
	@Override
	public void setBaseRepository(BaseRepository<UserSecurity, Long> baseRepository) {
		super.setBaseRepository(baseRepository);
	}

	
	

}
