package com.arts.org.data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface RolePermissionDAO {

	void deleteByPermissionId(@Param("permissionId")long permissionId); 	
	void deleteByRoleId(@Param("roleId")long roleId); 	
	
}
