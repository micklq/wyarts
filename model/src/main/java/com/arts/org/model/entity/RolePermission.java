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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Created by
 * User: djyin
 * Date: 12/16/13
 * Time: 7:37 PM
 * 代表系统中对象的访问许可
 */
@Entity
@JsonIgnoreProperties({  "createDate", "modifyDate","creatorId", "dataFrom", "createDateStr", "modifyDateStr" })
public class RolePermission extends BaseEntity {
	

	@Column(name = "roleid")
	private long roleId;//角色Id	
	
	@Column(name = "permissionid")
	private long permissionId;//权限Id	
		
	private long parentPermissionId;//权限Id	
	
	@Column(name = "actionvalue")
	private int actionValue;//事件
	
	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	public int getActionValue() {
		return actionValue;
	}

	public void setActionValue(int actionValue) {
		this.actionValue = actionValue;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getParentPermissionId() {
		return parentPermissionId;
	}

	public void setParentPermissionId(long parentPermissionId) {
		this.parentPermissionId = parentPermissionId;
	}

	
	
	
}
