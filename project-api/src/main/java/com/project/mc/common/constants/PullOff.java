package com.project.mc.common.constants;

public enum PullOff {
    PUT_ON((byte)0,"上架"),
    PULL_OFF((byte)1,"下架");

    public Byte key;
    public String value ;

    PullOff(Byte key, String value){
        this.key=key;
        this.value=value;
    }
    public static boolean exists(Byte status) {
        if (status == null) {
            return false;
        }
        byte s = status.byteValue();
        return exists(s);
    }

    public static boolean exists(byte s) {
        for (PullOff element : PullOff.values()) {
            if (element.key == s) {
                return true;
            }
        }
        return false;
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
