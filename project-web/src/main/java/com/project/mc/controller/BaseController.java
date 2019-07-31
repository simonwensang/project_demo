package com.project.mc.controller;


import com.project.mc.bean.SessionUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseController {
    private final Logger logger = LoggerFactory.getLogger(BaseController.class);

    public SessionUser getUser() {
        SessionUser user = null;
        if(SecurityUtils.getSubject().isAuthenticated()){
            user = (SessionUser)SecurityUtils.getSubject().getPrincipal();
        }
        return user;
    }

    public Long getUserId() {
        SessionUser user = getUser();
        if(null != user){
            return user.getId();
        }
        return null;
    }



    public String getSessionId() {
        String sessionId = null;
        Session session = SecurityUtils.getSubject().getSession();
        if( null != session){
            sessionId = session.getId().toString();
        }
        return sessionId;
    }

}
