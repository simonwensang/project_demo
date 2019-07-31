package com.project.mc.req.base;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by sang on 2018/6/27.
 */
@ApiModel("用户信息基类")
public class BaseDTO  implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
    * @Fields baseUserId 用户id
	 */
    @ApiModelProperty("用户id")
    private Long baseUserId  ;

    /*
    * @Fields baseUserName用户名
   */
    @ApiModelProperty("用户名")
    private String baseUserName  ;
    /*
      * @Fields baseUserType用户类型1管理员2加盟商家3供应商
     */
    @ApiModelProperty("用户类型1管理员2加盟商家3供应商")
    private Byte baseUserType  ;

    public Long getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(Long baseUserId) {
        this.baseUserId = baseUserId;
    }

    public String getBaseUserName() {
        return baseUserName;
    }

    public void setBaseUserName(String baseUserName) {
        this.baseUserName = baseUserName;
    }

    public Byte getBaseUserType() {
        return baseUserType;
    }

    public void setBaseUserType(Byte baseUserType) {
        this.baseUserType = baseUserType;
    }

    /*
     * @Fields wxOpenId 用户wxOpenId
     */
    @ApiModelProperty("用户wxOpenId")
    private String wxOpenId  ;

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }
}
