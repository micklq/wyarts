package com.arts.org.model.entity;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.arts.org.model.enums.OrganizationStatus;


/**
 * Created by
 * User: djyin
 * Date: 12/16/13
 * Time: 7:37 PM
 * 代表系统中对象的访问许可
 */
@Entity
public class Organization extends BaseEntity {
		
	
	public long getOrgId() {
		return ((this.getId()==null)?0:this.getId());
	}

	public void setOrgId(long orgId) {
		this.setId(orgId);
	}
	private String code;//组织编号    
	 
    /**
     * The Organization Name.
     */
    @Column(length = 300, nullable = true)
    private String name;//名称    
    /**
     * The Organization Description.
     */
    @Column(length = 1024, nullable = true)
    private String description; 
    
    
    @Column(length = 300, nullable = true)
    private String address;//单位地址    

    private int status; //0有效  1无效    
    
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getStatusText() {
		return  OrganizationStatus.getByValue(this.getStatus()).getName();
	}
    
    
	
	
}
