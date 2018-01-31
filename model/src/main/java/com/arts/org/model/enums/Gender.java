package com.arts.org.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
	Female("女", 0),Male("男", 1);

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

	private static Map<String, Gender> nameMap;

	private static Map<Integer, Gender> valueMap;

	static {
		nameMap = new HashMap<String, Gender>();
		valueMap = new HashMap<Integer, Gender>();

		for (Gender vs : Gender.values()) {
			nameMap.put(vs.name, vs);
			valueMap.put(vs.value, vs);
		}
	}

	private Gender(String name, int value ) {
		this.name = name;
		this.value = value;
	}

	public static Gender getByName(String name) {
		return nameMap.get(name);
	}

	public static Gender getByValue(int value) {
		return valueMap.get(value);
	}
}
