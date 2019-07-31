package com.project.mc.common.util;

import java.util.UUID;

public class CodeFactory {

	public static final String GOV ="PACJ";

	public static final String MACHINE_PEX ="M";


	public static String getCode(){
		return CodeFactory.GOV+ DateUtils.getCurrentDay(DateUtils.dateTimeLongString)+(int)(1000+ Math.random()*900);
	}

	public static String getMachineCode(){
		return CodeFactory.MACHINE_PEX+ DateUtils.getCurrentDay(DateUtils.dateMillTimeLongString)+ org.apache.commons.lang3.StringUtils.substringBefore(UUID.randomUUID().toString().toUpperCase() , "-");
	}


	public static void main(String[] args) {

		System.out.println(CodeFactory.getMachineCode());
	}

}
