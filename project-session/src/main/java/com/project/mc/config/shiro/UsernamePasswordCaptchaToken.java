package com.project.mc.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {
	private static final long serialVersionUID = -3437451976192922631L;
	private String captcha;
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public UsernamePasswordCaptchaToken() {
		super();
	}
	
	public UsernamePasswordCaptchaToken(String username, String password,
                                        boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}
	
}
