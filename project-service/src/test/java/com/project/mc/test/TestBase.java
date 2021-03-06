package com.project.mc.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.mc.common.util.FileUtil;
import com.project.mc.result.McResult;
import com.project.mc.result.McResultCode;


import static org.junit.Assert.assertEquals;
import java.io.IOException;

public class TestBase {
	private static final String SUCCESS_FLAG = "200";

	public void print(String name, Object msg) {
		System.out.println(name + "=======" + msg);
	}

	public void printAndAssertEquals(McResult<?> result) {
		System.out.println("--------------------打印开始--------------------");
		System.out.println(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
		assertEquals(SUCCESS_FLAG, result.getCode());
		System.out.println("--------------------打印结束--------------------");
	}


	public void printAndAssertEquals(McResult<?> result, McResultCode errorCode) {
		System.out.println("--------------------打印开始--------------------");
		System.out.println(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
		assertEquals(errorCode.getCode(), result.getCode());
		System.out.println("--------------------打印结束--------------------");
	}

	public <T> T readJsonFile(String path, Class<?> clazz) {
		String jsonContext = "";
		try {
			jsonContext = FileUtil.getResourceContent(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Object obj = JSON.parseObject(jsonContext, clazz);
		return (T)obj;
	}

	public <T> T readJsonFile(String path, TypeReference<T> type) {
		String jsonContext = "";
		try {
			jsonContext = FileUtil.getResourceContent(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Object obj = JSON.parseObject(jsonContext, type);
		return (T)obj;
	}


	public void out(Object obj) {
		System.out.println(JSON.toJSONString(obj));
	}
}