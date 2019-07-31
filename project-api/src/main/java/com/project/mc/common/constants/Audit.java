package com.project.mc.common.constants;

/**
 * Created by sang on 2018/8/28.
 */
public enum Audit {

    //0未审核1审核通过定2审核不通过
    UN_AUDIT((byte)0,"未审核"),
    AUDIT_PASS((byte)1,"审核通过") ,
    AUDIT_FAIL((byte)2,"审核不通过") ;

    public Byte key;
    public String value ;

    Audit(Byte key, String value){
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
        for (Audit element : Audit.values()) {
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
