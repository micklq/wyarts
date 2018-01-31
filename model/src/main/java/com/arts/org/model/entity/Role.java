package com.arts.org.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role extends BaseEntity {
		
		
	private String name;

	private String description;
	

	public long getRoleId() {
		return ((this.getId()==null)?0:this.getId());
	}

	public void setRoleId(long roleId) {
		this.setId(roleId);
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

	

	
	

	
	
	

}
