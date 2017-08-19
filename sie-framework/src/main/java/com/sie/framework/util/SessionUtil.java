package com.sie.framework.util;

import com.sie.framework.type.Constant;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/6/9.
 */
public class SessionUtil {

    /**
     * 获取当前session里存的用户信息
     * @param name
     * @return
     */
    public static Object getBeanValue(String name){
        // NOTE 这里不使用getRequestAttributes()是为了防止RequestContextListener没有部署, 返回不可控的null值
        // NOTE currentRequestAttributes()在RequestContextListener未部署，取不到对象时，会直接抛出异常
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 如果是ServletRequestAttributes,返回对象
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return request.getSession().getAttribute(name);
        } else {
            // 一般出现这个异常原因是没有部署RequestContextListener且启用了JSF环境
            throw new IllegalStateException("当前上下文环境非Servlet环境");
        }

    }





    public static Integer getUserId(){
        return (Integer) getBeanValue(Constant.SYSTEM_USER_ID);
    }

    public static String getUserName(){
        return (String) getBeanValue(Constant.SYSTEM_USER_NAME_KEY);
    }

    public static String getUserNickName(){
        return (String) getBeanValue(Constant.SYSTEM_USER_NICK_NAME_KEY);
    }

    public static void setSessionAttribute(String name, String value){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute(name, value);
    }
}
