package com.project.mc.common;


import com.project.mc.result.McResult;
import com.project.mc.result.McResultCode;
import com.project.mc.result.McResultCode;

public class CommonBizException extends BaseException{
	private static final long serialVersionUID = 1L;
	
	private McResultCode mcResultCode;

    public CommonBizException(String errorCode, String errorMsg, Throwable caused) {
        super(errorCode, errorMsg, caused);
    }

    public CommonBizException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public CommonBizException(String errorCode, String errorMsg, Object... args) {
        super(errorCode, errorMsg, args);
    }

    public CommonBizException(McResultCode resultCode){
        this(resultCode.getCode(),resultCode.getMessage());
        this.mcResultCode = resultCode;
    }

    public CommonBizException(McResultCode resultCode, Object... args){
        this(resultCode.getCode(),resultCode.getMessage(), args);
        this.mcResultCode = resultCode;
    }

    public CommonBizException(String errorCode, Throwable caused) {
        super(errorCode, caused);
    }

	public McResultCode getMcResultCode() {
		return mcResultCode;
	}
	
//	public void setMmcResultCode(MmcResultCode MmcResultCode) {
//		this.mmcResultCode = MmcResultCode;
//	}

}