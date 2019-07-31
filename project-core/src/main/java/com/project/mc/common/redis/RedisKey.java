package com.project.mc.common.redis;

import org.apache.commons.lang3.StringUtils;

public class RedisKey {
    public static final String PREFIX = "mkt:";
    public static final String LOCK = "lock:";

    private  static final String MOBILE_REGIST_PREFIX="MOBILE_REGIST:";
    private  static final String MOBILE_FORGETPWD_PREFIX="MOBILE_FORGETPWD:";

    public static String getRegistCacheKey (String mobile) {
        if (mobile == null ) {
            return "";
        }
        StringBuilder sbBuilder = new StringBuilder(50);
        sbBuilder.append(MOBILE_REGIST_PREFIX);
        sbBuilder.append(mobile);
        return sbBuilder.toString();
    }

    public static String getForgetPwdCacheKey (String mobile) {
        if (mobile == null ) {
            return "";
        }
        StringBuilder sbBuilder = new StringBuilder(50);
        sbBuilder.append(MOBILE_FORGETPWD_PREFIX);
        sbBuilder.append(mobile);
        return sbBuilder.toString();
    }
}
