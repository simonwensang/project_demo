package com.project.mc.common.jpush.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sang on 2018/11/28.
 */
@Data
public class PushOrder {

    private String orderId;//  订单号

    /**
     * 订单金额
     */
    private BigDecimal payment;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 酒机名称
     */
    private String  machineName;

    /**
     * 利润金额
     */
    private BigDecimal sellerProfit;
}
