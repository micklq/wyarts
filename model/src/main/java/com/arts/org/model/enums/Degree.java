package com.arts.org.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Degree {
	XiaoXue("小学", 0),ChuZhong("初中", 1),GaoZhong("高中", 2),DaZhuan("大专", 3),BenKe("本科", 4),YaJiuSheng("研究生", 5),BoShi("博士", 6);

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

	private static Map<String, Degree> nameMap;

	private static Map<Integer, Degree> valueMap;

	static {
		nameMap = new HashMap<String, Degree>();
		valueMap = new HashMap<Integer, Degree>();

		for (Degree vs : Degree.values()) {
			nameMap.put(vs.name, vs);
			valueMap.put(vs.value, vs);
		}
	}

	private Degree(String name, int value ) {
		this.name = name;
		this.value = value;
	}

	public static Degree getByName(String name) {
		return nameMap.get(name);
	}

	public static Degree getByValue(int value) {
		return valueMap.get(value);
	}
}
