package com.arts.org.service;

import java.util.List;

import com.arts.org.model.entity.Tests;
import com.arts.org.model.entity.UserProfile;


public interface UserProfileService extends BaseService<UserProfile, Long> 
{

	public void clearByDepartmentId(Long departId);
		
}
