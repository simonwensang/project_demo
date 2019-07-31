package com.project.mc.common.constants;

public enum PayType {
    TENPAY((byte)1,"微信支付"),
    ALIPAY((byte)2,"支付宝支付");

    public Byte key;
    public String value ;

     PayType(Byte key, String value){
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
