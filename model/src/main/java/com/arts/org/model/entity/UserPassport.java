package com.arts.org.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.arts.org.model.enums.PassportStatus;

@Entity
public class UserPassport extends BaseEntity {

	/**
	 * 用户名
	 */
	@NotEmpty(groups = {BaseEntity.Save.class,BaseEntity.Update.class})	
	@Length(min = 2, max = 30)
	@Column(nullable = false, updatable = false, unique = true)
	private String userName;
	
	/**
	 * 邮箱
	 */
	@Email
    @Length(max = 200)
    @Column(length = 200)
	private String email;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 角色Id
	 */
	private long roleId;
	
	@Transient
	private String roleName;	
	
	
	/**
	 * 状态
	 */
	private int passportStatus;
	
	
	
	public long getPassportId() {
		return ((this.getId()==null)?0:this.getId());
	}

	public void setPassportId(long passportId) {
		this.setId(passportId);
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int getPassportStatus() {
		return passportStatus;
	}

	public void setPassportStatus(int passportStatus) {
		this.passportStatus = passportStatus;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPassportStatusText() {
		return  PassportStatus.getByValue(this.getPassportStatus()).getName();
	}

		
	

}
