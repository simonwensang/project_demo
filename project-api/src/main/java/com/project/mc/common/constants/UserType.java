package com.project.mc.common.constants;

/**
 * Created by sang on 2018/6/27.
 */
public enum UserType {

    // 1管理员2加盟商家3厂家供应商
    ADMIN((byte)1,"管理员"),
    SELLER((byte)2,"加盟商家"),
    FACTORY((byte)3,"厂家供应商") ;

    private Byte key;
    private String value;

    private UserType(Byte key, String value) {
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
