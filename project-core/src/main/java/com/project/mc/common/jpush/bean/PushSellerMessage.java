package com.project.mc.common.jpush.bean;

import lombok.Data;

/**
 * Created by sang on 2018/4/28.
 */
@Data
public class PushSellerMessage<T> {

    private Byte type; //1订单推送

    private T playload; //消息体

}
