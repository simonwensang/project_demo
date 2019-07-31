package com.project.mc.model;

import java.util.Date;

public class User {

    public static final String ID = "Id";
    public static final String ACCOUNT = "账户";
    public static final String OPEN_ID = "微信openid";
    public static final String SESSION_KEY = "微信sessionkey";
    public static final String NAME = "姓名";
    public static final String CODE = "卡号";
    public static final String PASSWORD = "password";
    public static final String MOBILE = "mobile";
    public static final String CREATE_TIME = "createTime";
    public static final String UPDATE_TIME = "updateTime";
    public static final String U_STATUS = "0,待审核1 启用2,禁止";


    /**
     * Id
     */
    private Long id;

    /**
     * 账户
     */
    private String account;

    /**
     * 微信openid
     */
    private String openId;

    /**
     * 微信sessionkey
     */
    private String sessionKey;

    /**
     * 姓名
     */
    private String name;

    /**
     * 卡号
     */
    private String code;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String mobile;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 0,"待审核”1 "启用"2,"禁止"
     */
    private Byte uStatus;
    

    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public String getAccount(){
        return account;
    }
    
    public void setAccount(String account){
        this.account = account;
    }

    public String getOpenId(){
        return openId;
    }
    
    public void setOpenId(String openId){
        this.openId = openId;
    }

    public String getSessionKey(){
        return sessionKey;
    }
    
    public void setSessionKey(String sessionKey){
        this.sessionKey = sessionKey;
    }

    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getCode(){
        return code;
    }
    
    public void setCode(String code){
        this.code = code;
    }

    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }

    public String getMobile(){
        return mobile;
    }
    
    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public Date getCreateTime(){
        return createTime;
    }
    
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getUpdateTime(){
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public Byte getUStatus(){
        return uStatus;
    }
    
    public void setUStatus(Byte uStatus){
        this.uStatus = uStatus;
    }

    /**
    * The following custom property definition
    */

}