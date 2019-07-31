package com.project.mc.config.shiro;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by sang on 2018/4/18.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class WxLoginAuthenticationToken extends UsernamePasswordToken {
    private static final long serialVersionUID = -1L;
    private String sessionKey ;

    public WxLoginAuthenticationToken(String username, String password, boolean rememberMe, String sessionKey) {
        super(username, password, rememberMe);
        this.sessionKey = sessionKey;
    }
}
