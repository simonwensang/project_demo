package com.project.mc.common.constants;

public enum OnlineStatus {
    ON_LINE((byte)0,"online"),
    OFF_LINE((byte)1,"offline");

    public Byte key;
    public String value ;

    OnlineStatus(Byte key, String value){
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
        for (OnlineStatus element : OnlineStatus.values()) {
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
