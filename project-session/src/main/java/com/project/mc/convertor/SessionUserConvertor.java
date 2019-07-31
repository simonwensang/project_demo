package com.project.mc.convertor;

import com.project.mc.bean.SessionUser;
import com.project.mc.model.User;

import com.project.mc.bean.SessionUser;
import org.springframework.cglib.beans.BeanCopier;


/**
 * @CopyRright (c)2008-2017: <自定义>
 * @Project: <mc>
 * @Comments: <Dubbo层转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @JDK 1.7
 * @Author: <sang>
 * @email : <sangyiwen456@126.com>
 * @Create Date: <2018-01-22>
 * @Modify Date: <2018-01-22>
 * @Version: <1.0>
 */
public abstract class SessionUserConvertor {

    private static final BeanCopier beanCopierForSessionUser = BeanCopier.create(User.class, SessionUser.class, false);


    public static SessionUser toSessionUser(User user) {
        if(user== null){
            return  null;
        }
        SessionUser sessionUser = new SessionUser();
        beanCopierForSessionUser.copy(user, sessionUser, null);
        return sessionUser;
    }



}