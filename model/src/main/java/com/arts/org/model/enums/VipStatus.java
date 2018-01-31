package com.arts.org.model.enums;


/**
 * 用户状态
 * @author dmsong
 *
 */
public enum VipStatus {

	normal(0,"普通"),
	blueV(1,"蓝V"),
	yellowV(2,"黄V");
	
	private Integer stat;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private VipStatus(Integer stat,String name){
		this.name=name;
		this.setStat(stat);
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}
	public static void main(String args[]){
	}
}
