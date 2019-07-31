package com.project.mc.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class MachineToken extends UsernamePasswordToken {
	private static final long serialVersionUID = -3437451976192922631L;
	private String androidId ;
	private String machineIp ;

	public MachineToken() {
		super();
	}

	public MachineToken(String username, String password,
                        boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
		this.androidId = androidId;
		this.machineIp = machineIp;
	}

	public String getAndroidId() {
		return androidId;
	}

	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}

	public String getMachineIp() {
		return machineIp;
	}

	public void setMachineIp(String machineIp) {
		this.machineIp = machineIp;
	}
}
