package com.arts.org.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态
 * @author Jones
 *
 */
public enum Status {
	直播中("0"),
	无输入流("1"),
	直播未开始("2"),
	直播已结束("3");
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private static Map<String,Status> nameMap;
	static{
		nameMap=new HashMap<String,Status>();
		
		for (Status vs : Status.values()) {
			nameMap.put(vs.name, vs);
		}
	}
	private Status(String name){
		this.name=name;
	}
	public static Status getStreamStatusByName(String name){
		return nameMap.get(name);
	}
}
