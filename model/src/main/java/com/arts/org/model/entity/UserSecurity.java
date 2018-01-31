package com.arts.org.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.arts.org.model.entity.BaseEntity.Save;
import com.arts.org.model.entity.BaseEntity.Update;

@Entity
public class UserSecurity  extends BaseEntity {


	@NotEmpty(groups = {Save.class,Update.class})
    @Pattern(regexp = "^[^\\s&\"<>]+$")
    @Length(min = 4, max = 300)
    @Column(nullable = false)
	private String password;

	private String hashAlgorithm;

	private String passwordSalt;

	private boolean isLocked;
	
	private Date lastLockedTime;

	private int failedPasswordAttemptCount;

	private Date lastPasswordChangedTime;

	public long getPassportId() {
		return ((this.getId()==null)?0:this.getId());
	}

	public void setPassportId(long passportId) {
		this.setId(passportId);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHashAlgorithm() {
		return hashAlgorithm;
	}

	public void setHashAlgorithm(String hashAlgorithm) {
		this.hashAlgorithm = hashAlgorithm;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public int getFailedPasswordAttemptCount() {
		return failedPasswordAttemptCount;
	}

	public void setFailedPasswordAttemptCount(int failedPasswordAttemptCount) {
		this.failedPasswordAttemptCount = failedPasswordAttemptCount;
	}

	public Date getLastLockedTime() {
		return lastLockedTime;
	}

	public void setLastLockedTime(Date lastLockedTime) {
		this.lastLockedTime = lastLockedTime;
	}

	public Date getLastPasswordChangedTime() {
		return lastPasswordChangedTime;
	}

	public void setLastPasswordChangedTime(Date lastPasswordChangedTime) {
		this.lastPasswordChangedTime = lastPasswordChangedTime;
	}

	


}
