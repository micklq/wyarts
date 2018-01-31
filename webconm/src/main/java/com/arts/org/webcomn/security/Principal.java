package com.arts.org.webcomn.security;

import java.io.Serializable;

public class Principal implements Serializable {

    private static final long serialVersionUID = 5798882004228239559L;
    private Long passportid;
    private Long roleid;
    private String username;  

    public Principal(Long passportid, String username) {
        this.passportid = passportid;
        this.username = username;
    }
    public Principal(Long passportid, Long roleid,String username) {
        this.passportid = passportid;
        this.roleid = roleid;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return username;
    }

	public Long getPassportid() {
		return passportid;
	}

	public void setPassportid(Long passportid) {
		this.passportid = passportid;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	
}
