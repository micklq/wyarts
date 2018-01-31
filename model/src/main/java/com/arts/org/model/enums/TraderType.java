package com.arts.org.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 流类型
 * @author Jones
 *
 */
public enum TraderType {
	财达("0"),
	渤海("1"),
	保益托管("2"),
	国金("3"),
	银河证券("4"),
	方正证券("5"),
	中投证券("6");
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private static Map<String,TraderType> nameMap;
	static{
		nameMap=new HashMap<String,TraderType>();
		
		for (TraderType vs : TraderType.values()) {
			nameMap.put(vs.name, vs);
		}
	}
	private TraderType(String name){
		this.name=name;
	}
	public static TraderType getTraderTypeByName(String name){
		return nameMap.get(name);
	}
	
	public static void main(String args[]){
		System.out.println(TraderType.getTraderTypeByName("0").toString());
	}
}
