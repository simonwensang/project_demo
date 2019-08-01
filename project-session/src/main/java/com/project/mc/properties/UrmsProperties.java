package com.project.mc.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * cas相关配置信息
 */
@Component
@ConfigurationProperties(prefix = "urms.config", locations = "classpath:urms-config.yml")
public class UrmsProperties {

    /**
     * 单点登录服务域名
     */
    private String loginServerUrlPrefix;

    /**
     * shiro服务域名
     */
    private String shiroServerUrlPrefix;

    /**
     * 邮件前缀
     */
    private String emailSuffix = "@divine.com";

    /**
     * 登录成功后跳转地址
     */
    private String successUrl = "html/";

    /**
     * 权限验证失败后,跳转的地址
     */
    private String unauthorizedUrl = "/html/pages/403.html";

    /**
     * shiro不拦截的请求
     */
    private Map<String, String> anons = new LinkedHashMap<>();


    // 登录地址
    public String getLoginUrl() {
        return this.getLoginServerUrlPrefix();
        //return this.getLoginServerUrlPrefix() + "/login?service=" + this.getShiroServerUrlPrefix();
    }

    // 登出地址
    public String getLogoutUrl(){
        //return this.getLoginServerUrlPrefix() + "/logout";
        return this.getShiroServerUrlPrefix() + "/logout";
    }


    public String getLoginServerUrlPrefix() {
        return loginServerUrlPrefix;
    }

    public void setLoginServerUrlPrefix(String loginServerUrlPrefix) {
        this.loginServerUrlPrefix = loginServerUrlPrefix;
    }

    public String getShiroServerUrlPrefix() {
        return shiroServerUrlPrefix;
    }

    public void setShiroServerUrlPrefix(String shiroServerUrlPrefix) {
        this.shiroServerUrlPrefix = shiroServerUrlPrefix;
    }

    public String getEmailSuffix() {
        return emailSuffix;
    }

    public void setEmailSuffix(String emailSuffix) {
        this.emailSuffix = emailSuffix;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    public Map<String, String> getAnons() {
        return anons;
    }

    public void setAnons(Map<String, String> anons) {
        this.anons = anons;
    }
}
