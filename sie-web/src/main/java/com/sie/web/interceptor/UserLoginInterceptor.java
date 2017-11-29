package com.sie.web.interceptor;

import com.sie.framework.entity.LogEntity;
import com.sie.framework.type.Constant;
import com.sie.service.LogService;
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
import java.util.Map;

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

  @Autowired
  private LogService logService;

  /**
   * 日志
   */
  private static Log logger = LogFactory.getLog(UserLoginInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    HttpSession session = request.getSession();

    String url = request.getRequestURL().toString();
    String userName = (String)session.getAttribute(Constant.SYSTEM_USER_NAME_KEY);
    if(StringUtils.isBlank(userName)){
      response.sendRedirect("/login.html");
    }
    if(url.indexOf("addOrupdate.json") > -1 ||url.indexOf("delete.json") > -1 || url.indexOf("add.json") > -1 || url.indexOf("updateOrderInfo.json") > -1
            || url.indexOf("updateCourseIds.json") > -1){
      //记录日志
      LogEntity logEntity = new LogEntity();
      logEntity.setOperateUrl(request.getRequestURI());
      logEntity.setUserId((Integer)session.getAttribute(Constant.SYSTEM_USER_ID));
      //获取参数
      Map<String, String[]> params  = request.getParameterMap();
      if(!params.isEmpty()){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String[]> entry : params.entrySet()){
          if("_".equals(entry.getKey())){
            continue;
          }
          sb.append(entry.getKey()+":"+entry.getValue()[0]+",");
        }
        if(sb.length() > 0){
          logEntity.setComment(sb.substring(0, sb.length()-1));
        }
      }

     this.logService.saveOrUpdate(logEntity);
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
