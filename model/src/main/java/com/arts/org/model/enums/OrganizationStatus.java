package com.arts.org.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum OrganizationStatus {
	Standard("有效", 0),Locked("锁定", 1),Disable("禁用", 2),Cancellation("作废",3);

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

	private static Map<String, OrganizationStatus> nameMap;

	private static Map<Integer, OrganizationStatus> valueMap;

	static {
		nameMap = new HashMap<String, OrganizationStatus>();
		valueMap = new HashMap<Integer, OrganizationStatus>();

		for (OrganizationStatus vs : OrganizationStatus.values()) {
			nameMap.put(vs.name, vs);
			valueMap.put(vs.value, vs);
		}
	}

	private OrganizationStatus(String name, int value ) {
		this.name = name;
		this.value = value;
	}

	public static OrganizationStatus getByName(String name) {
		return nameMap.get(name);
	}

	public static OrganizationStatus getByValue(int value) {
		return valueMap.get(value);
	}
}
