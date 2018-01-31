package com.arts.org.data.repository; 

import javax.annotation.Resource;

import com.arts.org.model.entity.Organization;




@Resource(name = "organizationRepository")
public interface OrganizationRepository  extends BaseRepository<Organization, Long>{

}

