package com.arts.org.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.arts.org.service.DepartmentService;
import com.arts.org.data.repository.BaseRepository;
import com.arts.org.model.entity.Department;
import com.arts.org.data.mapper.DepartmentDAO;
import com.arts.org.data.repository.DepartmentRepository;

  
@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department, Long> implements DepartmentService {

	@Resource(name = "departmentRepository")
	private DepartmentRepository departmentRepository;

	@Autowired
	private DepartmentDAO departmentDAO;

	@Resource(name = "departmentRepository")
	@Override
	public void setBaseRepository(BaseRepository<Department, Long> baseRepository){

		   super.setBaseRepository(baseRepository);
    }



}

