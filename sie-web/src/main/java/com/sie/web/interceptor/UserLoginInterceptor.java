package com.sie.web.interceptor;

import com.jd.bdp.wisdom.service.UserLoginService;
import com.jd.bdp.wisdom.service.UserOperationService;
import com.jd.common.web.LoginContext;
import com.jd.wisdom.pojo.UserEntity;
import com.jd.wisdom.pojo.UserOperationEntity;
import com.jd.wisdom.type.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
  private UserLoginService userLoginService;

  @Autowired
  private UserOperationService userOperationService;

  @Value("${admin.user.names}")
  private String userNames;

  /**
   * 日志
   */
  private static Log logger = LogFactory.getLog(UserLoginInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    HttpSession session = request.getSession();
    String url = request.getRequestURL().toString();

    //获取客户端ip
    String clientIp = request.getHeader("x-forwarded-for");
    if(clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
      clientIp = request.getHeader("Proxy-Client-IP");
    }
    if(clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
      clientIp = request.getHeader("WL-Proxy-Client-IP");
    }
    if(clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
      clientIp = request.getRemoteAddr();
    }

    if (url.indexOf(".html") > -1) {
      request.setAttribute("aceStaticVersion", VERSION);
      request.setAttribute("staticVersion", VERSION);

      // 用户登录的erp
      //判断登录信息是否已经创建
      String userLoginFlag = (String)session.getAttribute(Constant.SYSTEM_USER_LOG_FLAG);
      if(StringUtils.isNotEmpty(userLoginFlag)){
        return true;
      }

      String erp = LoginContext.getLoginContext().getPin();
      logger.debug("从session 中获取 获取到的 LoginContext 为[" + erp + "]");
      String username = (String) session.getAttribute(Constant.SYSTEM_USER_NAME_KEY);
      logger.debug("从session 中获取 获取到的 USER_NAME 为[" + username + "]");
      if (StringUtils.isBlank(username)) {
        session.setAttribute(Constant.SYSTEM_USER_NAME_KEY, erp);
        session.setAttribute(Constant.SYSTEM_USER_NICK_NAME_KEY, LoginContext.getLoginContext().getNick());
        username = (String) session.getAttribute(Constant.SYSTEM_USER_NAME_KEY);
      }

      //保存登录信息
      try{
        UserOperationEntity userOperationEntity = new UserOperationEntity();
        userOperationEntity.setUserName((String) session.getAttribute(Constant.SYSTEM_USER_NAME_KEY));
        userOperationEntity.setClientUrl(clientIp);
        userOperationEntity.setRequestUrl("/login.ajax");
        UserEntity userEntity =userOperationService.insert(userOperationEntity);
        if(userEntity != null){
          session.setAttribute(Constant.SYSTEM_USER_LOG_FLAG,"1");
          session.setAttribute(Constant.SYSTEM_USER_ID,userEntity.getId());
          session.setAttribute(Constant.SYSTEM_USER_ROLE_FLAG,userEntity.getAvaliable());

          session.setAttribute(Constant.SYSTEM_USER_MART_NAME,userEntity.getMartName());
          session.setAttribute(Constant.SYSTEM_USER_MANAGER_MART_NAME,userEntity.getManagerMartName());
          if(userNames.indexOf(username) > -1){
            session.setAttribute(Constant.SYSTEM_USER_ROLE_FLAG,1);
          }

          if(!session.getAttribute(Constant.SYSTEM_USER_ROLE_FLAG).equals(1) && StringUtils.isEmpty(userEntity.getMartName())){
            response.sendRedirect("/noPermission.html");
            return true;
          }
        }
      }catch (Exception e){
        e.printStackTrace();
      }

    }else if(url.indexOf(".ajax") > -1){
      //保存用户操作信息
      try {
        UserOperationEntity userOperationEntity = new UserOperationEntity();
        userOperationEntity.setUserName((String) session.getAttribute(Constant.SYSTEM_USER_NAME_KEY));
        userOperationEntity.setClientUrl(clientIp);
        userOperationEntity.setRequestUrl(request.getRequestURI());

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
            userOperationEntity.setParameters(sb.substring(0, sb.length()-1));
          }
        }

        this.userOperationService.insert(userOperationEntity);
      }catch (Exception e){
        e.printStackTrace();
      }


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
