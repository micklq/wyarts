package com.arts.org.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum YesOrNO {
	No("否", 0),Yes("是", 1) ;

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

	private static Map<String, YesOrNO> nameMap;

	private static Map<Integer, YesOrNO> valueMap;

	static {
		nameMap = new HashMap<String, YesOrNO>();
		valueMap = new HashMap<Integer, YesOrNO>();

		for (YesOrNO vs : YesOrNO.values()) {
			nameMap.put(vs.name, vs);
			valueMap.put(vs.value, vs);
		}
	}

	private YesOrNO(String name, int value ) {
		this.name = name;
		this.value = value;
	}

	public static YesOrNO getByName(String name) {
		return nameMap.get(name);
	}

	public static YesOrNO getByValue(int value) {
		return valueMap.get(value);
	}
}
