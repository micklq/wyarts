package com.arts.org.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.arts.org.service.PermissionService;
import com.arts.org.data.mapper.PermissionDAO;
import com.arts.org.data.repository.*;
import com.arts.org.model.entity.Permission;

@Service("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Long> implements PermissionService{

	@Resource( name = "permissionRepository")
	PermissionRepository permissionRepository;
		
	@Autowired
	PermissionDAO PermissionDAO;
	
	@Resource(name = "permissionRepository")
	@Override
	public void setBaseRepository(BaseRepository<Permission, Long> baseRepository) {
		super.setBaseRepository(baseRepository);
	}

	

}
