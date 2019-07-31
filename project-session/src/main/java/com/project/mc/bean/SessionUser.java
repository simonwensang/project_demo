package com.project.mc.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SessionUser implements Serializable{
    private static final long serialVersionUID = 8378009535002650887L;
    /**
     * 用户openid
     */
    private String openId;
    /**
     * 用户名称
     */
    private String name;
    /**
     *
     */
    private Byte type;
    /**
     *
     */
    private Long id;

    private String account;

    private String sessionKey;

    private String code;

    private String password;

    private Byte uStatus;
    /**
     *
     */
    private String captcha;
    /**
     *
     */
    private String mobile;

    /**
     * 推送别名
     */
    private String pushAlias;
}
