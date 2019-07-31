package com.project.mc.common.constants;

public enum OrderStatus {
    INIT((byte)0,"初始"),
    ORDER((byte)1,"已下单待付款"),
    PAY((byte)2,"已支付"),
    PAY_ERROR((byte)3,"付款失败"),
    REFOUND((byte)4,"退款"),
    DROP_SUCCESS((byte)5,"出酒成功");

    public Byte key;
    public String value ;

    OrderStatus(Byte key, String value){
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
