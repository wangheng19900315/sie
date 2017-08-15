package com.sie.web.interceptor;

import com.sie.framework.type.Constant;
import com.sie.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 用戶权限拦截器<br>
 * <p>
 * Copyright: Copyright (c) 2017年5月15日 下午4:38:42
 * <p>
 * Company: 京东
 * <p>
 *
 * @author wangheng5@jd.com
 * @version 1.0.0
 */
public class UserLoginInterceptor implements HandlerInterceptor {

  private static final String VERSION = DateFormatUtils.format(new Date(System.currentTimeMillis()), "yyyyMMddHH");


  /**
   * 日志
   */
  private static Log logger = LogFactory.getLog(UserLoginInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    HttpSession session = request.getSession();

    String url = request.getRequestURI();
    String userName = (String)session.getAttribute(Constant.SYSTEM_USER_NAME_KEY);
    if(StringUtils.isBlank(userName)){
      response.sendRedirect("/login.html");
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    // TODO Auto-generated method stub

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    // TODO Auto-generated method stub

  }

}
