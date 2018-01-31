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

import com.arts.org.service.OrganizationService;
import com.arts.org.data.repository.BaseRepository;
import com.arts.org.model.entity.Organization;
import com.arts.org.data.mapper.OrganizationDAO;
import com.arts.org.data.repository.OrganizationRepository;

  
@Service("organizationService")
public class OrganizationServiceImpl extends BaseServiceImpl<Organization, Long> implements OrganizationService {

	@Resource(name = "organizationRepository")
	private OrganizationRepository organizationRepository;

	@Autowired
	private OrganizationDAO organizationDAO;

	@Resource(name = "organizationRepository")
	@Override
	public void setBaseRepository(BaseRepository<Organization, Long> baseRepository){

		   super.setBaseRepository(baseRepository);
    }



}

