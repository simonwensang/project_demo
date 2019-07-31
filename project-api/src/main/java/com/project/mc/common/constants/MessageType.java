package com.project.mc.common.constants;

/**
 * Created by sang on 2018/8/28.
 */
public enum MessageType {

    // 1大型机2小型机
    JPUSH((byte)1,"极光推送"),
    IOT((byte)2,"阿里云消息") ,
    SELLER((byte)3,"商家消息")   ;

    public Byte key;
    public String value ;

    MessageType(Byte key, String value){
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
        for (MessageType element : MessageType.values()) {
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
