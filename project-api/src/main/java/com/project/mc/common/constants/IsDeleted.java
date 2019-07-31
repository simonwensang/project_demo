package com.project.mc.common.constants;

/**
 * Created by sang on 2018/9/4.
 */
public enum IsDeleted {

    NO((byte)0,"可用"),
    YES((byte)1,"删除");

    public Byte key;
    public String value ;

    IsDeleted(Byte key, String value){
        this.key=key;
        this.value=value;
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
