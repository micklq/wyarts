package com.arts.org.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;











import com.arts.org.service.UserProfileService;
import com.arts.org.data.mapper.UserProfileDAO;
import com.arts.org.data.repository.*;
import com.arts.org.model.entity.UserProfile;

@Service("userProfileService")
public class UserProfileServiceImpl extends BaseServiceImpl<UserProfile, Long> implements UserProfileService{

	@Resource( name = "userProfileRepository")
	UserProfileRepository userProfileRepository;
		
	@Autowired
	UserProfileDAO userProfileDAO;
	
	@Resource(name = "userProfileRepository")
	@Override
	public void setBaseRepository(BaseRepository<UserProfile, Long> baseRepository) {
		super.setBaseRepository(baseRepository);
	}

	@Override
	public void clearByDepartmentId(Long departId) {
		// TODO Auto-generated method stub
		userProfileDAO.clearByDepartmentId(departId);
	}

		
	

}
