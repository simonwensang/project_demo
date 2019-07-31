package com.project.mc.common.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by sang on 2018/10/23.
 */
public enum IotMessageType {

    // 1酒售 ，开锁，查看电量2在线状态3定位4基站定位5停止心跳6开始心跳
    PUSH((byte)1,"酒售 ，开锁，查看电量"),
    IS_OFF_LINE((byte)2,"在线状态") ,
    LOCATION((byte)3,"定位") ,
    LBS((byte)4,"基站定位")   ,
    STOP_HEART_BEAT((byte)5,"停止心跳") ,
    START_HEART_BEAT((byte)6,"开始心跳")  ;
    public Byte key;
    public String value ;

    IotMessageType(Byte key, String value){
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
        for (IotMessageType element : IotMessageType.values()) {
            if (element.key == s) {
                return true;
            }
        }
        return false;
    }
    public static IotMessageType getBykey(Byte key) {
        if (key == null) {
            return null;
        }
        byte s = key.byteValue();
        for (IotMessageType element : IotMessageType.values()) {
            if (element.key == s) {
                return element;
            }
        }
        return null;
    }
    @JsonValue
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
