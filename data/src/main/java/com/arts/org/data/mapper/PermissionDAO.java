package com.arts.org.data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface PermissionDAO { 
		
	public Integer findMaxSortByPid(@Param("pid")Long pid);
		
}
