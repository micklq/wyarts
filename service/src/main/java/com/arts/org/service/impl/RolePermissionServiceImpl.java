package com.arts.org.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import com.arts.org.service.RolePermissionService;
import com.arts.org.data.mapper.RolePermissionDAO;
import com.arts.org.data.repository.*;
import com.arts.org.model.entity.RolePermission;

@Service("rolePermissionService")
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission, Long> implements RolePermissionService{

	@Resource( name = "rolePermissionRepository")
	RolePermissionRepository rolePermissionRepository;
		
	@Autowired
	RolePermissionDAO rolePermissionDAO;
	
	@Resource(name = "rolePermissionRepository")
	@Override
	public void setBaseRepository(BaseRepository<RolePermission, Long> baseRepository) {
		super.setBaseRepository(baseRepository);
	}

	@Override
	public void deleteByPermissionId(long permissionId) {
		
		rolePermissionDAO.deleteByPermissionId(permissionId);
		
	}
	
	@Override
	public void deleteByRoleId(long roleId) {
		
		rolePermissionDAO.deleteByRoleId(roleId);
		
	}

	

}
