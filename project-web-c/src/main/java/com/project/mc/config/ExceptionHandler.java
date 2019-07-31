package com.project.mc.config;

import com.allbuywine.mc.result.McResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ExceptionHandler implements HandlerExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);


    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        ModelAndView mv = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        Map<String, Object> attributes = new HashMap();
        attributes.put("code", McResultCode.SYS_ERROR.code);
        attributes.put("message", McResultCode.SYS_ERROR.message);
        attributes.put("dataMap", "内部异常");
        attributes.put("success", false);
        logger.error("内部异常", ex);
        logger.error("内部异常", exceptionStackTrace(ex));
        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }

    @Bean
    public ExceptionHandler createExceptionHandler() {
        return new ExceptionHandler();
    }


    public static String exceptionStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String stackTraceMsg = sw.toString();
        return stackTraceMsg;
    }

}
