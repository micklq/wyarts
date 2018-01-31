package com.arts.org.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 流类型
 * @author Jones
 *
 */
public enum StreamType {
	rtmp("0"),
	hls("1"),
	video_files("2");
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private static Map<String,StreamType> nameMap;
	static{
		nameMap=new HashMap<String,StreamType>();
		
		for (StreamType vs : StreamType.values()) {
			nameMap.put(vs.name, vs);
		}
	}
	private StreamType(String name){
		this.name=name;
	}
	public static StreamType getStreamTypeByName(String name){
		return nameMap.get(name);
	}
}
