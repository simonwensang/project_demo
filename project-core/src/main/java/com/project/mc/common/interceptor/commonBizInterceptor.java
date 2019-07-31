package com.project.mc.common.interceptor;



import com.alibaba.fastjson.JSON;
import com.project.mc.common.CommonBizException;
import com.project.mc.result.McResult;
import com.project.mc.result.McResultCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@Order(1)
public class commonBizInterceptor {
    private final Logger logger = LoggerFactory.getLogger(commonBizInterceptor.class);

    @Around("execution(* com.allbuywine.mc.service..*.*(..))")
    //@Around("execution(* com.chinaredstar.mmc.service.client.impl.*.*(..))")
    public Object intercept(ProceedingJoinPoint pjp) throws Throwable {
        Class<?> targetClass = pjp.getTarget().getClass();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = targetClass.getMethod(signature.getName(), signature.getParameterTypes());
        Object[] args = pjp.getArgs();

        long startTimeMillis = System.currentTimeMillis();
        StringBuilder log = new StringBuilder(">>> Start Method ")
                .append(method.getName()).append(" with args : ");
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (i > 0) {
                log.append(", ");
            }
            log.append(JSON.toJSON(arg));
        }
        logger.info(log.toString());

        Object ret = null;
        try {
            ret = pjp.proceed(args);
        } catch (CommonBizException e) {
            logger.info(e.getErrorMsg() + " Input parameters=" + JSON.toJSON(args));
            ret = McResult.newInstance(e.getMcResultCode().code,e.getErrorMsg(),false,null);
        } catch (Exception e) {
            logger.error(e.getMessage() + " Input parameters=" + JSON.toJSON(args), e);
            ret = McResult.newInstance(McResultCode.SYS_ERROR,false,null);;
        }

        log = new StringBuilder(">>> Finish Method ")
                .append(method.getName()).append(" with return : ").append( JSON.toJSON(ret))
                .append(", elapsed: ").append(System.currentTimeMillis() - startTimeMillis).append(" ms");
        logger.info(log.toString());
        return ret;
    }
}
