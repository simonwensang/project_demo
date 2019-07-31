package com.project.mc.common.constants;

public enum UserStatus {

	// 0待审核1启用2禁止
	AUDIT((byte)0,"待审核"),
	AVAILABLE((byte)1,"启用"),
	FORBIDDEN((byte)2,"禁止") ;

	private Byte key;
	private String value;
	
	private UserStatus(Byte key, String value) {
		this.key = key;
		this.value = value;
	}

	public Byte getKey() {
		return key;
	}

	public void setKey(Byte key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean equal(Byte val) {
		return val == null ? false : val.byteValue() == this.key;
	}
}
