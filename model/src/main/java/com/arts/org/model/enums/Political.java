package com.arts.org.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Political {
	QunZhong("群众", 0),TuanYuan("团员", 1),DangYuan("党员", 1),QiTa("其他", 1);

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

	private static Map<String, Political> nameMap;

	private static Map<Integer, Political> valueMap;

	static {
		nameMap = new HashMap<String, Political>();
		valueMap = new HashMap<Integer, Political>();

		for (Political vs : Political.values()) {
			nameMap.put(vs.name, vs);
			valueMap.put(vs.value, vs);
		}
	}

	private Political(String name, int value ) {
		this.name = name;
		this.value = value;
	}

	public static Political getByName(String name) {
		return nameMap.get(name);
	}

	public static Political getByValue(int value) {
		return valueMap.get(value);
	}
}
