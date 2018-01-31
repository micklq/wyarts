package com.arts.org.model.enums;

import java.util.HashMap;
import java.util.Map;


/**
 * 用户状态
 * @author dmsong
 *
 */
public enum ServerType {

	sourceServer("0","源服务器"),
	pushServer("1","push服务器");
	
	private String type;
	private String name;
	
	private static Map<String,ServerType> typeMap;
	private static Map<String,ServerType> nameMap;
	static{
		nameMap=new HashMap<String,ServerType>();
		typeMap=new HashMap<String,ServerType>();
		for (ServerType vs : ServerType.values()) {
			nameMap.put(vs.name, vs);
			typeMap.put(vs.type, vs);
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private ServerType(String type,String name){
		this.name=name;
		this.setType(type);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
