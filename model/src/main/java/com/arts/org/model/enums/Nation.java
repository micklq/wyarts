package com.arts.org.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Nation {
	Han("汉族", 0),Hui("回族", 1),Man("满族", 2),MengGu("蒙古族", 3),Zang("藏族", 4),WeiWuEr("维吾尔族", 5),Yi("彝族", 6);

	private String name;

	private int value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private static Map<String, Nation> nameMap;

	private static Map<Integer, Nation> valueMap;

	static {
		nameMap = new HashMap<String, Nation>();
		valueMap = new HashMap<Integer, Nation>();

		for (Nation vs : Nation.values()) {
			nameMap.put(vs.name, vs);
			valueMap.put(vs.value, vs);
		}
	}

	private Nation(String name, int value ) {
		this.name = name;
		this.value = value;
	}

	public static Nation getByName(String name) {
		return nameMap.get(name);
	}

	public static Nation getByValue(int value) {
		return valueMap.get(value);
	}
}
