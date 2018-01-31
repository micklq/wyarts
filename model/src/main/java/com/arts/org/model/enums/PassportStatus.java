package com.arts.org.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum PassportStatus {
	Standard("正常", 0),Locked("锁定", 500),Hibernation("休眠", 900),Cancellation("作废",999);

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

	private static Map<String, PassportStatus> nameMap;

	private static Map<Integer, PassportStatus> valueMap;

	static {
		nameMap = new HashMap<String, PassportStatus>();
		valueMap = new HashMap<Integer, PassportStatus>();

		for (PassportStatus vs : PassportStatus.values()) {
			nameMap.put(vs.name, vs);
			valueMap.put(vs.value, vs);
		}
	}

	private PassportStatus(String name, int value ) {
		this.name = name;
		this.value = value;
	}

	public static PassportStatus getByName(String name) {
		return nameMap.get(name);
	}

	public static PassportStatus getByValue(int value) {
		return valueMap.get(value);
	}
}
