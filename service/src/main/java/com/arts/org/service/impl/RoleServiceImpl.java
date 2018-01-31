package com.arts.org.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.arts.org.service.RoleService;
import com.arts.org.data.mapper.RoleDAO;
import com.arts.org.data.repository.*;
import com.arts.org.model.entity.Role;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService{

	@Resource( name = "roleRepository")
	RoleRepository roleRepository;
		
	@Autowired
	RoleDAO RoleDAO;
	
	@Resource(name = "roleRepository")
	@Override
	public void setBaseRepository(BaseRepository<Role, Long> baseRepository) {
		super.setBaseRepository(baseRepository);
	}

	
	

}
