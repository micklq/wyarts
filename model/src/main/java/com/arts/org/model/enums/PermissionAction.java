package com.arts.org.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum PermissionAction {
	All("全部", 0),Add("添加", 1),Update("修改", 2),Delete("删除", 3),Read("查看", 4),Audit("审批", 5);

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

	private static Map<String, PermissionAction> nameMap;

	private static Map<Integer, PermissionAction> valueMap;

	static {
		nameMap = new HashMap<String, PermissionAction>();
		valueMap = new HashMap<Integer, PermissionAction>();

		for (PermissionAction vs : PermissionAction.values()) {
			nameMap.put(vs.name, vs);
			valueMap.put(vs.value, vs);
		}
	}

	private PermissionAction(String name, int value ) {
		this.name = name;
		this.value = value;
	}

	public static PermissionAction getByName(String name) {
		return nameMap.get(name);
	}

	public static PermissionAction getByValue(int value) {
		return valueMap.get(value);
	}
}
