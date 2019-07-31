package com.project.mc.common.constants;

public enum LockedSale {
    SELL((byte)0,"售卖"),
    STOP_SELL((byte)1,"停卖");

    public Byte key;
    public String value ;

    LockedSale(Byte key, String value){
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
        for (LockedSale element : LockedSale.values()) {
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
