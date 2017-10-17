package com.sie.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.entity.*;
import com.sie.framework.type.OrderStatus;
import com.sie.framework.type.OrderType;
import com.sie.framework.type.SystemType;
import com.sie.service.*;
import com.sie.service.bean.OrderBean;
import com.sie.service.bean.ResultBean;
import com.sie.service.vo.*;
import com.sie.util.*;
import com.sie.util.model.OAuthInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangheng on 2017/8/20.
 */
@Controller
@RequestMapping("/api/wechat")
public class WechatController {

    @Autowired
    private StudentService studentService;

    private static final Logger logger = LoggerFactory.getLogger(APIController.class);


    /**
     * 微信消息接收和token验证
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/ownerCheck")
    public void ownerCheck(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(111);
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter print;
        if (isGet) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
                try {
                    print = response.getWriter();
                    print.write(echostr);
                    print.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
    * 微信引导页进入的方法
    * @return
    */
    @ResponseBody
    @RequestMapping("/loginByWeiXin")
    public ResultBean loginByWeiXin(HttpServletRequest request, Map<String, Object> map) {

        ResultBean resultBean = new ResultBean();
        // 微信接口自带 2 个参数
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        logger.info("loginByWeiXin.json code="+code +" state="+state);

        if(code != null && !"".equals(code)) {
            // 授权成功, 微信获取用户openID
            OAuthInfo authInfo = WeiXinUtil.getAccess_token(code);
            String openid = authInfo.getOpenid();
            String access_token = authInfo.getAccess_token();

            if(access_token == null) {
                // Code 使用过 异常
               logger.error("Code 使用过 异常.....");
            }else{
                // 数据库中查询微信号是否绑定平台账号
                StudentEntity studentEntity = studentService.loginByOpenid(authInfo);
                resultBean.setSuccess(true);
                resultBean.setMessage("登录成功");
                resultBean.setData(studentEntity);
            }
        }
        return resultBean;

    }

}
