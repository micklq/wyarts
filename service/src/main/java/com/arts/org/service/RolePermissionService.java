package com.arts.org.service;

import java.util.List;

import com.arts.org.model.entity.Permission;
import com.arts.org.model.entity.RolePermission;


public interface RolePermissionService extends BaseService<RolePermission, Long> 
{
	public void deleteByPermissionId(long permissionId);
	
	public void deleteByRoleId(long roleId);
}
