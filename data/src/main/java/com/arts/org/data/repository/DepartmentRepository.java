package com.arts.org.data.repository; 

import javax.annotation.Resource;

import com.arts.org.model.entity.Department;



@Resource(name = "departmentRepository")
public interface DepartmentRepository  extends BaseRepository<Department, Long>{

}

