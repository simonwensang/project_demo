package com.project.mc.common.jpush.bean;

import lombok.Data;

/**
 * Created by sang on 2018/4/28.
 */
@Data
public class PushMessagePojo {

    private String orderId;//  订单号

    private Byte dropCode; //

    private String command; //字符串命令

}
