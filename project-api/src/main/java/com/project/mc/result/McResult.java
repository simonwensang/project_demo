package com.project.mc.result;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "Rest result", description = "请求结果")
public class McResult<T> implements Serializable {
	private static final long serialVersionUID = -4696898674758059398L;

	@ApiModelProperty(value = "结果代码")
	private String code;
	@ApiModelProperty(value = "错误说明")
	private String message = "";
	@ApiModelProperty(value = "结果对象")
	private T dataMap;
	@ApiModelProperty(value = "操作标识")
	private boolean success;

	public McResult(String code, String message, boolean success, T dataMap){
		this.setCode(code);
		this.setMessage(message);
		this.setSuccess(success);
		this.setDataMap(dataMap);
	}

	public McResult() {
	}

	/**
	 *
	 * 构建返回对象实例
	 * @param resultCode
	 * @param success
	 * @param value
	 * @return
	 */
	public static <T> McResult<T> newInstance(McResultCode resultCode, boolean success, T value) {
		return new McResult<T>(resultCode.code, resultCode.message, success, value);
	}
	/**
	 *
	 * 构建返回对象实例
	 * @param code
	 * @param success
	 * @param value
	 * @return
	 */
	public static <T> McResult<T> newInstance(String code,String message, boolean success, T value) {
		return new McResult<T>( code,  message, success, value);
	}

	/**
	 *
	 * 构建成功对象实例
	 * @return
	 */
	public static <T> McResult<T> newSuccess() {
		return new McResult<T>(McResultCode.SUCCESS.code,
				McResultCode.SUCCESS.message, true, null);
	}

	/**
	 *
	 * 构建成功对象实例
	 * @return
	 */
	public static <T> McResult<T> newSuccess(T value) {
		return new McResult<T>(McResultCode.SUCCESS.code,
				McResultCode.SUCCESS.message, true, value);
	}

	/**
	 *
	 *	构建错误对象
	 * @param errorCode
	 * @return
	 */
	public static <T> McResult<T> newError(McResultCode errorCode) {
		return new McResult<T>(errorCode.code, errorCode.message, false, null);
	}

	public static <T> McResult<T> buildError(McResultCode resultCode, Object... args)
	{
		McResult<T> r = new McResult<T>("", "", false, null);
		r.setCode(resultCode.getCode());
		r.setFormatMessage(resultCode.getMessage(), args);

		return r;
	}

	/**
	 *
	 * 设置错误编码
	 * @param errorCode
	 */
	public  McResult<T> setErrorCode(McResultCode errorCode){
		if(errorCode ==null ) return null;

		this.code = errorCode.code;
		this.message = errorCode.message;
		this.success = false;
		return this;
	}

	/**
	 * 设置错误编码
	 * @param errorCode
	 * @param args
	 * @return
	 */
	public  McResult<T> setErrorCode(McResultCode errorCode, Object... args){
		if(errorCode ==null ) return null;

		this.code = errorCode.code;
		setFormatMessage(errorCode.getMessage(), args);
		this.success = false;
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getDataMap() {
		return dataMap;
	}

	public void setDataMap(T dataMap) {
		this.dataMap = dataMap;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMessageStatus(McResultCode resultCode, Object... args) {
		setCode(resultCode.getCode());
		setFormatMessage(resultCode.getMessage(), args);
	}

	public void setFormatMessage(String message, Object... args){
		if (args == null || args.length == 0) {
			this.setMessage(message);
		}else{
			this.setMessage(String.format(message, args));
		}
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
